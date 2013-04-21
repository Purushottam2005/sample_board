package com.jkonury.www.common.validator;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jkonury.www.common.security.LoginInfo;
import com.jkonury.www.user.model.User;

@Component
public class UserValidator implements Validator {

	@Inject
	private Provider<LoginInfo> loginInfoProvider;
	
	@Override
	public boolean supports(Class<?> clazz) {

		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User formUser = (User)target;
		
		if(!formUser.getPasswd().equals(formUser.getPasswdCheck())) {
			errors.rejectValue("passwdCheck", "passwdNotEqual");
		} else {
			LoginInfo loginInfo = loginInfoProvider.get();
			loginInfo.save(formUser);
		}
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, errorCode)
	}
}
