<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- header -->
<jsp:include page="../layout/header.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/common/css/forms.css" />

</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar">
	<jsp:include page="../layout/nav.jsp" />
	
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
				<div class="form-horizontal">
					<section id="">
						<table class="table table-striped">
							<tbody>
								<tr>
									<td>
										<label for="">제목</label> 
										${board.title}
									</td>
								</tr>
								<tr>
									<td>
										내용
									</td>
								</tr>
								<tr>
									<td>
										<label for="내용"></label>
										${board.content}
									</td>
								</tr>
								<c:if test="${board.hasFile == 'Y' }">
									<tr>
										<td>파일 &nbsp;&nbsp;&nbsp;&nbsp; <a href="/board/downloadFile?fileName=${boardFile.orgFileName} &boardSeq=${board.boardSeq}">${boardFile.orgFileName}</a></td>
									</tr>								
								</c:if>
							</tbody>
						</table>
						<div class="form-actions">
							<input type="button" class="btn btn-primary" value="수정" />
							<input type="button"  class="btn" value="삭제"  />
							<a href="../list/1" class="btn">목록보기</a>
						</div>
						<div class="control-group">
							<form:form modelAttribute="boardReply" action="/board/${boardId}/reply/${boardSeq}" method="POST">
								<form:textarea path="content" cols="100" rows="5"/>
								<form:errors cssClass="error" path="content"/>
									
								<div class="form-actions">
									<input type="submit" class="btn btn-primary" value="등록" />
									<input type="hidden" name="userSeq" value="${sessionScope.loginInfo.userSeq}" />
									<input type="hidden" name="tableSeq" value="${board.tableSeq}" />
								</div>
							</form:form>
						</div>
						
						<table class="table table-striped">
							<tbody>
								<c:forEach items="${boardReplies}" var="board_reply_list">
									<tr>
										<td>${board_reply_list.userName} |
											${board_reply_list.regDate} 
										</td>
									</tr>
									<tr>
										<td>${board_reply_list.content}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
					</section>
				</div>
			</div>
		</div>
	</div>
<!-- footer -->
<jsp:include page="../layout/footer.jsp" />
