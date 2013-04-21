package com.jkonury.www.board.service.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkonury.www.board.dao.BoardFileDao;
import com.jkonury.www.board.model.BoardFile;
import com.jkonury.www.board.service.BoardFileService;
import com.jkonury.www.common.util.FileUtil;

@Service
public class BoardFileServiceImpl implements BoardFileService{
	@Autowired
	BoardFileDao boardFileDao;

	@Override
	public int add(BoardFile boardFile) {

		return boardFileDao.add(boardFile);
	}

	@Override
	public BoardFile get(int id) {

		return boardFileDao.get(id);
	}

	@Override
	public void delete(BoardFile boardFile) {
		boardFileDao.delete(boardFile);
	}

	@Override
	public void deleteById(int id) {
		boardFileDao.deleteById(id);
	}

	@Override
	public List<BoardFile> getAll(BoardFile boardFile) {

		return boardFileDao.getAll(boardFile);
	}

	@Override
	public List<BoardFile> search(BoardFile boardFile) {

		return boardFileDao.search(boardFile);
	}
	
	@Override
	public void downloadBoardFile(HttpServletRequest request, HttpServletResponse response, int boardSeq, String fileName) throws Exception {
		BoardFile boardFile = boardFileDao.get(boardSeq);
		String filePath = boardFile.getFilePath() + File.separator;
		String copyFileName = boardFile.getCopyFileName();
		FileUtil.filDown(request, response, filePath, copyFileName, fileName);
	}
}
