package com.xiangwan.flashsale.cache.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xiangwan.flashsale.cache.redis.dao.KeyPrefix;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
	@Autowired
	private JedisPool jedisPool;
	
	public <T> boolean set(String key, T value) {
		return this.set(null, key, value);
	}
	
	public <T> T get(String key, Class<T> clazz) {
		return this.get(null, key, clazz);
	}
	
	public <T> boolean set(KeyPrefix prefix, String key, T value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String str = beanToString(value);
			if (str == null || str.length() == 0) {
				return false;
			}

			if (prefix == null) {
				jedis.set(key, str);
			} else {
				//生成真正的key
				String realKey = prefix.getPrefix() + key;
				int seconds = prefix.getExpireSeconds();
				if (seconds > 0) {
					jedis.setex(realKey, seconds, str);
				} else {
					jedis.set(realKey, str);
				}
			}
			
			return true;
		}
		finally {
			//释放连接池
			returnToPool(jedis);
		}
	}

	public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			//生成真正的key
			String realKey;
			if (prefix == null) {
				realKey = key;
			} else {
				realKey = prefix.getPrefix() + key;
			}
			String value = jedis.get(realKey);
			T t = stringToBean(value, clazz);
			return t;
		} finally {
			//释放连接池
			returnToPool(jedis);
		}
	}
	
	public boolean exist(String key) {
		return this.exist(null, key);
	}
	
	public boolean exist(KeyPrefix prefix, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			//生成真正的key
			String realKey;
			if (prefix == null) {
				realKey = key;
			} else {
				realKey = prefix.getPrefix() + key;
			}
			return jedis.exists(realKey);
		} finally {
			//释放连接池
			returnToPool(jedis);
		}
	}
	
	public <T> Long incr(String key) {
		return this.incr(null, key);
	}
	
	public <T> Long incr(KeyPrefix prefix, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			//生成真正的key
			String realKey;
			if (prefix == null) {
				realKey = key;
			} else {
				realKey = prefix.getPrefix() + key;
			}
			return jedis.incr(realKey);
		} finally {
			//释放连接池
			returnToPool(jedis);
		}
	}
	
	public <T> Long decr(String key) {
		return this.decr(null, key);
	}
	
	public <T> Long decr(KeyPrefix prefix, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			//生成真正的key
			String realKey;
			if (prefix == null) {
				realKey = key;
			} else {
				realKey = prefix.getPrefix() + key;
			}
			return jedis.decr(realKey);
		} finally {
			//释放连接池
			returnToPool(jedis);
		}
	}
	
	private <T> String beanToString(T value) {
		if (value == null) {
			return null;
		}
		
		Class<?> clazz = value.getClass();
		if (clazz == int.class || clazz == Integer.class) {
			return ""+value;
		} else if (clazz == long.class || clazz == Long.class) {
			return ""+value;
		} else if (clazz == String.class) {
			return (String)value;
		} else {
			return JSON.toJSONString(value);
		}
	}

	//只是bean类型转换 list不支持
	@SuppressWarnings("unchecked")
	private <T> T stringToBean(String value, Class<T> clazz) {
		if (value == null || value.length() == 0 || clazz == null) {
			return null;
		}
		if (clazz == int.class || clazz == Integer.class) {
			return (T) Integer.valueOf(value);
		} else if (clazz == long.class || clazz == Long.class) {
			return (T) Long.valueOf(value);
		} else if (clazz == String.class) {
			return (T) value;
		} else {
			return JSON.toJavaObject(JSON.parseObject(value), clazz);
		}
	}

	private void returnToPool(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}
}
