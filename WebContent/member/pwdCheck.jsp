<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>패스워드 확인</title>
	<style>
		hr {
			 align : left;
			 height: 10px;
			 background-color: orange;
			 width : 70%;
		
		}
	</style>
</head>
<body>
	<h1>Password 입력</h1>
	<hr>
	<h3>개인정보 보호를 위해 비밀번호를 다시 한번 입력해주세요</h3>
	<form action="${pageContext.request.contextPath}/pwdCheck.do" method="POST">
	<input type="password" name="userPwd" id="userPwd">
	
	<button type="submit">확인</button>
	</form>
</body>
</html>