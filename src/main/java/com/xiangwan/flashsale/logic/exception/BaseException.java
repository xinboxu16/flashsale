package com.xiangwan.flashsale.logic.exception;

import com.xiangwan.flashsale.logic.result.CodeMessage;

public class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private CodeMessage codeMessage;
	
	public BaseException(CodeMessage codeMessage) {
		super(codeMessage.toString());
		this.codeMessage = codeMessage;
	}
	
	public CodeMessage getCodeMessage() {
		return codeMessage;
	}
}
