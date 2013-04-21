package com.jkonury.www.board.service;

import com.jkonury.www.board.model.BoardTable;
import com.jkonury.www.common.dao.GenericDao;
import com.jkonury.www.common.service.GenericService;

public interface BoardTableService extends GenericService<BoardTable, GenericDao<BoardTable>>{
	BoardTable findByBoardId(String boardId);
	
}
