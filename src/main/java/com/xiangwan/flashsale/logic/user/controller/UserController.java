package com.xiangwan.flashsale.logic.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangwan.flashsale.logic.result.Result;
import com.xiangwan.flashsale.logic.user.domain.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	/**
	 * 使用这个参数做性能测试 吞吐量（qps）会高 因为只进行redis获取数据 所以性能消耗在数据库地方的大
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping("/info")
	@ResponseBody
	public Result<User> getUserById(Model model, User user) {
		return new Result<User>(user);
	}
}
