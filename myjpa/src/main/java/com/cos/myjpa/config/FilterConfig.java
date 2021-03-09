package com.cos.myjpa.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.cos.myjpa.filter.MyAuthenticationFilter;

//web.xml

@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<MyAuthenticationFilter> authenticationFilterAddRegister() {
		FilterRegistrationBean<MyAuthenticationFilter> bean =
				new FilterRegistrationBean<>(new MyAuthenticationFilter());//내가 만든 필터
		
		bean.addUrlPatterns("/test");
		bean.setOrder(1); //숫자가 낮은 번호가 먼저 실행된다.
		
		
		return bean;
		
	}
}
