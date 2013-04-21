package com.jkonury.www.common.interceptor;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jkonury.www.common.security.LoginInfo;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Inject
	private Provider<LoginInfo> loginInfoProvider; 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println(request.getRequestURI());
		
		String uri = request.getRequestURI();
		
		if(uri.contains("logout")) {
			return true;
		}
		
		if (loginInfoProvider.get().isLoggedIn()) {
			return true;
		}
		else {
			if(uri.contains("login") || uri.contains("/user/add")) {
				return true;
			}
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
	}
	
}
