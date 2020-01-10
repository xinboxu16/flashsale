 package com.xiangwan.flashsale;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan({"com.xiangwan.flashsale.**.dao"})
public class App extends SpringBootServletInitializer
{
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
	
	/**
	 * jar和war包都能打包
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}
}
