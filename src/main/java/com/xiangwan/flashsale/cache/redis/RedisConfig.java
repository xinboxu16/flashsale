package com.xiangwan.flashsale.cache.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 添加@Component 注释简单地让它成为组件扫描的一部分
 * 设置@ConfigurationProperties类的字段必须有公共setter 
 * 
 */
@Component
@ConfigurationProperties(prefix="redis")//以spring.redis开头的配置都读入
public class RedisConfig {
	private String host;
	private int port;
	private int timeout;
	private String password;
	private int database;
	private int poolMaxActive;
	private int poolMaxIdle;
	/**
	 * 用了两种方式来读取application.properties中的配置，一种是直接设置@ConfigurationProperties(prefix = “spring.redis”)，
	 * 然后变量名与application.properties中的变量名一样，这样就可以读出来了，然后像max-wait这种变量名没法定义啊，
	 * 所以又用了@Value(“${spring.redis.jedis.pool.max-idle}”)这种注解的方式来读取，
	 * 当然你可以全部影注解的方式来读取，注意过程中的每一个变量的基本数据类型定义准确。
	 * maxWait和timeout这两个本来都是Duration类型的，但是这里分别写成long和int类型的。一会儿再解释这个问题。
	 */
	//@Value("${spring.redis.jedis.pool.max-active}")
	private int poolMaxWait;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setDatabase(int database) {
		this.database = database;
	}
	public int getDatabase() {
		return database;
	}
	
	public int getPoolMaxActive() {
		return poolMaxActive;
	}
	
	public void setPoolMaxActive(int poolMaxActive) {
		this.poolMaxActive = poolMaxActive;
	}
	
	public int getPoolMaxIdle() {
		return poolMaxIdle;
	}
	public void setPoolMaxIdle(int poolMaxIdle) {
		this.poolMaxIdle = poolMaxIdle;
	}
	
	public int getPoolMaxWait() {
		return poolMaxWait;
	}
	public void setPoolMaxWait(int poolMaxWait) {
		this.poolMaxWait = poolMaxWait;
	}
}
