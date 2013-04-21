<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="pg" uri="http://www.jkonury.com/lib/pagination" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title></title>
	<link rel="stylesheet" type="text/css" href="/resources/css//board/board.css"/>
	
	<script type="text/javascript" src="/resources/js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	function goPage(){
		$("#frm").attr("action", "communityList").submit();
	}
	</script>
</head>
<body>
	<pg:pagination paging="${pageVO}">
	<div class="paging mtop_6">
		<a href="#">처음으로 이동</a>
		<a href="#">이전</a>
		<span>
			<a href="#"><b>1</b></a>
			<a href="#">2</a>
			<a href="#">3</a>
		</span>
		<a href="#">다음</a>
		<a href="#">마지막으로이동</a>
	</div>
</pg:pagination>
</body>
</html>