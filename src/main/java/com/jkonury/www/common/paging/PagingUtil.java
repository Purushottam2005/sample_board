package com.jkonury.www.common.paging;

import lombok.Data;

@Data
public class PagingUtil {
	private int startPage;
	private int totalPage;
	private int currentPage;
	
	private final int numPerPage = 30;
	private final int numPageBlock = 15;
	
	// *******************************************************************************
    // 페이지 수 구하기
    // *******************************************************************************
	public int getPageCount(int numPerPage, int dataCount) {
		int pageCount = 0;
		int remainPage = 0;
		 // 총 페이지 수를 구하기 위한 나머지 계산
		remainPage = dataCount % numPerPage;
		if(remainPage == 0){
			pageCount = dataCount / numPerPage;
		}
		else{
			pageCount = dataCount / numPerPage + 1;
		}
		return pageCount;
	}
	
	// 자바 스크립트(listPage 함수)에 의한 페이지 처리 메서드 ***********************
    public String pageIndexList(int current_page, int total_page) {
        int currentPageSetUp;
        int n;
        int page;
        String strList="";
        
        if(current_page == 0)
        	return "";

        // 표시할 첫 페이지
        currentPageSetUp = (current_page / numPageBlock) * numPageBlock;
        if (current_page % numPageBlock == 0)
            currentPageSetUp = currentPageSetUp - numPageBlock;

        // 1 페이지
        if ((total_page > numPageBlock) && (currentPageSetUp > 0)) {
            strList = "<input onclick='setPage(1)' type='button' class='button' value='&lt&lt' title='처음' />&nbsp;";
        }

        // [Prev] : 총 페이지수가 numPerBlock 이상인 경우 이전 numPerBlock 보여줌
        n = current_page - 1;
        if (total_page > numPageBlock && currentPageSetUp > 0) {
            strList = strList + "<input type='button' class='button' value='&lt' title='이전' onclick='setPage("+n+")' />&nbsp;";
        }

        // 바로가기 페이지 구현
        page = currentPageSetUp + 1;
        strList = strList + "<span>";
        while((page <= total_page) && (page <= currentPageSetUp + numPageBlock)) {
           if(page == current_page) {
               strList = strList + "<a href='#' class='on'>"+page+"</a>";
           }
           else {
               strList = strList +"<a href='#' onclick='setPage("+page+")'>"+page+"</a>";
           }
           page++;
        }
        strList = strList +"</span>";
        
        // [Next] : 총 페이지수가 numPerBlock 페이지 이상인 경우 다음 numPerBlock 페이지를 보여줌
        // n = currentPageSetUp + numPerBlock + 1;
        n = current_page + 1;
        if (total_page - currentPageSetUp > numPageBlock) {
            strList = strList + "&nbsp;<input type='button' class='button' value='>' title='다음' onclick='setPage("+n+")' />";
        }

        // 마지막 페이지
        if ((total_page > numPageBlock) && (currentPageSetUp + numPageBlock < total_page)) {
            strList = strList + "&nbsp;<input type='button' class='button' value='>>' title='마지막' onclick='setPage("+total_page+")' />";
        }
        return strList;
    }
}
