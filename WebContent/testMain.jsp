<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>테스트 메인 창</title>
	</head>
	<body>
		<form action= "${pageContext.request.contextPath}/LogoutHandler"></form>
		<div>
		로그인한 사용자 아이디 : <%=(String)session.getAttributeNames("userId") %><br>
		로그인한 사용자 등급 : <%=(String)session.getAttributeNames("rankNo") %>
		
		</div>
		<button value="submit">로그아웃</button>

	</body>
</html>