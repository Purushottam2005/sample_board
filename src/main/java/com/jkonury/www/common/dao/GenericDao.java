package com.jkonury.www.common.dao;

import java.util.List;

/**
 * Generic Dao Interface
 * @author JJH
 *
 * @param <T> Domain Class
 */
public interface GenericDao<T> {
	int add(T entity);
	
	T get(int id);
	
	void update(T entity);
	
	void delete(T entity);
	
	void deleteById(int id);
	
	List<T> getAll(T entity);
	
	List<T> search(T entity);
	
	void deleteAll();
	
	int getCount();
}
