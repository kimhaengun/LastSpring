package com.cos.myjpa.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.handler.ex.MyAuthenticationException;

@Configuration //IOC에 등록
public class WebMvcConfig implements WebMvcConfigurer{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptor() {
			
			//컨트롤러 실행 직전에 동작
			//반환값이 true일 경우 정상적인 진행 / false일 경우 컨트롤러 진입안함
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				// TODO Auto-generated method stub
				HttpSession session = request.getSession();
				User principal = (User) session.getAttribute("principal");
				
				if(principal == null) {
					throw new MyAuthenticationException();
				}else { //로그인 성공하면 컨트롤러 진입
					return true;
				}
			}
			
		}).addPathPatterns("/user").addPathPatterns("/post");
	}
}
