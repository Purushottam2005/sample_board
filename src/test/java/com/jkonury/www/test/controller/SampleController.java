package com.jkonury.www.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jkonury.www.board.model.Board;
import com.jkonury.www.test.model.TestVO;
import com.jkonury.www.test.model.TestVO2;

@Controller
@RequestMapping("/test")
public class SampleController {
	
	@RequestMapping(value="/form", method = RequestMethod.GET)
	public void form() {
		
	}
	
	@RequestMapping(value="/form", method = RequestMethod.POST)
	public String form(TestVO str, TestVO2 str2) {
		
		System.out.println("str : " + str);
		System.out.println("str2 :" + str2);
		
		return "redirect:form";
	}
	
	@RequestMapping("/page")
	public void page(ModelMap model) {
		Board board = new Board();
		
		model.addAttribute("pageVO", board);
	}
	
	@RequestMapping("req")
	public void req(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getSession().getServletContext().getRealPath("/"));		
		
	}
	
	
}
