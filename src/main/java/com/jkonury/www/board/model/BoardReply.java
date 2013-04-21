package com.jkonury.www.board.model;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.jkonury.www.common.model.Common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BoardReply extends Common{
	private int replySeq; 
	private int boardSeq; 
	private int userSeq;
	private String userName;
	
	
	@Size(min=10, max=200, message="")
	private String content; 
	private int recommend; 
	private String ip;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
