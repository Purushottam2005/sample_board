/**
 * pagination
 * @author heejoongs
 * @version 0.1
 */
public class Paging {

	private int currentPageNo = 1;
	private int recordCountPerPage = 10;
	private int pageSize = 10;
	private int totalRecordCount = 0;
	private int totalPageCount = 0;
	private int firstPageNoOnPageList = 0;
	private int lastPageNoOnPageList = 0;
	private int firstRecordIndex = 0;
	private int lastRecordIndex = 0;
	
	private String searchWord = "";
	private String searchType = "";
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
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
		if(this.lastPageNoOnPageList > this.totalPageCount){
			this.lastPageNoOnPageList = this.totalPageCount;
		}
		return this.lastPageNoOnPageList;
		
	}

	public int getFirstRecordIndex() {
		this.firstRecordIndex = (this.currentPageNo - 1) * this.recordCountPerPage;
		return this.firstRecordIndex;
	}

	public int getLastRecordIndex() {
		this.lastRecordIndex = this.currentPageNo * this.recordCountPerPage;
		return this.lastRecordIndex;
	}
	
	
	
	/*public void setTotalPageCount(int totalPageCount) {
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
	}*/
}


package com.jkonury.www.common.paging;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class Pagination extends SimpleTagSupport {
	private Paging paging;

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	@Override
	public void doTag() throws JspException, IOException {
		//페이징 부분 감싸는 최상위 div 태크 선택
		
		JspWriter out = getJspContext().getOut();		
		JspFragment body = getJspBody();
		StringWriter htmlTag = new StringWriter();				
		if(body != null){
			out.println("<center><H2>");
			body.invoke(htmlTag);
			out.println("</H2></center>");
		}
		
//		System.out.println(body.toString());
//		System.out.println(htmlTag);
		
		Elements elem = Jsoup.parseBodyFragment(htmlTag.toString()).select("div");

		//페이징 이전/다음 버튼에 page값 입력
		if (paging.getTotalPageCount() == 1) { // 페이지가 1개일 때

			elem.select("a:has(img)").eq(0).attr("href", Integer.toString(paging.getCurrentPageNo())).addClass("goPage"); // 첫 페이지
			elem.select("a:has(img)").eq(1).attr("href", Integer.toString(paging.getCurrentPageNo())).addClass("goPage"); // 이전 페이지
			elem.select("a:has(img)").eq(2).attr("href", Integer.toString(paging.getCurrentPageNo())).addClass("goPage"); // 다음 페이지
			elem.select("a:has(img)").eq(3).attr("href", Integer.toString(paging.getCurrentPageNo())).addClass("goPage"); // 마지막 페이지
		} else { // 페이지가 여러개일 때

			if (paging.getCurrentPageNo() == 1) { // 현재 페이지가 1 페이지일 때

				elem.select("a:has(img)").eq(0).attr("href", Integer.toString(paging.getFirstPageNoOnPageList())).addClass("goPage"); // 첫 페이지
				elem.select("a:has(img)").eq(1).attr("href", Integer.toString(paging.getFirstPageNoOnPageList())).addClass("goPage"); // 이전 페이지
				elem.select("a:has(img)").eq(2).attr("href", Integer.toString(paging.getCurrentPageNo() + 1)).addClass("goPage"); // 다음 페이지
				elem.select("a:has(img)").eq(3).attr("href", Integer.toString(paging.getTotalPageCount())).addClass("goPage"); // 마지막 페이지
			} else if (paging.getCurrentPageNo() == paging.getTotalPageCount()) { // 현재 페이지가 마지막 페이지 일 때

				elem.select("a:has(img)").eq(0).attr("href", Integer.toString(1)).addClass("goPage"); // 첫 페이지
				elem.select("a:has(img)").eq(1).attr("href", Integer.toString(paging.getCurrentPageNo() - 1)).addClass("goPage"); // 이전 페이지
				elem.select("a:has(img)").eq(2).attr("href", Integer.toString(paging.getCurrentPageNo())).addClass("goPage"); // 다음 페이지
				elem.select("a:has(img)").eq(3).attr("href", Integer.toString(paging.getCurrentPageNo())).addClass("goPage"); // 마지막 페이지
			} else {

				elem.select("a:has(img)").eq(0).attr("href", Integer.toString(1)).addClass("goPage"); // 첫 페이지
				elem.select("a:has(img)").eq(1).attr("href", Integer.toString(paging.getCurrentPageNo() - 1)).addClass("goPage"); // 이전 페이지
				elem.select("a:has(img)").eq(2).attr("href", Integer.toString(paging.getCurrentPageNo() + 1)).addClass("goPage"); // 다음 페이지
				elem.select("a:has(img)").eq(3).attr("href", Integer.toString(paging.getTotalPageCount())).addClass("goPage"); // 마지막 페이지
			}
		}


		//현재 페이지가 첫 페이지면 이전 버튼 숨김
		if(1 == paging.getFirstPageNoOnPageList()){
			//elem.select("a:has(img)").eq(0).attr("style", "display:none");
		}
		//현재 페이지가 제일 마지막이면 다음 버튼 숨김
		if(paging.getTotalPageCount() == paging.getLastPageNoOnPageList()){
			//elem.select("a:has(img)").eq(1).attr("style", "display:none");
		}

		//페이징 번호에 값 입력
		/*Element ul = elem.select("ul").first();
				Element li = elem.select("ul li").first().clone();
				ul.children().remove();*/
//		Element span = elem.select("span").first();
//		Element spanCopy = elem.select("span a").first().clone();
//		span.children().remove();
//		for(int i = paging.getFirstPageNoOnPageList(); i <= paging.getLastPageNoOnPageList(); i++){
//			Element child = spanCopy.clone();
//
//			//현재 페이지 부분일 경우엔 a태그 삭제
//			if(paging.getCurrentPageNo() == i){
//				child.children().remove();
//				//child.text(Integer.toString(i));
//				child.html("<b>" + Integer.toString(i) + "</b>");
//			}
//			//현재 페이지가 아닐 경우
//			else{
//				child.select("a").first().text(Integer.toString(i)).attr("href", Integer.toString(i)).addClass("goPage");
//			}
//			span.appendChild(child);
//		}		
//
//		elem.prepend("<input type='hidden' name='currentPageNo' value='" + paging.getCurrentPageNo() + "'/>");

		//jquery 조작 부분
		elem.append(
				"<script type='text/javascript'>" +				
						"	$(\".goPage\").click(function(){" +
						"		$(\"input[name='currentPageNo']\").val($(this).attr(\"href\"));" +
						"		goPage(); " +
						"		return false;" +
						"	});" +
				"</script>");
//		getBodyContent().clearBody();
		out.println(elem.toString());
		System.out.println(elem.toString());
			
	}
}




<?xml version="1.0" encoding="UTF-8" ?>

<taglib xmlns="http://java.sun.com/xml/ns/j2ee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd"
    version="2.0">
    
  <description>Pagination 0.1 library</description>
  <display-name>pagination</display-name>
  <tlib-version>0.1</tlib-version>
  <short-name>pg</short-name>
  <uri>http://www.heejoongs.com/lib/pagination</uri>

  
  <tag>
    <description>
	Simple conditional tag that establishes a context for
	mutually exclusive conditional operations, marked by
	&lt;when&gt; and &lt;otherwise&gt;
    </description>
    <name>pagination</name>
    <tag-class>com.bsc.common.Pagination</tag-class>
    <body-content>JSP</body-content>
    
    <attribute>
    	<description>페이징 객체</description>
    	<name>paging</name>
    	<required>true</required>    	
    	<rtexprvalue>true</rtexprvalue>
    </attribute>
    
  </tag>

  
</taglib>


<pg:pagination paging="${communityVO}">
	<div class="paging mtop_6">
		<a href="#"><img src="/images/basic/paging_prev2.gif" alt="처음으로이동" /></a>
		<a href="#"><img src="/images/basic/paging_prev1.gif" alt="이전" /></a>
		<span>
			<a href="#"><b>1</b></a>
			<a href="#">2</a>
			<a href="#">3</a>
		</span>
		<a href="#"><img src="/images/basic/paging_next1.gif" alt="다음" /></a>
		<a href="#"><img src="/images/basic/paging_next2.gif" alt="마지막으로이동" /></a>
	</div>
</pg:pagination>

@RequestMapping(value="/community/communityList")
protected ModelAndView communityList(HttpServletRequest arg0, HttpServletRequest arg1, CommunityVO communityVO) throws Exception {
	ModelAndView mav = new ModelAndView("community/communityList");
	
	List<CommunityVO> list = null;
	List<CommunityVO> headList = null;
	int totalCount = 0;

	try {
		// 게시글 리스트
		communityVO.setBoardSeq(communityVO.getBoardSeq());
		totalCount = service.getTotalCount(communityVO);
		communityVO.setTotalRecordCount(totalCount);
		
		list = (List<CommunityVO>) service.getBoardList(communityVO);
		headList = service.getBoardHeadList(communityVO);
	}catch (Exception exception) {
		log.error(exception);
	}
	
	mav.addObject("headList", headList);
	mav.addObject("list", list);
	mav.addObject("communityVO", communityVO);
	mav.addObject("count", communityVO.getTotalRecordCount());
	
	return mav;
}

<select id="getBoardList" parameterClass="communityVO" resultClass="communityVO">
	select 
		seq					as boardSeq,
		category			as boardCategory,
		company				as boardCompany,
		ifnull(member_no,0)	as memberNo,
		writer				as boardWriter,
		title				as boardTitle,
		content				as boardContent,
		reg_date			as boardRegDate,
		ip_addr				as ip_addr,
		hit					as boardHit,
		F.file_name			as boardFile,
		F.file_real_name	as boardRealFile,
		(SELECT COUNT(*) FROM ij_comment WHERE b_seq=B.seq and status!='D' and b_type='B') AS boardCommentCnt
	from 
		ij_board B 
	left outer join 
		ij_file F 
	on 
		B.seq=F.b_seq and F.board_type='B'
	where 
		B.status!='D'
	<include refid="boardSearch"/>
	<dynamic>
		<isNotEmpty prepend="AND" property="cate1">
			B.category = #cate1#
		</isNotEmpty>
		<isNotEmpty prepend="AND" property="boardCompany">
			B.company = #boardCompany#
		</isNotEmpty>
		<isGreaterThan prepend="AND" property="boardSeq" compareValue="0">
			B.seq = #boardSeq# 
		</isGreaterThan>
		<isEqual property="searchType" prepend="AND" compareValue="boardTitle">
			B.title like CONCAT('%',#searchWord#,'%')
		</isEqual>
		<isEqual property="searchType" prepend="AND" compareValue="boardCompany">
			<isEqual property="searchWord" compareValue="기타">
				B.company = 'etc'
			</isEqual>
			<isNotEqual property="searchWord" compareValue="기타">
				B.company like CONCAT('%',#searchWord#,'%')
			</isNotEqual>
		</isEqual>
		<isEqual property="searchType" prepend="AND" compareValue="boardWriter">
			B.writer like CONCAT('%',#searchWord#,'%')
		</isEqual>
	</dynamic>
	order by B.seq desc
	limit #firstRecordIndex#, #recordCountPerPage#
</select>



오라클 페이지 처리 sql
SELECT 
    boardNo,
    userId,
    title,
    TEXT,
    TO_CHAR (
        regTime,
        'YYYY-MM-DD HH24:MI:SS'
    ) AS regTime,
    faqBoardNo,
    hits 
FROM
    FaqBoardView 
WHERE faqBoardNo IN 
    (SELECT 
        faqBoardNo 
    FROM
        (SELECT 
            faqBoardNo,
            ROWNUM AS sq 
        FROM
            (SELECT 
                faqBoardNo 
            FROM
                FaqBoardView 
            ORDER BY faqBoardNo DESC)
		) 
    WHERE sq > #startPage# AND sq <= #endPage#)
ORDER BY faqBoardNo DESC 

답변형 게시판
INSERT INTO BoardBT	(		boardNo,	userId, title, text, regTime,		category,		freeBoardNo,			listNo, parent	, depth 
VALUES				(SEQ_BoardBT.NEXTVAL , ?,	?,		?,		SYSDATE,	 ?,		SEQ_productBoardNo.NEXTVAL,		?,			?,		? )