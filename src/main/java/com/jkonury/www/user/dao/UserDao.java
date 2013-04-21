package com.jkonury.www.user.dao;

import com.jkonury.www.common.dao.GenericDao;
import com.jkonury.www.user.model.User;

public interface UserDao extends GenericDao<User>{
	User findUser(String id);
}
