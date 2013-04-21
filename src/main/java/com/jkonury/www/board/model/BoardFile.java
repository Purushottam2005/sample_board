package com.jkonury.www.board.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.jkonury.www.common.model.Common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BoardFile extends Common{
	private int fileSeq; 
	private int boardSeq; 
	private int userSeq; 
	private String filePath; 
	private String orgFileName; 
	private String copyFileName; 
	private int fileSize; 
	private String fileExt; 
	private int downCount;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
