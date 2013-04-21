<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- header -->
<jsp:include page="../layout/header.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/common/css/forms.css" />
<!-- <script type="text/javascript">
	
</script> -->
</head>
<body>
	<jsp:include page="../layout/nav.jsp" />
	
	<%-- 현재 uri 얻어오기 --%>
	<c:set var="uri" value="${requestScope['javax.servlet.forward.request_uri']}" />
	
	<div class="container">
		<div class="row">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<c:forEach items="${boardTables}" var="board_table_list">
						<c:choose>
							<c:when test="${fn:indexOf(uri, board_table_list.boardId) > -1}">
								<li class="active"><a href="/board/${board_table_list.boardId}/list/1"><i class="icon-chevron-right"></i>${board_table_list.boardName}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/board/${board_table_list.boardId}/list/1"><i class="icon-chevron-right"></i>${board_table_list.boardName}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<%-- <li><a href="noti_board"><i class="icon-chevron-right"></i> 공지사항</a></li>
					<li><a href="free_board"><i class="icon-chevron-right"></i> 자유게시판</a></li> --%>
				</ul>
			</div>	
			<div class="span9">
				<section id="">
				<c:if test="${boardId != null}">
					<table class="table table-striped">
						<thead >
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${boardList}" var="list">
								<tr>
									<td>${list.boardSeq}</td>
									<td>
									<a id="boardView" href="/board/${boardId}/view/${list.boardSeq}">${list.title}</a>
									<input type="hidden" name="" value="${list.userSeq}" />
									<input type="hidden" name="" value="${list.passwd != null and list.passwd != ''}" />
									<c:if test="${list.hasFile == true}"><i class="icon-file"></i></c:if>
									<c:if test="${list.passwd != null and list.passwd != ''}"><i class="icon-lock"></i></c:if>
									</td>
									<td>${list.userName}</td>
									<td>
										<jsp:useBean id="today" class="java.util.Date" />
										
										<fmt:parseDate value="${list.regDate}" pattern="yyyy-MM-dd HH:mm:ss" var="regDate"/>
										<fmt:formatDate value="${regDate}" pattern="yyyy-MM-dd"/>
									</td>
									<td>${list.inquiryCount}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<div class="control-group">
						<form action="../search/1" class="form-horizontal" method="POST">
							<a href="../add" class="btn btn-primary">글쓰기</a>
							<select name="searchType" >
								<option value="all">전체</option>
								<option value="title">제목</option>
								<option value="name">작성자</option>
								<option value="content">내용</option>
							</select>
							<input type="text" name="searchWord" id="searchWord" />
							<input type="submit" class="btn" value="검색">
						</form>
					</div>
					
					<!-- 페이지 처리부분 -->
					<div class="pagination pagination-small" style="text-align:center">
						<div class="form-horizontal">
							<ul>
								<c:if test="${ page.prePage == true }">
									<li><a href="1" class="direction" title="처음 페이지">&lt;&lt;</a></li>	
									<li><a href="${ page.blockStart - 15 }" class="direction" title="이전 페이지">&lt;</a></li>	
								</c:if>
								<c:if test="${ page.prePage == false }">
								</c:if>
								<c:forEach var="cnt" begin="${ page.blockStart }" end="${ page.blockEnd }">
									<c:if test="${ curPage == cnt  }" >
										<li class="active"><a href="#">${cnt}</a></li>	
									</c:if>
									<c:if test="${ curPage != cnt  }" >
										<li><a href="${cnt}">${cnt}</a></li>						
									</c:if>
								</c:forEach>
								<c:if test="${ page.nextPage == true }">
									<li><a href="${ page.blockStart + 15 }" class="direction" title="다음 페이지">&gt;</a></li>	
									<li><a href="${ page.numOfPage}" class="direction" title="마지막 페이지">&gt;&gt;</a></li>	
								</c:if>
								<c:if test="${ page.nextPage == false }">
								</c:if>
							</ul>
						</div>
					</div>
				</c:if>
				</section>
			</div>
		</div>
	</div>
	
	
<script type="text/javascript">
	$(document).on('click', '#boardView', function(){
		var path = $(this).attr("href");
		
		var sessionUserSeq = "${sessionScope.user.userSeq}";
		var userSeq = $(this).next().val();
		var hasPasswd = $(this).next().next().val();
		
		if(hasPasswd == false){
			location.href = path;
		} else {
			if(userSeq == sessionUserSeq){
				location.href = path;
			} else {
				alert("비밀글은 작성자만 볼 수 있습니다.");
				return false;
			}
		}
	});
	
</script>
<!-- footer -->
<jsp:include page="../layout/footer.jsp" />
