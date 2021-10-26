<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="domain.member.UserInfoVo"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>





<html>
	<head>
		<meta charset="UTF-8">
		<!DOCTYPE html>
	<script type="text/javascript"  src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>


<script type="text/javascript">


</script>


<title>로그아웃 폼</title>
	</head>
		<body>
		로그인 아이디 : <% session.getAttribute("userId"); %> 님이 로그인 중. <br>
		
		<c:url var="logoutURL" value="">
		
		<Button type ="submit" id = "logoutBtn">로그아웃</Button>
		

		</body>
</html>