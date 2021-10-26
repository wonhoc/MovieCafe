<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="domain.member.UserInfoVo"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

	<script type="text/javascript">


	</script>
<title>마이페이지</title>
</head>

<body>
	<%--<!--임의로 사용자 아이디, 닉네임, 가입일 세션에 바인딩 -->--%>
	<c:set var="userId" value="test_user01" scope="session" />
	<c:set var="userNick" value="일용직" scope="session" />
	<c:set var="joinDate" value="2021-10-10" scope="session" />
	<c:set var="photoSys" value= "" scope="session"/>
	<form>
		<h1>마이페이지</h1>
		<h3>내 활동을 확인할 수 있습니다.</h3>
		<div>
			프로필 사진
			<img id = "photoSys">
		</div>

		<div>
			아이디 :<c:out value="${userId}"    /> <br>
			닉네임 :<c:out value="${userNick }" /> <br>
			가입일 :<c:out value="${joinDate }" /> <br>
		</div>

		<div>
			<button>수정</button>
		</div>
		<button> 탈퇴</button>

	</form>





</body>
</html>