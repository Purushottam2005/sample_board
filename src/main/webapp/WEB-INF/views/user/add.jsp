<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header -->
<jsp:include page="../layout/header.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/common/css/forms.css" />
</head>
<body>
	<jsp:include page="../layout/nav.jsp" />
	
	<div class="container">
		<form:form modelAttribute="user" method="POST" class="form-horizontal">
			<fieldset>
				<legend>회원정보</legend>
				<div class="control-group">
					<form:label path="name" class="control-label">이름:</form:label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-user"></i></span>
							<form:input path="name" size="16" maxlength="18" placeholder="이름"/>
							<form:errors cssClass="error" path="name" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<form:label path="id" class="control-label">아이디 :</form:label>
					<div class="controls">
						<form:input path="id" size="8" maxlength="12" placeholder="아이디"/>
						<form:errors cssClass="error" path="id" />
					</div>
				</div>
				
				<div class="control-group">
					<form:label path="email" class="control-label">이메일:</form:label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-envelope"></i></span>
							<form:input type="email" path="email" size="20" maxlength="20" placeholder="이메일"/>
							<form:errors cssClass="error" path="email" />
						</div>
					</div>
				</div>	
				
				<div class="control-group">	
					<form:label path="tel" class="control-label">전화번호 :</form:label>
					<div class="controls">
						<form:input type="tel" path="tel" size="20" maxlength="20" />
						<form:errors cssClass="error" path="tel" />
					</div>
				</div>
				
				<div class="control-group">	
					<form:label path="phone" class="control-label">핸드폰 번호:</form:label>
					<div class="controls">
						<form:input type="tel" path="phone" size="20" maxlength="20" />
						<form:errors cssClass="error" path="phone" />
					</div>
				</div>
				
				<div class="control-group">
					<form:label path="passwd" class="control-label">비밀번호:</form:label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-lock"></i></span>
							<form:password path="passwd" showPassword="true" size="12" maxlength="12" />
							<form:errors cssClass="error" path="passwd" />
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<form:label path="passwdCheck" class="control-label">비밀번호 확인:</form:label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-lock"></i></span>
							<form:password path="passwdCheck" showPassword="true" size="12" maxlength="12" />
							<form:errors cssClass="error" path="passwdCheck" />
						</div>
					</div>
				</div>
				
				<div class="form-actions">
					<button type="submit" class="btn btn-primary">회원가입</button>
					<button type="reset" class="btn">다시입력</button>
				</div>
			</fieldset>
		</form:form>
	</div>
	
	<!-- footer -->
	<jsp:include page="../layout/footer.jsp" />
</body>
</html>