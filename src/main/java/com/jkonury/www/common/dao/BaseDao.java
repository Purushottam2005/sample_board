package com.jkonury.www.common.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.event.RowHandler;

/**
 * BaseDao를 빈으로 등록하여 아이바티스 주로 쓰는 메소드를 제네릭 기반으로 
 * 변경하여 SqlMapClientDaoSupport를 상속하지 않고 
 * Autowired로 DI 받아 사용할 수 있도록 함  
 * @author jjh
 * @since 2012.11.28
 * @version 1.0
 */
@Repository
public class BaseDao extends SqlMapClientDaoSupport{
	
	@Autowired
	public void setSqlMapC(SqlMapClient sqlMapClient){
		this.setSqlMapClient(sqlMapClient);
	}
	
	public <T> T queryForObject(final String statementName){
		return queryForObject(statementName, null);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T queryForObject(final String statementName, final Object parameterObject){
		return (T) getSqlMapClientTemplate().queryForObject(statementName, parameterObject);
	}
	
	public <T> List<T> queryForList(final String statementName){
		return queryForList(statementName, null);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> queryForList(final String statementName, final Object parameterObject){
		return getSqlMapClientTemplate().queryForList(statementName, parameterObject);
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> List<T> queryForList(String statementName, int skipResults, int maxResults) {

		return getSqlMapClientTemplate().queryForList(statementName, skipResults, maxResults);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> queryForList(final String statementName, final Object parameterObject, final int skipResults, final int maxResults) {
		return getSqlMapClientTemplate().queryForList(statementName, parameterObject, skipResults, maxResults);
	}
	
	public <T> T insert(String statementName){
		return insert(statementName);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T  insert(final String statementName, final Object obj){
		return (T) getSqlMapClientTemplate().insert(statementName, obj);
	}
	
	public int update(final String statementName){
		return update(statementName, null);
	}
	
	public int update(final String statementName, final Object parameterObject){
		return getSqlMapClientTemplate().update(statementName, parameterObject);
	}
	
	public void update(String statementName, Object parameterObject, int requiredRowsAffected) {
		
		getSqlMapClientTemplate().update(statementName, parameterObject, requiredRowsAffected);
	}


	public int delete(String statementName){
		return delete(statementName, null);
	}
	
	public int delete(final String statementName, final Object parameterObject){
		return getSqlMapClientTemplate().delete(statementName, parameterObject);
	}
	
	public void delete(String statementName, Object parameterObject, int requiredRowsAffected) {
		getSqlMapClientTemplate().delete(statementName, parameterObject, requiredRowsAffected);
	}
	
	public <T> T execute(SqlMapClientCallback<T> action) throws DataAccessException {
		return getSqlMapClientTemplate().execute(action);
	}

	@SuppressWarnings("unchecked")
	public <T> T queryForObject(final String statementName, final Object parameterObject, final Object resultObject){

		return (T) getSqlMapClientTemplate().queryForObject(statementName, parameterObject, resultObject);
		
	}

	public void queryWithRowHandler(String statementName, RowHandler rowHandler) {

		getSqlMapClientTemplate().queryWithRowHandler(statementName, null, rowHandler);
	}

	public void queryWithRowHandler(final String statementName, final Object parameterObject, final RowHandler rowHandler){

		getSqlMapClientTemplate().queryWithRowHandler(statementName, parameterObject, rowHandler);
	}


	
}