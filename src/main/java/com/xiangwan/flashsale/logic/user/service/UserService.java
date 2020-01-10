package com.xiangwan.flashsale.logic.user.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiangwan.flashsale.cache.redis.service.RedisService;
import com.xiangwan.flashsale.constants.AppConstants;
import com.xiangwan.flashsale.logic.exception.BaseException;
import com.xiangwan.flashsale.logic.login.vo.LoginVo;
import com.xiangwan.flashsale.logic.user.dao.UserDao;
import com.xiangwan.flashsale.logic.user.domain.User;
import com.xiangwan.flashsale.util.MD5Util;
import com.xiangwan.flashsale.util.Util;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RedisService redisService;
	
	public User getUserById(long id) {
		return this.userDao.getUserById(id);
	}
	
	public User getUserByPhoneNumber(long phoneNumber) {
		return this.userDao.getUserByPhoneNumber(phoneNumber);
	}
	
	/**
	 * 用户登录
	 * @param response
	 * @param loginVo
	 * @return
	 */
	public boolean login(HttpServletRequest request, HttpServletResponse response, LoginVo loginVo) {
		if (loginVo == null) {
			throw new BaseException(AppConstants.SERVER_ERROR);
		}
		
		String phoneNumber = loginVo.getMobile();
		String pwd = loginVo.getPassword();
		
		User user = this.getUserByPhoneNumber(Long.parseLong(phoneNumber));
		if (user == null) {
			throw new BaseException(AppConstants.MOBILE_NOT_EXIST);
		}
		
		//验证密码
		String dbPass = user.getPassword();
		String slatDB = user.getSalt();
		
		String calcPwd = MD5Util.formPasswordToDB(pwd, slatDB);
		if (!calcPwd.equals(dbPass)) {
			throw new BaseException(AppConstants.PASSWORD_ERROR);
		}
		
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length <= 0) {
			this.addCookie(response, user);
		}
		
		return true;
	}
	
	/**
	 * 增加cookie
	 * @param response
	 * @param user
	 */
	public void addCookie(HttpServletResponse response, User user) {
		//登陆成功生成token
		String token = Util.uuid();
		this.redisService.set(AppConstants.USER_KEY_TOKEN, token, user);
		//生成cookie
		Cookie cookie = new Cookie(AppConstants.COOKIE_TOKEN, token);
		cookie.setMaxAge(AppConstants.USER_KEY_TOKEN.getExpireSeconds());
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/**
	 * 增加cookie时间
	 * @param response
	 * @param user
	 */
	public void prolongCookie(HttpServletRequest request, String token, User user) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getValue().equals(token)) {
					this.redisService.set(AppConstants.USER_KEY_TOKEN, token, user);
					cookie.setMaxAge(AppConstants.USER_KEY_TOKEN.getExpireSeconds());
				}
			}
		}
		
	}
	
	/**
	 * 根据cookie从缓存中获取 并且延长cookie
	 * @param token
	 * @return
	 */
	public User getUserByToken(HttpServletRequest request, String token) {
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		
		User user = this.redisService.get(AppConstants.USER_KEY_TOKEN, token, User.class);
		//延长有效期
		if (user != null) {
			this.prolongCookie(request, token, user);
		}
		return user;
	}

	//@Transactional事务 这两个插入在相同的事务中 因为第二条事务数据库中已经存在会导致事务回滚 则第一条应该成功的也会失败
	@Transactional
	public boolean tx() {
		User user = new User();
		user.setId(2);
		user.setName("张丽");
		this.userDao.insert(user);
		
		User user2 = new User();
		user2.setId(1);
		user2.setName("崔琦1");
		this.userDao.insert(user2);
		
		return true;
	}
}
