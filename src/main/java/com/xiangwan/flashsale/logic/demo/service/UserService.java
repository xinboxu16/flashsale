package com.xiangwan.flashsale.logic.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiangwan.flashsale.logic.demo.dao.UserDao;
import com.xiangwan.flashsale.logic.demo.domain.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public User getUserById(long id) {
		return this.userDao.getUserById(id);
	}
}
