<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>로그인 유저 폼</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/login.do" method="POST">
		
			ID : <input type="text" name="userId" id="userId"><br>
			Password : <input type="password" name="userPwd" id="userPwd"><br>
		
		<button value="submit">로그인</button>
		<button value="reset">취소</button>	
		
		</form>

	</body>
</html>