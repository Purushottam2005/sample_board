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

			elem.select("a").eq(0).attr("href", Integer.toString(paging.getCurrentPageNo())).addClass("goPage"); // 첫 페이지
			elem.select("a").eq(1).attr("href", Integer.toString(paging.getCurrentPageNo())).addClass("goPage"); // 이전 페이지
			elem.select("a").eq(2).attr("href", Integer.toString(paging.getCurrentPageNo())).addClass("goPage"); // 다음 페이지
			elem.select("a").eq(3).attr("href", Integer.toString(paging.getCurrentPageNo())).addClass("goPage"); // 마지막 페이지
		} else { // 페이지가 여러개일 때

			if (paging.getCurrentPageNo() == 1) { // 현재 페이지가 1 페이지일 때

				elem.select("a").eq(0).attr("href", Integer.toString(paging.getFirstPageNoOnPageList())).addClass("goPage"); // 첫 페이지
				elem.select("a").eq(1).attr("href", Integer.toString(paging.getFirstPageNoOnPageList())).addClass("goPage"); // 이전 페이지
				elem.select("a").eq(2).attr("href", Integer.toString(paging.getCurrentPageNo() + 1)).addClass("goPage"); // 다음 페이지
				elem.select("a").eq(3).attr("href", Integer.toString(paging.getTotalPageCount())).addClass("goPage"); // 마지막 페이지
			} else if (paging.getCurrentPageNo() == paging.getTotalPageCount()) { // 현재 페이지가 마지막 페이지 일 때

				elem.select("a").eq(0).attr("href", Integer.toString(1)).addClass("goPage"); // 첫 페이지
				elem.select("a").eq(1).attr("href", Integer.toString(paging.getCurrentPageNo() - 1)).addClass("goPage"); // 이전 페이지
				elem.select("a").eq(2).attr("href", Integer.toString(paging.getCurrentPageNo())).addClass("goPage"); // 다음 페이지
				elem.select("a").eq(3).attr("href", Integer.toString(paging.getCurrentPageNo())).addClass("goPage"); // 마지막 페이지
			} else {

				elem.select("a").eq(0).attr("href", Integer.toString(1)).addClass("goPage"); // 첫 페이지
				elem.select("a").eq(1).attr("href", Integer.toString(paging.getCurrentPageNo() - 1)).addClass("goPage"); // 이전 페이지
				elem.select("a").eq(2).attr("href", Integer.toString(paging.getCurrentPageNo() + 1)).addClass("goPage"); // 다음 페이지
				elem.select("a").eq(3).attr("href", Integer.toString(paging.getTotalPageCount())).addClass("goPage"); // 마지막 페이지
				
				
			}
		}


		//현재 페이지가 첫 페이지면 이전 버튼 숨김
		if(1 == paging.getFirstPageNoOnPageList()){
			//elem.select("a").eq(0).attr("style", "display:none");
		}
		//현재 페이지가 제일 마지막이면 다음 버튼 숨김
		if(paging.getTotalPageCount() == paging.getLastPageNoOnPageList()){
			//elem.select("a").eq(1).attr("style", "display:none");
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
		elem.prepend("<input type='hidden' name='currentPageNo' value='" + paging.getCurrentPageNo() + "'/>");

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
