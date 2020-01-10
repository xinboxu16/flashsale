package com.xiangwan.flashsale.logic.configurer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * webmvc配置
 * @author Administrator
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
	@Autowired
	private UserArgumentResolver userArgumentResolver;
	
	/**
	 * 解决继承WebMvcConfigurationSupport之后无法加载静态资源
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
			.addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}
	
	/**
	 * @RequestMapping("/to_list")
	 *	public String toList(HttpServletResponse response, Model model, 
	 *		@CookieValue(value=AppConstants.COOKIE_TOKEN, required=false) String cookieToken,
	 *		@RequestParam(value=AppConstants.COOKIE_TOKEN, required=false) String paramToken)
	 * Controller这上面的方法携带的参数就是从addArgumentResolvers这个方法中扩展出去的
	 */
	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(this.userArgumentResolver);
	}
}
