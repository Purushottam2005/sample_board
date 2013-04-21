package com.jkonury.www.user.service;

import com.jkonury.www.common.dao.GenericDao;
import com.jkonury.www.common.service.GenericService;
import com.jkonury.www.user.model.User;

public interface UserService extends GenericService<User, GenericDao<User>>{
	User findUser(String id);
	void login(User user);
}
