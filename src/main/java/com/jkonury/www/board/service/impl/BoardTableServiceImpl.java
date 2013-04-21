package com.jkonury.www.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkonury.www.board.dao.BoardTableDao;
import com.jkonury.www.board.model.BoardTable;
import com.jkonury.www.board.service.BoardTableService;

@Service
public class BoardTableServiceImpl implements BoardTableService {
	@Autowired
	BoardTableDao boardTableDao;
	
	@Override
	public int add(BoardTable boardTable ) {

		return boardTableDao.add(boardTable );
	}

	@Override
	public BoardTable get(int id) {

		return boardTableDao.get(id);
	}

	@Override
	public void delete(BoardTable boardTable ) {
		boardTableDao.delete(boardTable );
	}

	@Override
	public void deleteById(int id) {
		boardTableDao.deleteById(id);
	}

	@Override
	public List<BoardTable> getAll(BoardTable boardTable ) {

		return boardTableDao.getAll(boardTable );
	}

	@Override
	public List<BoardTable> search(BoardTable boardTable ) {

		return boardTableDao.search(boardTable );
	}

	@Override
	public BoardTable findByBoardId(String boardId) {
		return boardTableDao.findByBoardId(boardId);
	}

}
