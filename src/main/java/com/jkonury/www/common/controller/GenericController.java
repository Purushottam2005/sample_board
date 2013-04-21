package com.jkonury.www.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.jkonury.www.common.dao.GenericDao;
import com.jkonury.www.common.service.GenericService;

/**
 * 
 *  @param <T> Entity class type
 *  @param <S> BaseService class type
 *  @param <V> Validator class type.(This is org.springframework.validation.Validator implementation)
 */

public abstract class GenericController<T,S extends GenericService<T, ? extends GenericDao<T>>, V extends Validator> {
	protected Logger logger = LoggerFactory.getLogger(GenericController.class);
	
//	protected T entity;
//	protected S service;
//	protected R ref;
//	protected V validator;
	
//	@RequestMapping(method = RequestMethod.GET)
//	public abstract void add();
//	
//	@RequestMapping(method = RequestMethod.POST)
//	public abstract String add(T entity, BindingResult result, SessionStatus status);
//	
//	@RequestMapping(method = RequestMethod.GET)
//	public abstract void update();
//	
//	@RequestMapping(method = RequestMethod.POST)
//	public abstract String update(T entity, BindingResult result, SessionStatus status);
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public abstract void add(ModelMap model);
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public abstract String add(T entity, BindingResult result, SessionStatus status);
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public abstract void update(ModelMap model);
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public abstract String update(T entity, BindingResult result, SessionStatus status);
}
