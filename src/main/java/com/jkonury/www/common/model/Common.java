package com.jkonury.www.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
public class Common {
	private String rmYn;
	private String regDate;
	private String rmDate;
	
	private String searchWord;
	private String searchType;
	
	private String pageNum;
	
	private int startPage;
	private int endPage;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
