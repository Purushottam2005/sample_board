package com.jkonury.www.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;

import com.jkonury.www.test.AbstractDispatcherServletTest;

public class ControllerTest extends AbstractDispatcherServletTest{
//	@Test
	public void simpleHandler() throws ServletException, IOException {
//		this.setClasses(SampleController.class)
//			.runService("/test/req");
		
//		assertThat(this.response.getContentAsString(), is("hi"));
		this.setClasses(SampleController.class)
		.initRequest("/test/form", "POST")
		.addParameter("str", "testStr")
		.addParameter("str2", "testSTr2")
		.runService();
		assertThat(this.getModelAndView().getViewName(), is("test/form"));
		System.out.println(runService("/test/page").getContentAsString());
	}
	
	@Test 
	public void xml() throws ServletException, IOException{
		this.setLocations(
				"test-context.xml")
				.runService("/test/req");
	}
	

}
