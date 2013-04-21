package com.jkonury.www.board.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jkonury.www.board.model.BoardReply;

@Component
public class BoardReplyValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return BoardReply.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("**************************BoardReplyValidator**************************");
		System.out.println(target);
		System.out.println("**************************BoardReplyValidator**************************");
	}
}
