package com.xiangwan.flashsale.constants;

import com.xiangwan.flashsale.cache.redis.dao.impl.UserKey;
import com.xiangwan.flashsale.logic.result.CodeMessage;

public class AppConstants {
	public final static String SUCCESS = "success"; 
	public static final CodeMessage EXCEPTION_ERROR = new CodeMessage(100001, "%s");
	//消息返回状态
	public static final CodeMessage SERVER_SUCESS = new CodeMessage(200, "服务器正常");
	//服务器异常
	public static final CodeMessage SERVER_ERROR = new CodeMessage(500001, "服务器异常");
	//登录
	public static final CodeMessage LOGIN_CORRECT = new CodeMessage(0, "登陆成功");
	public static final CodeMessage LOGIN_EMPTY = new CodeMessage(600001, "手机号或密码不能为空");
	public static final CodeMessage MOBILE_ERROR = new CodeMessage(600002, "手机号格式错误");
	public static final CodeMessage MOBILE_NOT_EXIST = new CodeMessage(600003, "用户手机号不存在");
	public static final CodeMessage PASSWORD_ERROR = new CodeMessage(600005, "密码错误");
	public static final int USER_ERROR_CODE = 600006;
	public static final CodeMessage USER_ERROR = new CodeMessage(USER_ERROR_CODE, "用户不存在,重新登录");	
	//秒杀模块
	public static final CodeMessage SALE_EMPTY_ERROR = new CodeMessage(700001, "商品秒杀完毕");
	public static final CodeMessage SALE_REPEATE = new CodeMessage(700002, "不能重复秒杀");
	public static final CodeMessage SALE_GOODS_LECK = new CodeMessage(700003, "库存不足!");
	
	//redis缓存 key设置
	public static final UserKey USER_KEY_ID = new UserKey("id");
	public static final UserKey USER_KEY_NAME = new UserKey("name");
	
	//redis-token 设置过期时间
	public static final UserKey USER_KEY_TOKEN = new UserKey("token", 3600);
	
	//cookie
	public static final String COOKIE_TOKEN = "token";
}
