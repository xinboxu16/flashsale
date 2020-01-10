package com.xiangwan.flashsale.cache.redis.dao;

public interface KeyPrefix {
	public int getExpireSeconds();
	public String getPrefix();
}
