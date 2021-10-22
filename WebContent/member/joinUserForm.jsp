<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--joinUserForm.jsp --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 가입 폼</title>
	</head>
<body>
	<form action="${pageContext.request.contextPath}/uploadFile" method="POST" enctype="multipart/form-data">
		<div>
			<label for ="userId">ID</label>
			<input type="text" name="userId" id="userId">
			<button type="button" id="idCheck">중복확인</button>
		</div>
		<div>
			<label for="userPwd">Password</label>
			<input type="password" name="userPwd" id="userPwd">
		</div>
		<div>
			<label for="userPwdCheck">Password 확인</label>
			<input type="password" name="userPwdCheck" id="userPwdCheck">
		</div>
		<div>
			<label for="userEmail">E-mail</label>
			<input type="text" name="userEmail" id="userEmail">
		</div>
		<div>
			<label>생년월일</label>
			<select id="birthYear" name="birthYear">
				<option value="">1990</option>
			</select>
			<select id="birthMonth" name="birthMonth">	
				<option value="">01</option>
			</select>
			<select id="birthDate" name="birthDate">
				<option value="">13</option>
			</select>
		</div>

		<div>
			<label>연락처</label>
			<select id="contact1" name="contact1">
				<option value="">010</option>
			</select>
			<select id="contact2" name="contact2">	
				<option value="">0101</option>
			</select>
			<select id="contact3" name="contact3">
				<option value="">1111</option>
			</select>
		</div>
		
		<div>
			<label for="photoSys"> 프로필 사진 설정 </label>
			<input type="file" name="photoSys" id="photoSys">
		</div>
		
		<div>
			<label for="userNick"> 닉네임 설정</label>
			<button type="submit"> 확인</button>
		</div>
		
		<div>
			<label for="userName"> 이름</label>
			<input type="text" name="userName" id="userName">
		</div>
		
		<div>
			<label> 성별</label>
			<label for="pickGender">남</label>
			<input type="radio" name="pickGender" id="pickMale" value="M" checked>
			<label for="pickFemale">여</label>
			<input type="radio" name="pickGender" id="pickfeMale" value="F">
		</div>
		
		<button type="button">취소</button>
		<button type="submit">확인</button>
		
	</form>


</body>
</html>