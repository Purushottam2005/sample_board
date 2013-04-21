package com.jkonury.www.user.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jkonury.www.common.dao.BaseDao;
import com.jkonury.www.user.dao.UserDao;
import com.jkonury.www.user.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	BaseDao baseDao;
	
	@Override
	public int add(User user) {

		return baseDao.insert("user.add", user);
	}

	@Override
	public User get(int id) {

		return baseDao.queryForObject("user.get", id);
	}

	@Override
	public void update(User user) {
		baseDao.update("user.update", user);
	}

	@Override
	public void delete(User user) {
		baseDao.update("user.delete", user);
	}

	@Override
	public void deleteById(int id) {
		baseDao.update("user.deleteById", id);
	}

	@Override
	public List<User> getAll(User user) {
		
		return baseDao.queryForList("user.getAll", user);
	}

	@Override
	public List<User> search(User user) {

		return baseDao.queryForList("user.search", user);
	}

	@Override
	public void deleteAll() {
		baseDao.delete("user.deleteAll");
	}

	@Override
	public int getCount() {
		return baseDao.queryForObject("user.getCount");
	}

	@Override
	public User findUser(String id) {
		return baseDao.queryForObject("user.findUser", id);
	}
}
