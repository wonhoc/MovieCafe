<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="domain.member.UserInfoVo"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴페이지</title>
</head>

<body>
	<h1>회원탈퇴</h1>
	<hr>
	<h3>★정말로 탈퇴하시겠습니까?</h3>
	<form action="${pageContext.request.contextPath}/userDelete.do" method="POST">
	<p>지금 탈퇴하시면 기존의 작성한 게시글, 댓글 등 회원님의 카페 관련 활동내역들이 남아있게 됩니다. 기록이 남지 않기를 원하신다면 직접 삭제 후 탈퇴 과정을 진행하시길 바랍니다. 탈퇴 후 재가입은 7일 이후에 가능합니다
정말 탈퇴하시겠습니까?
	</p>
	
	<button type="submit">확인</button>
	</form>
</body>
</html>