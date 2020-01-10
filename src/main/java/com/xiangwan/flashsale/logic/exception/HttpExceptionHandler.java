package com.xiangwan.flashsale.logic.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangwan.flashsale.constants.AppConstants;
import com.xiangwan.flashsale.logic.result.CodeMessage;
import com.xiangwan.flashsale.logic.result.Result;

/**
 * 处理捕获的异常
 * @author Administrator
 *
 */
@ControllerAdvice
@ResponseBody
public class HttpExceptionHandler {
	@ExceptionHandler(value=Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest request, Exception ex) {
		ex.printStackTrace();
		if (ex instanceof BaseException) {
			BaseException baseException = (BaseException) ex;
			CodeMessage codeMessage = baseException.getCodeMessage();
			return new Result<String>(codeMessage);
		}
		if (ex instanceof BindException) {
			BindException bindException = (BindException) ex;
			List<ObjectError> errors = bindException.getAllErrors();
			ObjectError objectError = errors.get(0);
			Object[] msg = {objectError.getDefaultMessage()};
			return new Result<String>(AppConstants.EXCEPTION_ERROR, msg);
		} else {
			return new Result<String>(AppConstants.SERVER_ERROR);
		}
	}
}
