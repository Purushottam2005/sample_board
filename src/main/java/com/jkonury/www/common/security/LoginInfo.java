package com.jkonury.www.common.security;

import java.util.Date;

import com.jkonury.www.user.model.User;

public interface LoginInfo {
	void save(User user);
	void remove();
	boolean isLoggedIn();
	User currentUser();
	Date getLoginTime();
}
