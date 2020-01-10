package com.xiangwan.flashsale.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Util {
	private static final Pattern mobilePattern = Pattern.compile("1\\d{10}");
	/**
	 * 手机号是否正确
	 * @param phoneNum
	 * @return
	 */
	public static boolean isMobile(String phoneNum) {
		if (StringUtils.isEmpty(phoneNum)) {
			return false;
		}
		Matcher matcher = mobilePattern.matcher(phoneNum);
		return matcher.matches();
	}
	
	/**
	 * 生成唯一id
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
