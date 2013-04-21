<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- header -->
<jsp:include page="../layout/header.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/common/css/forms.css" />
</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar">
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
				<form:form modelAttribute="board" method="POST" class="form-horizontal" enctype="multipart/form-data">
					<section id="">
						<table class="table table-striped">
							<tbody>
								<tr>
									<td>
										<form:label path="title" >제목</form:label>
										<form:input path="title" size="20" maxlength="20"/>
										<form:errors cssClass="error" path="title" />
									</td>
								</tr>
								<tr>
									<td>
										<form:label path="passwd">비밀번호</form:label>
										<form:password path="passwd" size="20" maxlength="20"/>
										<form:errors cssClass="error" path="passwd" />
									</td>
								</tr>
								<tr>
									<td>
										<form:label path="content">내용</form:label>
										<form:textarea path="content" cols="100" rows="10" />
										<form:errors cssClass="error" path="content" />
									</td>
								</tr>
								<tr>
									<td>
										<form:label path="file">파일</form:label>
										<form:input type="file" path="file"/>
										<form:errors cssClass="error" path="file" />
									</td>
								</tr>
								
							</tbody>
						</table>
						<div class="form-actions">
							<input type="submit" class="btn btn-primary" value="입력" />
							<input type="reset"  class="btn" value="다시 입력"  />
							<a href="list/1" class="btn">목록보기</a>
						</div>
					</section>
				</form:form>
			</div>
		</div>
	</div>
<!-- footer -->
<jsp:include page="../layout/footer.jsp" />
