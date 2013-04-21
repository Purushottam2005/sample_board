<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<button type="button" class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="brand" href="/">Sample Board</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class=""><a href="/">Home</a></li>
					<c:if test="${empty sessionScope.loginInfo }">
						<li class=""><a href="/user/login">로그인</a></li>
					</c:if>
					<c:if test="${!empty sessionScope.loginInfo }">
						<li><a href="#">${sessionScope.loginInfo.name}님</a></li>
						<li class=""><a href="/user/logout">로그아웃</a></li>
					</c:if>
					<li class=""><a href="/user/add">회원가입</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">마이페이지 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/user/update">기본정보 수정</a></li>
							<li><a href="#">비밀번호 변경</a></li>
						</ul>
					</li>
					<li class=""><a href="/board/list">게시판</a></li>
					<li class=""><a href="#"></a></li>
					<li class=""><a href="#"></a></li>
				</ul>
			</div>
		</div>
	</div>
</div>

<!-- Subhead ================================================== -->
<header class="jumbotron subhead" id="overview">
	<div class="container">
		<h1>Spring 3.1+ iBatis + Maven Sample Board</h1>
		<p class="lead">Spring 3.1, iBatis Sample Board</p>
	</div>
</header>
<%-- <p>${sessionScope.user}</p>
<p>${empty sessionScope.loginInfo}</p>
<p>${sessionScope.loginInfo}</p> --%>