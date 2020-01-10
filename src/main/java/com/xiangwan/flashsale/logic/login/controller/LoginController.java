package com.xiangwan.flashsale.logic.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangwan.flashsale.logic.login.vo.LoginVo;
import com.xiangwan.flashsale.logic.result.Result;
import com.xiangwan.flashsale.logic.user.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 这个没有ResponseBody根据application.properities直接调用获取templates下的html
	 * @return
	 */
	@RequestMapping("/to_login")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("/do_login")
	@ResponseBody
	public Result<Boolean> doLogin(HttpServletRequest request, HttpServletResponse response, @Valid LoginVo loginVo) {
		logger.info(loginVo.toString());
		//参数校验 改成使用spring-boot-starter-validation方式校验
		/*String password = loginVo.getPassword();
		String mobile = loginVo.getMobile();
		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(mobile)) {
			return new Result<String>(AppConstants.LOGIN_EMPTY);
		}
		
		if (!StringUtil.isMobile(mobile)) {
			return new Result<String>(AppConstants.MOBILE_ERROR);
		}
		
		CodeMessage loginMsg = this.userService.login(loginVo);
		
		return new Result<String>(loginMsg);*/
		//采用捕获异常方式抛出 拦截异常输出
		this.userService.login(request, response, loginVo);

		return new Result<Boolean>(true);
	}
}
