package com.jkonury.www.board.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jkonury.www.board.model.Board;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"	
})
public class BoardDaoTest {
	@Autowired
	BoardDao boardDao;
	
//	@Test
	public void add() {
		Board board = new Board();
		
		board.setContent("내용");
		board.setUserSeq(1);
		board.setTitle("제목");

		boardDao.add(board);
	}
	
//	@Test
	public void getAll() {
		Board board = new Board();
		
		board.setTableSeq(1);
		board.setStartPage(0);
		board.setEndPage(15);
		
		List<Board> boards = boardDao.getAll(null);
		
		System.out.println(boards);
	}
	
	@Test
	public void get() {
		Board board = boardDao.get(1);
		
		System.out.println(board);
	}
	
//	@Test
	public void update() {
		Board board = boardDao.get(1);
		
		board.setTitle("수정");
		board.setContent("내용 수정");
		
		boardDao.update(board);
	}
	
//	@Test
	public void search() {
		List<Board> boards = boardDao.search(null);
		
		System.out.println(boards);
	}
}
