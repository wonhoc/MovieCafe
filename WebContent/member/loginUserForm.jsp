<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인 유저 폼</title>
	</head>
	<body>
		<form action ="${pageContext.request.contextPath}/joinUser" id = loginUser name = loginUser method="POST">
			<div>
				<label for= "userId">ID</label>
				<input type="text" id="userId" name="userId">
			</div>
			<div>
				<label for="userPwd">PassWord</label>
				<input type="password" id="userPwd" name="userPwd">
			</div>
			
			<button type="submit"> 로그인</button>
			<button type="button"> 취소</button>
			<button type="button" onclick= "location ='joinUserForm.jsp'"> 회원가입 </button>
		
		
		</form>
		
	</body>
</html>