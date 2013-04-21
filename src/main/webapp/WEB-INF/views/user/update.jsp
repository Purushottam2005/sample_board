<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- header -->
	<jsp:include page="../layout/header.jsp" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap/css/docs.css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap/css/prettify.css.css" />
	<title>회원가입 화면</title>
</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar">
	<jsp:include page="../layout/nav.jsp" />
	
	
	<!-- container -->
	<jsp:include page="../layout/sidebar.jsp" />
	
	<div class="container">
		<!-- Button to trigger modal -->
		<a href="#myModal" role="button" class="btn" data-toggle="modal">Launch demo modal</a>
 
		<!-- Modal -->
		<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">Modal header</h3>
			</div>
			<div class="modal-body">
				<p>One fine body…</p>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
				<button class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
	
	<a href="#myModal" role="button" class="btn" data-toggle="modal">Launch demo modal</a>

	<div class="pagination">
		<ul>
			<li><a href="#">Prev</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">Next</a></li>
		</ul>
	</div>
	
	<div class="pagination">
		<ul>
			<li class="active"><a href="#">Prev</a></li>
			<li class="disabled"><a href="#">1</a></li>
			<li class="active"><a href="#">2</a></li>
			<li class="active"><a href="#">3</a></li>
			<li class="active"><a href="#">4</a></li>
			<li class="active"><a href="#">Next</a></li>
		</ul>
	</div>

	<div class="pagination">
		<ul>
			<li><a href="#">&laquo;</a></li>
			<li><a href="#">&lt;</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">&gt;</a></li>
			<li><a href="#">&raquo;</a></li>
		</ul>
	</div>

	<!-- footer -->
	<jsp:include page="../layout/footer.jsp" />
</body>
</html>