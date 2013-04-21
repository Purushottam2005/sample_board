package com.jkonury.www.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkonury.www.board.dao.BoardReplyDao;
import com.jkonury.www.board.model.BoardReply;
import com.jkonury.www.board.service.BoardReplyService;

@Service
public class BoardReplyServiceImpl implements BoardReplyService {
	@Autowired
	BoardReplyDao boardReplyDao;

	@Override
	public int add(BoardReply boardReply) {

		return boardReplyDao.add(boardReply);
	}

	@Override
	public BoardReply get(int id) {

		return boardReplyDao.get(id);
	}

	@Override
	public void delete(BoardReply boardReply) {
		boardReplyDao.delete(boardReply);
	}

	@Override
	public void deleteById(int id) {
		boardReplyDao.deleteById(id);
	}

	@Override
	public List<BoardReply> getAll(BoardReply boardReply) {

		return boardReplyDao.getAll(boardReply);
	}

	@Override
	public List<BoardReply> search(BoardReply boardReply) {

		return boardReplyDao.search(boardReply);
	}

}
