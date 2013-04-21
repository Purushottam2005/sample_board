package com.jkonury.www.board.dao;

import com.jkonury.www.board.model.BoardTable;
import com.jkonury.www.common.dao.GenericDao;

public interface BoardTableDao extends GenericDao<BoardTable>{
	BoardTable findByBoardId(String boardId);
}
