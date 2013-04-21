package com.jkonury.www.board.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jkonury.www.board.dao.BoardTableDao;
import com.jkonury.www.board.model.BoardTable;
import com.jkonury.www.common.dao.BaseDao;

@Repository
public class BoardTableDaoImpl implements BoardTableDao {
	@Autowired
	BaseDao baseDao;

	@Override
	public int add(BoardTable boardTable) {

		return baseDao.queryForObject("board_table.add", boardTable);
	}

	@Override
	public BoardTable get(int id) {

		return baseDao.queryForObject("board_table.get", id);
	}

	@Override
	public void update(BoardTable boardTable) {
		baseDao.update("board_table.update", boardTable);
	}

	@Override
	public void delete(BoardTable boardTable) {
		baseDao.update("board_table.delete", boardTable);
	}

	@Override
	public void deleteById(int id) {
		baseDao.update("board_table.deleteById", id);
	}

	@Override
	public List<BoardTable> getAll(BoardTable boardTable) {

		return baseDao.queryForList("board_table.getAll");
	}

	@Override
	public List<BoardTable> search(BoardTable boardTable) {

		return baseDao.queryForList("board_table.search");
	}

	@Override
	public void deleteAll() {
		baseDao.delete("board_table.deleteAll");
	}

	@Override
	public int getCount() {

		return baseDao.queryForObject("board_table.getCount");
	}

	@Override
	public BoardTable findByBoardId(String boardId) {
		return baseDao.queryForObject("board_table.findByBoardId", boardId);
	}

}
