<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		$(document).ready(function() { 
			<%--아이디 찾기 --%>
			$('#inputNameConBtn').on('click', function() {
				const sendUserName = $('#inputUserName').val();
				const sendUserContact = $('#userContact').val();
				
				getForgetId('${pageContext.request.contextPath}/returnId.do', sendUserName, sendUserContact);
				
			});
			
			const getAjax3 = function(url, username, usercon) {
				return new Promise( (resolve, reject) => {
					$.ajax({
					
						url: url,
						method: 'POST',
						dataType: 'json',
						data: {
							userName : username,
							userContact : usercon
							
						},
						success: function(data) {
							resolve(data);
						},
						error : function(e) {
							reject(e);
						}
						
					});
					
				});
			};
			
			async function getForgetId(url, sendUserName, sendUserContact) {
				console.log('sendUserName =', sendUserName);
				console.log('sendUserContact = ', sendUserContact);
			
				try {
					const result3 = await getAjax3(url, sendUserName, sendUserContact);
				if(result3.returnId.length >= 1) {
						alert("아이디는 " + result3.returnId + " 입니다.");				
					} else {
						alert("실패");
					} 
					
				} catch(error5) {
					console.log("error5 : ", error5);
					}
				}
			
			
			<%-- 생년월일 선택 부분 구현--%>
			var now = new Date();
			var birthYear = now.getFullYear();
			var birthMonth = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1): '0'+(now.getMonth() + 1);
			var birthDate = (now.getDate()) > 9 ? ''+(now.getDate()) : '0'+ (now.getDate());
			
			for(var i = 1900; i <= birthYear; i++) {
				
				$('#birthYear').append('<option value ="'+ i +'" >' + i + '년</option>')
			}
			for(var i = 1; i <= 12; i++) {
				if (i < 10) {
					$('#birthMonth').append("<option value='0" + i + "'>0" + i+ "</option>");
						} else {
							$('#birthMonth').append("<option value='" + i + "'>" + i+ "</option>");
						}
					}
				
				
				
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
			$("#birthYear > option[value="+birthYear+"]").attr("selected", "true");
			$("#birthMonth > option[value="+birthMonth+"]").attr("selected", "true");
			$("#birthDate > otpion[value="+birthDate+"]").attr("selected", "true");
			
			<%-- 비밀번호 찾기 --%>
			$('#inputNameConBirBtn').on('click', function() {
				
				const inputUserId2 = $('#inputUserId2').val();
				const inputUserCon2 = $('#inputUserCon2').val();
				const inputUserBir2 = $('#birthYear' + '#birthMonth' + '#birthDate').val();
				
				getForgetPw = ('${pageContext.request.contextPath}/returnPw.do', inputUserId2,inputUserCon2,inputUserBir2);
			});
			
			const getAjax4 = function(url, inputUserId2, inputUserCon2,inputUserBir2 ) {
				return new Promise((reslove, reject => {
					$.ajax({
					
						url: url,
						mothod: 'POST',
						dataType: 'json',
						data: {
							inputUserId2 : inputUserId2,
							inputUserCon2 : inputUserCon2,
							inputUserBir2 : inputUserBir2
							
						},
						success: function(data) {
							resolve(data);
						},
						error: function(e) {
							reject(e);
						}
					});
				});
			};
			
			
			})

		
		</script>
<title>아이디 비밀번호 찾기</title>
</head>
<body>
	<form>
		<h2>아이디 / 비밀번호 찾기</h2>
		<div>
			<h3>아이디 찾기</h3>
			<br> 가입 당시 입력한 성명과 연락처를 입력하세요.<br> <label for="inputIdCon">성명</label>
			<input type="text" name="inputId" id="inputUserName"><br>
			<label for="inputIdCon">연락처</label> <input type="text"
				name="inputIdCon" id="userContact">
			<button type="button" id="inputNameConBtn">확인</button>

		</div>
		<div>
			<h3>비밀번호 찾기</h3>
			<br> 가입 당시 입력한 아이디와 연락처, 생년월일을 입력하세요.<br> 
			<label for="inputIdConBir">ID</label> 
			<input type="text" name="inputIdConBir2" id="inputUserId2"><br> 
			<label for="inputIdConBir">연락처</label> 
			<input type="text" name="inputIdConBir2" id="inputUserCon2"><br> 
			<label for="inputInConBir"> 생년월일</label> 
			<select id="birthYear" name="birthYear"></select> 
			<select id="birthMonth" name="birthMonth"></select>
			<select id="birthDate" name="birthDate"></select> <br>
			<button type="button" id="inputNameConBirBtn">확인</button>

		</div>






	</form>
</body>
</html>