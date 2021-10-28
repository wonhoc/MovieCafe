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
<style>
content {
  width: 100%;
}
.join_title {
  font-size: 36px;
    font-weight: bolder;
    margin: 0.3em 0;
}
.join_notice {
  padding: 3em 0 2em 5em;
}
.join_devide {
  width: 30%;
  height: 4px;
  background-color: black;
}
.join_text {
  margin: 0.25em;
}

.input_section {
  display: flex;
  align-items: center;
}
.input_title {
  margin-right: 1em;
}

.input_box {
  height: 1.5rem;
  outline: auto;
  border-radius: 0.25em;
}

.join_box {
  display: flex;
  width: 100%;
  justify-content: space-around;
}

button {
  cursor: pointer;
  height: 1.5rem;
  margin: 0 0.3em;
  font-size: 18px;
  color: #454545;
  background-color: #fdfdfd;
  border: 2px solid #454545;
  border-radius: 0.25em;
  transition: all 150ms ease-in;
}
button:hover {
  background-color: #e2e2e2;
}

#profilePhoto {
  outline: none;
}

.form_imgBtn label {
  margin-top: 1em;
  display: inline-block;
  padding: 0.5em 0.75em;
  color: #404040;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #fdfdfd;
  cursor: pointer;
  border: 1px solid #626262;
  border-radius: 0.25em;
}
.form_imgBtn input[type="file"] {
  /* 파일 필드 숨기기 */
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}

#img {
  border-radius: 50%;
  width: 8rem;
  height: 8rem;
  border: 1px solid;
}

.btn_box {
  margin-top: 3em;
  text-align: center;
}
.submitBtn {
  width: 3em;
  height: 1.7em;
  padding: 0.25em;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	
	
	<%--프로필 사진 미리보기--%>
    $("#imgInput").on("change", function handleImgFileSelect(e) {
        let files = e.target.files;
        let filesArr = Array.prototype.slice.call(files);
        filesArr.forEach(function (f) {
          if (!f.type.match("image.*")) {
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
          }
          let reader = new FileReader();
          reader.onload = function (e) {
            $("#img").attr("src", e.target.result);
          };
          reader.readAsDataURL(f);
        });
      });
    
	
	$('input:radio[name=pickGender]:input[value=${requestScope.user.gender}]').attr("checked", true);
	$('input:radio[name=pickGender]').attr("disabled", true);
				
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
		$('#birthMonth').val('${pageScope.month}').prop("selected", true);
	}

	function appendDate() {
		for (var i = 1; i <= 31; i++) {
			if (i < 10) {
				$("#birthDate").append("<option value='0" + i + "'>0" + i + "</option>");
			} else {
				$("#birthDate").append("<option value='" + i + "'>" + i + "</option>");
			}

		}
		$('#birthDate').val('${pageScope.date}').prop("selected", true);
	}

	appendYear();
	appendMonth();
	appendDate();

	<%--입력한 비밀번호 재확인 --%>
	
	$('#pwChkBtn').on('click', function() {
		var p1 = document.getElementById('userPwd').value;
		var p2 = document.getElementById('userPwdCheck').value;
		if(p1 != p2) {
			pwdcheck.innerText="비밀번호가 일치하지 않습니다. 다시 입력해주세요.";
			pwdcheck.style.color="red";
			$('#isSubmitBtn').attr("disabled",true);
		} else {
			pwdcheck.innerText="비밀번호가 일치합니다.";
			pwdcheck.style.color="blue";
			$('#isSubmitBtn').attr("disabled",false);
		};	
	});
	
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
	                	$('#isSubmitBtn').attr("disabled",true);
	                	
	                }else{
	                	$('#result').text('사용 가능한 닉네임입니다.');
	                	$('#result').css('color', 'blue');
	                	$('#isSubmitBtn').attr("disabled",false);
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
<section>
<div class="join_notice">
            <h1 class="join_title">회원 정보 수정</h1>
            <div class="join_devide"></div>
            <p class="join_text">회원정보를 수정할 수 있습니다.</p>
          </div>
	<form action="${pageContext.request.contextPath}/uploadFile"
		method="POST" enctype="multipart/form-data">
		<div class="join_box">
			<div class="join_left">
				<div class="input_section">
					<h3 class="input_title">ID</h3> 
					<input class="input_box" type="text" name="userId" id="userId" 
					value="${requestScope.user.userId}" readonly>
				</div>
			
			<div class="input_section">
			<h3 class="input_title">Password</h3> 
			<input class="input_box" type="password" name="userPwd" id="userPwd" 
			value="${requestScope.user.userPwd}">
		</div>
		<div class="input_section">
			<h3 class="input_title">Password 확인</h3>
			<input class="input_box" type="password" name="userPwdCheck" id="userPwdCheck">
            <button type="button" id = "pwChkBtn">확인</button>
		</div>
		<div>
              <span id="pwdcheck"></span>
        </div>
        <div>
              <span class="pwdformchk"></span>
        </div>
		<div class="input_section">
			<h3 class="input_title">E-mail</h3> 
			 <input class="input_box" type="text" name="userEmail" id="userEmail"
				value="${requestScope.user.userEmail}">
		</div>
		<div class="input_section">
			<h3 class="input_title">생년월일</h3>
			<select id="birthYear" name="birthYear"></select> 
            <select id="birthMonth" name="birthMonth"></select> 
            <select id="birthDate" name="birthDate"></select>
		</div>

		<div class="input_section">
			 <div class="input_section"><h3 class="input_title">연락처</h3>
			    <input id="contact1" name="contact1" value="${pageScope.contact1 }">
				<input id="contact2" name="contact2" value="${pageScope.contact2 }">
				<input id="contact3" name="contact3" value="${pageScope.contact3 }">
		</div>
		
		</div>
		<div class="input_section">
			<h3 class="input_title"> 닉네임 설정</h3> 
			<input class="input_box" type="text" name="userNick" id="userNick"
			 value="${requestScope.user.userNick}">
			<button type="button" id="userNicBtn">확인</button>
			<div id="result"></div>
		</div>
		</div>
		<div class="join_right">
			<img id="img">
			<label for="photoSys">이미지 등록</label> 
			<input type="file" class="form_imgInput" id="photoSys" name="photoSys" 
			value="${requestScope.user.photoSys}">
			<div class="input_section">
			<h3 class="input_title"> 이름</h3>  
			<input class="input_box" type="text" name="userName" id="userName"
			 value="${requestScope.user.userName}">
		</div>

		<div class="input_section">
			<h3 class="input_title"> 성별</h3>
			 <label for="pickMale">남</label> 
			 <input type="radio" name="pickGender" id="pickMale" value="M"> 
			 <label for="pickFemale">여</label>
			 <input type="radio" name="pickGender" id="pickfeMale" value="F">
		</div>
			
		</div>

		
		</div>
		<div class="btn_box">
		<button class="submitBtn" type="button">취소</button>
		 <button class="submitBtn" type="submit">확인</button>
		</div>
	</form>

</section>
</body>
</html>