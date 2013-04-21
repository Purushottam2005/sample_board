package com.jkonury.www.board.service;

import com.jkonury.www.board.model.Board;
import com.jkonury.www.common.dao.GenericDao;
import com.jkonury.www.common.service.GenericService;


public interface BoardService extends GenericService<Board, GenericDao<Board>>{
	int getCount(Board board);
	
	void update(Board board);
	
	
}
