package com.jkonury.www.common.service;

import java.util.List;

import com.jkonury.www.common.dao.GenericDao;

/**
 * 
 * @author Administrator
 *
 * @param <T> Entity class type
 * @param <D> GenericDao class type
 */
public interface GenericService<T, D extends GenericDao<T>> {
	int add(T entity);
	
	T get(int id);

	void delete(T entity);

	void deleteById(int id);

	List<T> getAll(T params);

	List<T> search(T params);
}
