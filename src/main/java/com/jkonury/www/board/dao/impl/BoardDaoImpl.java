package com.jkonury.www.board.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jkonury.www.board.dao.BoardDao;
import com.jkonury.www.board.model.Board;
import com.jkonury.www.common.dao.BaseDao;

@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	BaseDao baseDao;
	
	@Override
	public int add(Board board) {
		return baseDao.insert("board.add", board);
	}

	@Override
	public Board get(int id) {

		return baseDao.queryForObject("board.get", id);
	}

	@Override
	public void update(Board board) {
		baseDao.update("board.update", board);
	}

	@Override
	public void delete(Board board) {
		baseDao.update("board.delete", board);
	}

	@Override
	public void deleteById(int id) {
		baseDao.update("board.deleteById", id);
	}

	@Override
	public List<Board> getAll(Board board) {
		return baseDao.queryForList("board.getAll", board);
	}

	@Override
	public List<Board> search(Board board) {
		return baseDao.queryForList("board.search", board);
	}

	@Override
	public void deleteAll() {
		baseDao.delete("board.deleteAll");
	}

	@Override
	public int getCount() {
		return baseDao.queryForObject("board.getCount");
	}

	@Override
	public int getCount(Board board) {
		return baseDao.queryForObject("board.getCount", board);
	}

}
