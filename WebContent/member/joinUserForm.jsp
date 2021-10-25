<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="domain.member.UserInfoVo"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%--joinUserForm.jsp --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<script>
		$(document).ready(function() {
			
			
			<%--아이디 중복 체크 --%>
			$('#idChkBtn').on('click', function(){
				const userId = $('#userId').val();
				idchkProcess('${pageContext.request.contextPath}/checkId.do', userId);
			});
			
			const getAjax2 = function(url, userId) {
				return new Promise( (resolve, reject) => {
					$.ajax({
						url: url,
						method: 'POST',
						dataType: 'json',
						data: {
							userId: userId
						},
						//콜백함수
						success: function(data) {
							resolve(data);
						},
						error : function(e) {
							reject(e);
						}
					});
				});
			};
			
			async function idchkProcess(url, userId) {
				console.log('userId = ' , userId);
				try {
					const result = await getAjax2(url, userId);
					console.log("result : ", result.isUserId);
					if(result.isUserId == 'true') {
	                	$('#resultId').text('이미 사용중인 아이디 입니다.');
	                	$('#resultId').css('color', 'red');
					} else {
	                	$('#resultId').text('사용 가능한 아이디입니다.');
	                	$('#resultId').css('color', 'blue');
						}
					} catch (error)  {
						console.log("error : ", error);	
					
					}
				}
						
						
						
			<%--닉네임 중복 체크 --%>
			$('#userNickBtn').on('click', function() {				
				const userNick = $('#userNick').val();
				sendProcess('${pageContext.request.contextPath}/checkNick.do', userNick);		
				
			});
			
			 const getAjax1 = function(url, userNick) {
		            return new Promise( (resolve, reject) => {
		                $.ajax({                        
		                    url: url,
		                    method: 'POST',
		                    dataType: 'json',
		                    data: {
		                    	userNick: userNick           
		                    },
		                    // 콜백함수
		                    success: function(data) {
		                    	resolve(data);
		                   
		                    }, 
		                    error: function(e) {                    	
		                        reject(e);
		                    }
		                });
		            });
		        };   
			
			
			async function sendProcess(url, userNick) {
				console.log('userNick : ', userNick);
	            try {
	                const result = await getAjax1(url, userNick);	               
	                console.log("result : ", result.isUserNick);    
	                if(result.isUserNick == 'true') {
	                	$('#result').text('이미 사용중인 닉네임입니다.');
	                	$('#result').css('color', 'red');
	                }  else {
	                	$('#result').text('사용 가능한 닉네임입니다.');
	                	$('#result').css('color', 'blue');
	                }
	            } catch (error) {
	                console.log("error : ", error);   
	            }
	        }
			

			<%-- 생년월일 선택 부분 구현--%>
			window.onload = function() {
				var sYear = 1950;
				var eYear = 2021;
				var sMonth = 01;
				var eMonth = 12;
				var sDay = 1;
				var eDay = 31;
				
				var strYear = "";
				var strMonth = "";
				var strDay = "";
				
				for(var i =sYear; i<=eYear; i++){
					strYear +="<option value "+i+"> " +i+ " </option>";
				}
				for (var i =sMonth; i<=eMonth; i++) {
					strMonth += "<option value" +i+"> " +i+ "</option>";
				}
				
				for(var i = sDay; i<=eDay; i++) {
					strDay += "<option value" +i+ ">" +i+ "</option>";
				}
				
				document.getElementById('birthYear').innerHTML = strYear;
				document.getElementById('birthMonth').innerHTML = strMonth;
				document.getElementById('birthDate').innerHTML = strDay;
				
			}
			
			
			<%--비밀 번호 양식 확인 (보류)--%>
			function pwdformchk() {
				var exp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[-_])[a-z\d-_]{10,30}$/;
				var p1 = document.getElementById('userPwd').value;
				if(p1.match(exp)) {
					pwdformchk.innerText = "비밀번호 양식에 적합합니다.";
					pwdformchk.style.color="blue"				
				} else {
					wpd.formchk.innerText = "영문대소문자,특수문자가 하나 이상 들어가야 합니다. "
					pwdformchk.style.color="red"				
					
				}

			}
			
			<%--입력한 비밀번호 재확인 --%>
				function chkpwd() {
					var p1 = document.getElementById('userPwd').value;
					var p2 = document.getElementById('userPwdCheck').value;
					var pwdcheck = document.getElementsByClassName("alertpwd")[0];
					if(p1 != p2) {
						pwdcheck.innerText="비밀번호가 일치하지 않습니다.";
						pwdcheck.style.color="red";
					} else {
						pwdcheck.innerText="비밀번호가 일치합니다.";
						pwdcheck.style.color="blue";
					}	
				}
				
				
			<%--업로드 프로필 사진 미리보기 (보류) --%>
				function setPreview(event) {
					
					var reader = new FileReader();

					reader.onload = function(event) {
						var img = document.createElement("img");
						img.setAttribute("src", event.target.result);
						document.querySelector("div#previewContainer").appendChild(img);
					};
					reader.readAsDataURL(event.target.files[0]);
					
				}
				
			
		});
		
		</script>

<style>
.preViewContainer {
	width: 100px;
	height: 100px;
	object-fit: cover;
	background-color: gray;
}
</style>

<title>회원 가입 폼</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/joinUser"
		method="POST" enctype="multipart/form-data">
		<div>
			<label for="userId">ID</label> 
			<input type="text" name="userId" id="userId"> 
			<button type="button" id="idChkBtn" name="idChkBtn">확인</button>
			<div id="resultId"></div>
		</div>

		<div>
			<label for="userPwd">Password</label> <input type="password"
				name="userPwd" id="userPwd">
		</div>

		<%-- 비밀번호 양식이 맞는지 확인(10자이상, 영문대소문자, 특수문자 하나 이상) --%>

		<div>
			<span class="pwdformchk"></span>
		</div>



		<div>
			<label for="userPwdCheck">Password 확인</label> 
			<input type="password" name="userPwdCheck" id="userPwdCheck">
			<button type="button" onclick="chkpwd();">확인</button>
		</div>
		<%-- 입력한 비밀번호와 일치하는지 확인 --%>
		<div>
			<span class="alertpwd"></span>
		</div>


		<div>
			<label for="userEmail">E-mail</label> <input type="text"
				name="userEmail" id="userEmail">
		</div>
		<div>
			<label for="userBirth">생년월일</label> <select id="birthYear"
				name="birthYear">
			</select> <select id="birthMonth" name="birthMonth">
			</select> <select id="birthDate" name="birthDate">
			</select>
		</div>

		<div>
			<label for="userContact">연락처</label> <select id="contact1"
				name="contact1">
				<option value="010">010</option>
			</select> <input type="text" id="contact2" name="contact2"> <input
				type="text" id="contact3" name="contact3">

		</div>

		<div>
			<h3>프로필 사진 설정</h3>
			<div class="preViewContainer" id="previewContainer"></div>
			<input type="file" id="profilePhoto" name="profilePhoto"
				accept="image/*" onchange="setPreview(event);" />
		</div>

		<div>
			<label for="userNick"> 닉네임 설정</label> 
			<input type="text" name="userNick" id="userNick">
			<button type="button" id="userNickBtn">확인</button>
			<div id="result"></div>
		</div>


		<div>
			<label for="userName"> 이름</label> <input type="text" name="userName"
				id="userName">
		</div>

		<div>
			<label> 성별</label> <label for="pickGender">남</label> <input
				type="radio" name="pickGender" id="pickMale" value="M" checked>
			<label for="pickFemale">여</label> <input type="radio"
				name="pickGender" id="pickfeMale" value="F">
		</div>

		<button type="button">취소</button>
		<button type="submit">확인</button>

	</form>


</body>
</html>