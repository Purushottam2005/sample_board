package com.jkonury.www.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jkonury.www.board.model.BoardFile;
import com.jkonury.www.common.dao.GenericDao;
import com.jkonury.www.common.service.GenericService;

public interface BoardFileService extends GenericService<BoardFile, GenericDao<BoardFile>>{
	public void downloadBoardFile(HttpServletRequest request, HttpServletResponse response, int boardSeq, String fileName) throws Exception;
}
