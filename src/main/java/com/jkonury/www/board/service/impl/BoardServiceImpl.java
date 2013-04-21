package com.jkonury.www.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkonury.www.board.dao.BoardDao;
import com.jkonury.www.board.model.Board;
import com.jkonury.www.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;

	@Override
	public Board get(int id) {

		return boardDao.get(id);
	}

	@Override
	public void delete(Board board) {
		boardDao.delete(board);
	}

	@Override
	public void deleteById(int id) {
		boardDao.deleteById(id);
	}

	@Override
	public List<Board> getAll(Board board) {

		return boardDao.getAll(board);
	}

	@Override
	public List<Board> search(Board board) {

		return boardDao.search(board);
	}

	@Override
	public int add(Board board) {
		return boardDao.add(board);
	}

	@Override
	public int getCount(Board board) {
		return boardDao.getCount(board);
	}

	@Override
	public void update(Board board) {
		boardDao.update(board);
	}
}
