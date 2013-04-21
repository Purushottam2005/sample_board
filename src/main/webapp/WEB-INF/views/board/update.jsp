<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header -->
<jsp:include page="../layout/header.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/common/css/forms.css" />
<script type="text/javascript">
	function goList(){
		location.href = "list";
	}
</script>

</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar">
	<jsp:include page="../layout/nav.jsp" />
	
	<div class="container">
		<div class="row">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li><a href="#"><i class="icon-chevron-right"></i> 공지사항</a></li>
					<li><a href="#"><i class="icon-chevron-right"></i> 자유게시판</a></li>
				</ul>
			</div>		
			<div class="span9">
				<form:form modelAttribute="board" method="POST" class="form-horizontal">
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
									<td>파일 &nbsp;&nbsp;&nbsp;&nbsp; <input type="file" name="file" id="file" /></td>
								</tr>
								
							</tbody>
						</table>
						<div class="form-actions">
							<input type="button" class="btn btn-primary" value="수정" />
							<input type="button"  class="btn" value="삭제"  />
							<input onclick="goList()" type="button" class="btn" value="목록보기" />
						</div>
					</section>
				</form:form>
			</div>
		</div>
	</div>
<!-- footer -->
<jsp:include page="../layout/footer.jsp" />
