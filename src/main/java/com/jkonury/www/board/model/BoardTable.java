package com.jkonury.www.board.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.jkonury.www.common.model.Common;

@Data
@EqualsAndHashCode(callSuper = false)
public class BoardTable extends Common{
	private int tableSeq; 
	private String boardId; 
	private String boardName; 
	private String boardDesc; 
	private String boardTheme; 
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}	
}
