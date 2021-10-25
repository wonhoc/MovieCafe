<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--joinUserForm.jsp --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"
			  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
			  crossorigin="anonymous"></script>
		<script>
		
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
		<%-- 아이디 중복 검사--%>

		
		
			function idValueChk() {
				var inputId = document.userId.value();
				
			}
		<%-- 새롭게 아이디를 입력했을 경우 중복검사를 거치지 않은 것으로 변경되도록 함--%>
			function inputIdCheck() {
				document.idDuplication.value = "idUncheck";
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

		</script>
		<style>
			.preViewContainer {
				width: 100px;
				height: 100px;
				object-fit : cover;
				background-color: gray;
			}

		</style>
		
		
		<title>회원 가입 폼</title>
	</head>
<body>
	<form action="${pageContext.request.contextPath}/joinUser" method="POST" enctype="multipart/form-data">
		<div>
			<label for ="userId">ID</label>
			<input type="text" name="userId" id="userId">
			<input type="button" id="requestChkId" name="requestChkId" value="중복확인">
			<div id="resultOk"></div>
		</div>
		
		<script>
		
		 const getAjax = function(url,inputId) {
	            return new Promise( (resolve, reject) => {
	                $.ajax({                        
	                    url: url,
	                    method: 'POST',
	                    dataType: 'json',
	                    data: {
	                    	inputId: inputId           
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
		
		
		async function requestProcess(url, inputId) {
            try {
                const inputIds = await getAjax(url, inputId);
                 
                var htmlStr = '<p>'+ ${inputIds.chkOk} + '</p>' ;
                
                
                $('body > #resultOk').html(htmlStr);
                
                  
            } catch (error) {
                console.log("error : ", error);   
            }
        }
		
		$('#requestChkId').on('click',function(){
			const inputId = $('#userId').val();
			requestProcess('${pageContext.request.contextPath}/checkId.do', inputId);
			
			
		});
		
		
		
		</script>
		
		<div>
			<label for="userPwd">Password</label>
			<input type="password" name="userPwd" id="userPwd">	
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
			<label for="userEmail">E-mail</label>
			<input type="text" name="userEmail" id="userEmail">
		</div>
		<div>
			<label for="userBirth" >생년월일</label>
			<select id="birthYear" name="birthYear">
				<option value="1990">1990</option>
				<option value="1991" selected>1991</option>
			</select>
			<select id="birthMonth" name="birthMonth">	
				<option value="01">01</option>
			</select>
			<select id="birthDate" name="birthDate">
				<option value="13">13</option>
			</select>
		</div>

		<div>
			<label for="userContact">연락처</label>
			<select id="contact1" name="contact1">
				<option value="010">010</option>
			</select>
			<input type="text" id="contact2" name="contact2">
			<input type="text" id="contact3" name="contact3">
			
		</div>
		
		<div>
			<h3> 프로필 사진 설정</h3>
			<div class = "preViewContainer" id ="previewContainer"></div>
			<input type="file" id="profilePhoto" name="profilePhoto" accept="image/*" onchange="setPreview(event);"/>
		</div>	
		
		<div>
			<label for="userNick"> 닉네임 설정</label>
			<input type="text" name="userNick" id="userNick">
			<button type="button"> 중복 </button>
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