package com.xiangwan.flashsale.cache.redis.dao.impl;

import com.xiangwan.flashsale.cache.redis.dao.impl.base.BasePrefix;

public class UserKey extends BasePrefix {
	public UserKey(String prefix, int expireSeconds) {
		super(expireSeconds, prefix);
	}
	
	public UserKey(String prefix) {
		super(prefix);
	}
}
