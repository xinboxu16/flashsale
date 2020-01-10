package com.xiangwan.flashsale.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {
	@Autowired
	private RedisConfig redisConfig;
	
	@Bean
	public JedisPool jedisPoolFactory() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
		jedisPoolConfig.setMaxTotal(redisConfig.getPoolMaxActive());
		jedisPoolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
		//jedisPoolConfig.setTestOnBorrow(false);
		
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisConfig.getHost(), 
				redisConfig.getPort(), redisConfig.getTimeout()*1000,redisConfig.getPassword(),
				redisConfig.getDatabase());
		return jedisPool;
	}
}
