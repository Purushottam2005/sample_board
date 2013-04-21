package com.jkonury.www.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkonury.www.user.dao.UserDao;
import com.jkonury.www.user.model.User;
import com.jkonury.www.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	
	@Override
	public User get(int id) {

		return userDao.get(id);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public void deleteById(int id) {
		userDao.deleteById(id);
	}

	@Override
	public List<User> getAll(User user) {

		return userDao.getAll(user);
	}

	@Override
	public List<User> search(User user) {

		return userDao.search(user);
	}

	@Override
	public User findUser(String id) {
		return userDao.findUser(id);
	}

	@Override
	public int add(User user) {
		return userDao.add(user);
	}

	@Override
	public void login(User user) {
		user.login();
		userDao.update(user);
	}

}
