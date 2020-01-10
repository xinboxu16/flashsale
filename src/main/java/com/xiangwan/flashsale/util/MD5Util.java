package com.xiangwan.flashsale.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
	
	public static final String SALT = "xu09cui19qi";
	
	public static String md5(String src) {
		return DigestUtils.md5Hex(src);
	}
	
	//将输入的密码转为表单密码
	public static String inputPwdToFormPwd(String inputPassword) {
		String newPwd = "" + SALT.charAt(0) + SALT.charAt(2) + inputPassword + SALT.charAt(5) +SALT.charAt(6);
		return md5(newPwd);
	}
	
	//二次md5 将表单密码转为数据库密码
	public static String formPasswordToDB(String inputPassword, String salt) {
		String newPwd = "" + salt.charAt(2) + salt.charAt(6) + inputPassword + salt.charAt(3) +salt.charAt(5);
		return md5(newPwd);
	}
	
	//将二次加密密码生成放入数据库的密码
	public static String inputPasswordToDB(String inputPassword, String saltDB) {
		String formPwd = inputPwdToFormPwd(inputPassword);
		String dbPass = formPasswordToDB(formPwd, saltDB);
		return dbPass;
	}
}
