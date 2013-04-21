package com.jkonury.www.board.dao;

import com.jkonury.www.board.model.Board;
import com.jkonury.www.common.dao.GenericDao;


public interface BoardDao extends GenericDao<Board>{
	int getCount(Board board);
}
