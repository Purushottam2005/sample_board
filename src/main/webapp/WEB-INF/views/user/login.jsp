<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- header -->
	<jsp:include page="../layout/header.jsp" />
	
</head>
<body>
	<jsp:include page="../layout/nav.jsp" />
	
	<!-- container -->
	<div class="container">
		<form:form class="form-horizontal"  method="POST" modelAttribute="user">
			<fieldset>
				<legend>로그인</legend>
				<div class="control-group">
					<form:label class="control-label" path="id">아이디:</form:label>
					<div class="controls">
						<form:input path="id" size="20" maxlength="20" placeholder="아이디"/>
						<form:errors cssClass="error" path="id" />
					</div>
				</div>
				<div class="control-group">
					<form:label class="control-label" path="passwd">비밀번호:</form:label>
					<div class="controls">
						<form:password path="passwd" showPassword="true" size="20" maxlength="20" placeholder="비밀번호"/>
						<form:errors cssClass="error" path="passwd" />
						<p class="help-block"><a href="/forgot_password">(forgot password)</a></p>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="remember">Remember Me</label>
					<div class="controls">
						<label class="checkbox">
							<input id="remember" type="checkbox" name="_spring_security_remember_me"  />
						</label>
					</div>
				</div>
				<%-- <c:if test="${ajaxlogin_yn == 'N'}">
					<input type="hidden" value="${param.returnURL}" name = "spring-security-redirect" />
				</c:if> --%>
		
				<div class="form-actions">
					<button type="submit" class="btn btn-primary">들어가기</button>
					<button type="reset" class="btn">다시입력</button>
				</div>
			</fieldset>
		</form:form>

	</div>
	
<!-- footer -->
<jsp:include page="../layout/footer.jsp" />
