package com.jkonury.www.board.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jkonury.www.board.dao.BoardReplyDao;
import com.jkonury.www.board.model.BoardReply;
import com.jkonury.www.common.dao.BaseDao;

@Repository
public class BoardReplyDaoImpl implements BoardReplyDao {
	@Autowired
	BaseDao baseDao;

	@Override
	public int add(BoardReply boardReply) {

		return baseDao.insert("board_reply.add", boardReply);
	}

	@Override
	public BoardReply get(int id) {

		return baseDao.queryForObject("board_reply.get", id);
	}

	@Override
	public void update(BoardReply boardReply) {
	}

	@Override
	public void delete(BoardReply boardReply) {
		baseDao.update("board_reply.delete", boardReply);
	}

	@Override
	public void deleteById(int id) {
		baseDao.update("board_reply.deleteById", id);
	}

	@Override
	public List<BoardReply> getAll(BoardReply boardReply) {

		return baseDao.queryForList("board_reply.getAll", boardReply);
	}

	@Override
	public List<BoardReply> search(BoardReply boardReply) {

		return baseDao.queryForList("board_reply.search", boardReply);
	}

	@Override
	public void deleteAll() {
		baseDao.delete("board_reply.deleteAll");
	}

	@Override
	public int getCount() {

		return baseDao.queryForObject("board_reply.getCount");
	}

}
