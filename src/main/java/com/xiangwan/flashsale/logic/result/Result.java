package com.xiangwan.flashsale.logic.result;

public class Result<T> {
	private int code;
	private String msg;
	private T data;
	
	public Result(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public Result(CodeMessage codeMsg, T data) {
		this.code = codeMsg.getCode();
		this.msg = codeMsg.getMsg();
		this.data = data;
	}
	
	public Result(CodeMessage codeMsg) {
		this.code = codeMsg.getCode();
		this.msg = codeMsg.getMsg();
	}
	
	public Result(CodeMessage codeMsg, Object... args) {
		this.code = codeMsg.getCode();
		String message = codeMsg.getMsg();
		this.msg = String.format(message, args);
	}
	
	public Result(T data) {
		this.code = 0;
		this.msg = "";
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
