package com.xiangwan.flashsale.logic.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangwan.flashsale.cache.redis.service.RedisService;
import com.xiangwan.flashsale.constants.AppConstants;
import com.xiangwan.flashsale.logic.result.Result;
import com.xiangwan.flashsale.logic.user.domain.User;
import com.xiangwan.flashsale.logic.user.service.UserService;

@Controller
@RequestMapping(value="/demo")//(value="/demo", produces="application/json;charset=UTF-8")
public class DemoController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisService redisService;
	
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
	
	//连接数据库
	@RequestMapping("/db/get")
	@ResponseBody
	public Result<User> doGet() {
		User user = this.userService.getUserById((long)1);
		return new Result<User>(user);
	}
	
	//事务测试
	@RequestMapping("/db/tx")
	@ResponseBody
	public Result<Boolean> doTx() {
		this.userService.tx();
		return new Result<Boolean>(true);
	}
	
	//redis prefix增加前缀 防止重复
	@RequestMapping("/redis/get")
	@ResponseBody
	public Result<String> redisGet() {
		String value = this.redisService.get("say", String.class);
		return new Result<String>(value);
	}
	
	@RequestMapping("/redis/set")
	@ResponseBody
	public Result<Boolean> redisSet() {
		boolean ret = this.redisService.set("say", "cuiqi,hello");
		return new Result<Boolean>(ret);
	}
	
	@RequestMapping("/redis/getUser")
	@ResponseBody
	public Result<User> redisGetUser() {
		User user = this.redisService.get(AppConstants.USER_KEY_ID, "1", User.class);
		return new Result<User>(user);
	}
	
	@RequestMapping("/redis/setUser")
	@ResponseBody
	public Result<Boolean> redisSetUser() {
		User user = new User(1, "崔琦");
		boolean ret = this.redisService.set(AppConstants.USER_KEY_ID, "1", user);
		return new Result<Boolean>(ret);
	}
}
