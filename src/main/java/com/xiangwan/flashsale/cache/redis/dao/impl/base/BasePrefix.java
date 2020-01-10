package com.xiangwan.flashsale.cache.redis.dao.impl.base;

import com.xiangwan.flashsale.cache.redis.dao.KeyPrefix;

public abstract class BasePrefix implements KeyPrefix {

	private int expireSeconds = 0;
	
	private String prefix = "";

	//0永不过期
	public BasePrefix(String prefix) {
		this(0, prefix);
	}
	
	public BasePrefix(int expireSeconds, String prefix) {
		this.expireSeconds = expireSeconds;
		this.prefix = prefix;
	}
	
	
	@Override
	public int getExpireSeconds() {
		return this.expireSeconds;
	}

	@Override
	public String getPrefix() {
		String className = this.getClass().getSimpleName();
		return className+":"+this.prefix+"_";
	}
}
