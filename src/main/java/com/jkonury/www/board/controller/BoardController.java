package com.jkonury.www.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.jkonury.www.board.model.Board;
import com.jkonury.www.board.model.BoardFile;
import com.jkonury.www.board.model.BoardReply;
import com.jkonury.www.board.model.BoardTable;
import com.jkonury.www.board.service.BoardFileService;
import com.jkonury.www.board.service.BoardReplyService;
import com.jkonury.www.board.service.BoardService;
import com.jkonury.www.board.service.BoardTableService;
import com.jkonury.www.board.validator.BoardReplyValidator;
import com.jkonury.www.board.validator.BoardValidator;
import com.jkonury.www.common.paging.PageUtil;
import com.jkonury.www.common.util.FileUtil;
import com.jkonury.www.user.model.User;

@Controller
@RequestMapping("/board")
@SessionAttributes("board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardFileService boardFileService;
	
	@Autowired
	private BoardReplyService boardReplyService;
	
	@Autowired
	private BoardTableService boardTableService;
	
	@Autowired
	private BoardValidator boardValidator;
	
	@Autowired
	private BoardReplyValidator boardReplyValidator;
	
	@RequestMapping(value="/{boardId}/add", method=RequestMethod.GET)
	public String add(@PathVariable("boardId") String boardId, ModelMap model) {
		List<BoardTable> boardTables = boardTableService.getAll(null);
		
		model.addAttribute("board", new Board());
		model.addAttribute("boardTables", boardTables);
		return "board/add";
	}

	@RequestMapping(value="/{boardId}/add", method=RequestMethod.POST)
	public String add(@PathVariable("boardId") String boardId, @ModelAttribute("board") Board board, BindingResult result, SessionStatus status, HttpServletRequest request, BoardFile boardFile) {
		boardValidator.validate(board, result);
		
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
                System.out.println(error.getCode() + " : " + error.getDefaultMessage());
            }
			return "board/add";
		} else {
				
			if(GenericValidator.isBlankOrNull(board.getPasswd())) {
				board.setPasswd(null);
			}
			
			User user = (User) request.getSession().getAttribute("loginInfo");
			BoardTable boardTable = boardTableService.findByBoardId(boardId);
			
			board.setUserSeq(user.getUserSeq());
			board.setTableSeq(boardTable.getTableSeq());
			
			if (board.getFile() != null && board.getFile().getOriginalFilename().trim().length() != 0) {
				board.setHasFile("Y");
			} else {
				board.setHasFile("N");
			}
			
			int boardSeq = boardService.add(board);
			
			board.setBoardSeq(boardSeq);
			
			FileUtil.fileUpload(request, board, boardFile);
			
			boardFileService.add(boardFile);
			
			status.setComplete();
			return "redirect:list/1";
		}
	}

	public void update(ModelMap model) {

	}

	public String update(@ModelAttribute("board") Board board, BindingResult result, SessionStatus status) {
		boardValidator.validate(board, result);
		
		if(result.hasErrors()) {
			return "board/update";
		} else {
			boardService.update(board);
			status.setComplete();
			return "redirect:list/1";
		}
	}
	
	@RequestMapping(value = "/{boardId}/update/{boardSeq}", method = RequestMethod.GET)
	public void update(@PathVariable String boardId, @PathVariable int boardSeq, @ModelAttribute("board") Board board) {
		
	}
	
	@RequestMapping(value = "/{boardId}/update/{boardSeq}", method = RequestMethod.POST)
	public String update(@PathVariable String boardId, @PathVariable int boardSeq, @ModelAttribute("board") Board board, BindingResult result, SessionStatus status) {
		boardValidator.validate(board, result);
		
		if(result.hasErrors()) {
			return "board/update";
		} else {
//			boardService.
			
			status.setComplete();
			return "redirect:/board" + boardId + "/list/1";
		}
	}
	
//	@RequestMapping("/view")
//	public void view(int boardSeq, ModelMap model) {
//		Board board = boardService.get(boardSeq);
//		
//		model.addAttribute("board", board);
//	}
	
	@RequestMapping(value = "/{boardId}/view/{boardSeq}")
	public String view(@PathVariable String boardId, @PathVariable int boardSeq, ModelMap model) {

		List<BoardTable> boardTables = boardTableService.getAll(null);
		Board board = boardService.get(boardSeq);
		
		BoardReply boardReply = new BoardReply();
		boardReply.setBoardSeq(boardSeq);
		List<BoardReply> boardReplies = boardReplyService.getAll(boardReply);
		
		BoardFile boardFile = null;
		if( board.getHasFile().equals("Y")) {
			boardFile = boardFileService.get(boardSeq);
		}
		
		board.inquiryCountAdd();
		
		boardService.update(board);
		
		model.addAttribute("board", board);
		model.addAttribute("boardTables", boardTables);
		model.addAttribute("boardReplies", boardReplies);
		model.addAttribute("boardFile", boardFile);
		model.addAttribute("boardId", boardId);
		model.addAttribute("boardSeq", boardSeq);
		
		model.addAttribute("boardReply", new BoardReply());
		
		System.out.println(boardReplies);
		
		return "board/view";
	}
	
	
	@RequestMapping("/list")
	public void list(ModelMap model) {
		List<BoardTable> boardTables = boardTableService.getAll(null);
		
		model.addAttribute("boardTables", boardTables);
	}
	
	@RequestMapping("/{boardId}/list/{curPage}")
	public String list(Board board, @PathVariable String boardId, @PathVariable int curPage, PageUtil page,  ModelMap model) {
		BoardTable boardTable = boardTableService.findByBoardId(boardId);
		
		board.setTableSeq(boardTable.getTableSeq());
		
		/**
		 * 페이징 처리 부분 
		 */
		int boardTotalCount = boardService.getCount(board);
		
		page.setPage(curPage);
		page.init(boardTotalCount);
		
		board.setStartPage( (curPage - 1) * PageUtil.PAGE_SIZE);
		board.setEndPage( PageUtil.PAGE_SIZE );
		
//		System.out.println(page);
		
		List<Board> boardList = boardService.getAll(board);
		List<BoardTable> boardTables = boardTableService.getAll(null);
		
		model.addAttribute("boardTables", boardTables);
		model.addAttribute("page", page);
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardId", boardId);
		
		return "board/list";
	}
	
	@RequestMapping("/{boardId}/search/{curPage}")
	public String search(Board board, @PathVariable String boardId, @PathVariable int curPage, PageUtil page,  ModelMap model) {
		BoardTable boardTable = boardTableService.findByBoardId(boardId);
		
		board.setTableSeq(boardTable.getTableSeq());
		
		int boardTotalCount = boardService.getCount(board);
		
		page.setPage(curPage);
		page.init(boardTotalCount);
		
		board.setStartPage( (curPage - 1) * PageUtil.PAGE_SIZE);
		board.setEndPage( PageUtil.PAGE_SIZE );

		List<Board> boardList = boardService.search(board);
		List<BoardTable> boardTables = boardTableService.getAll(null);
		
		model.addAttribute("boardTables", boardTables);
		model.addAttribute("page", page);
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardId", boardId);
		
		return "board/search";
	}
	
	@RequestMapping("downloadFile")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, int boardSeq, String fileName) throws Exception {
		try {
			System.out.println(fileName);
			
//			String us2FileName = urldecode(fileName).trim();
			
			boardFileService.downloadBoardFile(request, response, boardSeq, fileName);
//			boardFileService.downloadBoardFile(request, response, boardSeq, us2FileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 댓글 달기
	 * @param boardReply
	 * @param result
	 * @param status
	 * @param boardId
	 * @param boardSeq
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{boardId}/reply/{boardSeq}", method = RequestMethod.POST)
	public String reply(@ModelAttribute @Valid BoardReply boardReply, BindingResult result, SessionStatus status, @PathVariable String boardId, @PathVariable int boardSeq, HttpServletRequest request) {
		boardReplyValidator.validate(boardReply, result);
		
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println("===============Validator Error=======================");
                System.out.println(error.getCode() + " : " + error.getDefaultMessage());
                System.out.println("=================================================================");
            }
			return "redirect:/board/" + boardId + "/view/" + boardSeq;
		} else {
			boardReply.setIp(request.getRemoteAddr());
			
			boardReplyService.add(boardReply);
			status.setComplete();
			return "redirect:/board/" + boardId + "/view/" + boardSeq;
		}
		
	}
	
	public static String urldecode( String str ){
		if( str == null || str.equals("") ){
			return str;
		}

		try{
			byte[] tmp = str.getBytes("iso-8859-1");
			str = new String( tmp , "utf-8" );
			str = java.net.URLDecoder.decode( str, "utf-8" );
		}
		catch( java.io.UnsupportedEncodingException e ){}
		return str;
	}
}
