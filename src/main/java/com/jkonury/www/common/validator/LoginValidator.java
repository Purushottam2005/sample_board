package com.jkonury.www.common.validator;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jkonury.www.common.security.LoginInfo;
import com.jkonury.www.user.model.User;
import com.jkonury.www.user.service.UserService;

@Component
public class LoginValidator implements Validator{
	@Autowired
	UserService userService;
	
	@Inject
	Provider<LoginInfo> loginProvider;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User loginUser = (User)target;
		
		User user = userService.findUser(loginUser.getId());
		
		if(user == null || !user.getPasswd().equals(loginUser.getPasswd())) {
			errors.reject("invalidLogin", "invalidLogin");
		} else {
			LoginInfo loginInfo = loginProvider.get();
			loginInfo.save(user);
		}
	}
}