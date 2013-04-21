package com.jkonury.www.common.paging;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Paging {

	private int currentPageNo = 1;             // 현재 페이지
	private int recordCountPerPage = 10;       // 한 페이지에 보여주는 글 목록 개수
	private int pageSize = 10;                 // 한 화면에 보여주는 페이지 숫자
	private int totalRecordCount = 0;          // 전체 글 목록 개수
	private int totalPageCount = 0;            // 전체 페이지 개수
	private int firstPageNoOnPageList = 0;     // 한 화면에 보여주는 첫 페이지 숫자
	private int lastPageNoOnPageList = 0;      // 한 화면에 보여주는 마지막 페이지 숫자 
	private int firstRecordIndex = 0;          // 첫 번째 글 목록 숫자
	private int lastRecordIndex = 0;           // 마지막 글 목록 숫자
                                               
	private String searchWord = "";			// 검색 단어
	private String searchType = "";			// 검색 조건

	public int getTotalPageCount() {
		this.totalPageCount = ((this.totalRecordCount - 1) / this.recordCountPerPage) + 1;
		return this.totalPageCount;
	}

	public int getFirstPageNoOnPageList() {
		this.firstPageNoOnPageList = ((this.currentPageNo - 1) / this.pageSize) * this.pageSize + 1;
		return this.firstPageNoOnPageList;
	}

	public int getLastPageNoOnPageList() {
		this.lastPageNoOnPageList = this.firstPageNoOnPageList + this.pageSize - 1;
		if (this.lastPageNoOnPageList > this.totalPageCount) {
			this.lastPageNoOnPageList = this.totalPageCount;
		}
		return this.lastPageNoOnPageList;

	}

	public int getFirstRecordIndex() {
		this.firstRecordIndex = (this.currentPageNo - 1) * this.recordCountPerPage + 1;
		return this.firstRecordIndex;
	}

	public int getLastRecordIndex() {
		this.lastRecordIndex = this.currentPageNo * this.recordCountPerPage;
		return this.lastRecordIndex;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public void setFirstPageNoOnPageList(int firstPageNoOnPageList) {
		this.firstPageNoOnPageList = firstPageNoOnPageList;
	}

	public void setLastPageNoOnPageList(int lastPageNoOnPageList) {
		this.lastPageNoOnPageList = lastPageNoOnPageList;
	}

	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}

	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}