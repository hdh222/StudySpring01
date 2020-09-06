<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>home 화면입니다. </h1>
	<c:choose>
		<c:when test="${empty id }">
			<a href="users/login-form.do">로그인</a>
			<a href="users/signup-form.do">회원가입</a>
		</c:when>
		<c:otherwise>
			<a href="users/private/info.do"><strong>${id }</strong></a>님 로그인중..
			<a href="users/private/logout.do">로그아웃</a>
		</c:otherwise>
	</c:choose>
	
</body>
</html>