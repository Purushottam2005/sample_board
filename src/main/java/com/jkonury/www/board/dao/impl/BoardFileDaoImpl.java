package com.jkonury.www.board.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jkonury.www.board.dao.BoardFileDao;
import com.jkonury.www.board.model.BoardFile;
import com.jkonury.www.common.dao.BaseDao;

@Repository
public class BoardFileDaoImpl implements BoardFileDao {
	@Autowired
	BaseDao baseDao;

	@Override
	public int add(BoardFile boardFile) {
		return baseDao.insert("board_file.add", boardFile);
	}

	@Override
	public BoardFile get(int id) {
		return baseDao.queryForObject("board_file.get", id);
	}

	@Override
	public void update(BoardFile boardFile) {
	}

	@Override
	public void delete(BoardFile boardFile) {
		baseDao.update("board_file.delete", boardFile);
	}

	@Override
	public void deleteById(int id) {
		baseDao.update("board_file.deleteById", id);
	}

	@Override
	public List<BoardFile> getAll(BoardFile boardFile) {

		return null;
	}

	@Override
	public List<BoardFile> search(BoardFile boardFile) {

		return null;
	}

	@Override
	public void deleteAll() {
		baseDao.delete("board_file.deleteAll");
	}

	@Override
	public int getCount() {

		return baseDao.queryForObject("board_file.getCount");
	}

}
