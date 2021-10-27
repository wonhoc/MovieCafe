<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="domain.member.UserInfoVo"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:forEach var="birth"
	items="${fn:split(requestScope.user.userBirth, '-')}" varStatus="i">
	<c:if test="${i.index == 0}">
		<c:set var="year" value="${birth }" />
	</c:if>
	<c:if test="${i.index == 1}">
		<c:set var="month" value="${birth }" />
	</c:if>
	<c:if test="${i.index == 2}">
		<c:set var="date" value="${birth }" />
	</c:if>
</c:forEach>

<c:forEach var="contact"
	items="${fn:split(requestScope.user.userContact, '-')}" varStatus="i">
	<c:if test="${i.index == 0}">
		<c:set var="contact1" value="${contact }" />
	</c:if>
	<c:if test="${i.index == 1}">
		<c:set var="contact2" value="${contact }" />
	</c:if>
	<c:if test="${i.index == 2}">
		<c:set var="contact3" value="${contact }" />
	</c:if>
</c:forEach>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 폼</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<script>
	$(document).ready(function() {
		$('input:radio[name=gender]:input[value=${requestScope.user.gender}]').attr("checked", true);
			$('input:radio[name=gender]').attr("disabled", true);
				function appendYear() {
					var date = new Date();
					var year = date.getFullYear();
					for (var i = year - 50; i <= year; i++) {
							$('#birthYear').append("<option value='" + i + "'>" + i+ "</option>");
							}
							$('#birthYear').val('${pageScope.year}').prop("selected", true);
							}

				function appendMonth() {
					for (var i = 1; i <= 12; i++) {
						if (i < 10) {
							$('#birthMonth').append("<option value='0" + i + "'>0" + i+ "</option>");
								} else {
									$('#birthMonth').append("<option value='" + i + "'>" + i+ "</option>");
								}

							}
							$('#birthMonth').val('${pageScope.month}').prop(
									"selected", true);
						}

						function appendDate() {
							for (var i = 1; i <= 31; i++) {
								if (i < 10) {
									$("#birthDate").append(
											"<option value='0" + i + "'>0" + i
													+ "</option>");
								} else {
									$("#birthDate").append(
											"<option value='" + i + "'>" + i
													+ "</option>");
								}

							}
							$('#birthDate').val('${pageScope.date}').prop(
									"selected", true);
						}

						appendYear();
						appendMonth();
						appendDate();

		//닉네임 중복여부를 확인한다.
		$('#userNicBtn').on('click', function() {
			var userNick = $('#userNick').val(); 
			console.log('userNick : ', userNick);
	        $.ajax({
	            url:'${pageContext.request.contextPath}/checkNick.do',
	            method: 'POST',
	            dataType: 'json',
	            data: {
	            	userNick: userNick
	            },
	            success:function(data){
	            	console.log("data", data);
	                if(data.isUserNick == "true"){	                	
	                	var html="이미 사용하고있는 닉네임입니다. 다른 닉네임을 입력해주세요.";
	                	$("#result").html(html);
	                	$("#result").css('color', 'red');
	                }
	            },
	            error:function(error){
	                console.log(error);
	            }
	        });
			
		})
	
	
	});
	
</script>

</head>
<body>
	<form action="${pageContext.request.contextPath}/uploadFile"
		method="POST" enctype="multipart/form-data">
		<div>
			<label for="userId">ID</label> <input type="text" name="userId"
				id="userId" value="${requestScope.user.userId}" readonly>
		</div>
		<div>
			<label for="userPwd">Password</label> <input type="password"
				name="userPwd" id="userPwd" value="${requestScope.user.userPwd}">
		</div>
		<div>
			<label for="userPwdCheck">Password 확인</label> <input type="password"
				name="userPwdCheck" id="userPwdCheck">
		</div>
		<div>
			<label for="userEmail">E-mail</label> <input type="text"
				name="userEmail" id="userEmail"
				value="${requestScope.user.userEmail}">
		</div>

		<div>
			<label>생년월일</label> <select id="birthYear" name="birthYear"></select>
			<select id="birthMonth" name="birthMonth"></select> <select
				id="birthDate" name="birthDate"></select>
		</div>

		<div>
			<label>연락처</label> <input id="contact1" name="contact1"
				value="${pageScope.contact1 }"> <input id="contact2"
				name="contact2" value="${pageScope.contact2 }"> <input
				id="contact3" name="contact3" value="${pageScope.contact3 }">
		</div>

		<div>
			<label for="photoSys"> 프로필 사진 설정 </label> <input type="file"
				name="photoSys" id="photoSys" value="${requestScope.user.photoSys}">
		</div>

		<div>
			<label for="userNick"> 닉네임 설정</label> <input type="text"
				name="userNick" id="userNick" value="${requestScope.user.userNick}">
			<button type="button" id="userNicBtn">확인</button>
			<div id="result"></div>
		</div>

		<div>
			<label for="userName"> 이름</label> <input type="text" name="userName"
				id="userName" value="${requestScope.user.userName}">
		</div>

		<div>
			<label> 성별</label> <label for="pickGender">남</label> <input
				type="radio" name="gender" id="pickMale" value="M"> <label
				for="pickFemale">여</label> <input type="radio" name="gender"
				id="pickfeMale" value="F">
		</div>

		<button type="button">취소</button>


		<button type="submit">수정</button>

	</form>


</body>
</html>