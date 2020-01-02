package com.xiangwan.flashsale.constants;

import com.xiangwan.flashsale.logic.result.CodeMessage;

public class AppConstants {
	public final static String SUCCESS = "success"; 
	//消息返回状态
	public static CodeMessage SERVER_SUCESS = new CodeMessage(200, "服务器正常");
	//服务器异常
	public static CodeMessage SERVER_ERROR = new CodeMessage(500100, "服务器异常");
}
