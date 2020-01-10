package com.xiangwan.flashsale.logic.configurer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.xiangwan.flashsale.constants.AppConstants;
import com.xiangwan.flashsale.logic.user.domain.User;
import com.xiangwan.flashsale.logic.user.service.UserService;

/**
 * 使用自定义的ArgumentResolver
 * @author Administrator
 *
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private UserService userService;
	
	/**
	 * 如果有这个User参数才需要做这个处理
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> clazz = parameter.getParameterType();
		return clazz == User.class;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		//HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		
		String paramToken = request.getParameter(AppConstants.COOKIE_TOKEN);
		String cookieToken = this.getCookieValue(request, AppConstants.COOKIE_TOKEN);
		if (StringUtils.isEmpty(paramToken) && StringUtils.isEmpty(cookieToken)) {
			return null;
		}
		
		String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
		User user = this.userService.getUserByToken(request, token);
		return user;
	}
	
	private String getCookieValue(HttpServletRequest request, String cookieToken) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieToken)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

}
