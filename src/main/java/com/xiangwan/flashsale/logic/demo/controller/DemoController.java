package com.xiangwan.flashsale.logic.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangwan.flashsale.constants.AppConstants;
import com.xiangwan.flashsale.result.Result;

@Controller
@RequestMapping(value="/demo")//(value="/demo", produces="application/json;charset=UTF-8")
public class DemoController {
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello World Tomcat!";
	}
	
	@RequestMapping(value="/success")
	@ResponseBody
	public Result<String> sucess() {
		return new Result<String>(AppConstants.SERVER_SUCESS, "成功得到了数据");
	}
	
	@RequestMapping("/error")
	@ResponseBody
	public Result<String> error() {
		return new Result<String>(AppConstants.SERVER_ERROR);
	}
	
	@RequestMapping(value="/thymeleaf")
	public String thymeleaf(Model model) {
		model.addAttribute("name", "这是一个thymeleaf模板html");
		return "hello";
	}
	
}