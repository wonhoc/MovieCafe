<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="domain.member.UserInfoVo"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>




<% 
	String requestUri = (String)request.getAttribute("requestUri");
	requestUri = requestUri == null ? "" : requestUri;

%>



<!DOCTYPE html>
<html lang="ko">
<script type="text/javascript"  src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>


	<head>
	
	<script>
	
	
	
	
	</script>
		<meta charset="UTF-8">
		<title>로그인 유저 폼</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/login.do" method="POST">
		
			ID : <input type="text" name="userId" id="userId"><br>
			Password : <input type="password" name="userPwd" id="userPwd"><br>
		
		<input type="hidden" name="requestUri" value="<%=requestUri%>"/>
		
		<button value="submit">로그인</button>
		<button value="reset">취소</button>
		
		
		</form>

	</body>
</html>