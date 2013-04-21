package com.jkonury.www.board.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

import com.jkonury.www.common.model.Common;

@Data
@EqualsAndHashCode(callSuper = false)
public class Board extends Common {
	private int boardSeq;
	private int tableSeq;
	private int userSeq;
	private String title;
	private String content;
	private String passwd;
	private String userName;
	private int inquiryCount;
	private String hasFile;
	private String ip;
	private int replyCount;
	

	private MultipartFile file;
	
	public void inquiryCountAdd() {
		inquiryCount++;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
