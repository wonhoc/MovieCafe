<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>패스워드 확인</title>
	<style>
.pwd {
  width: 70%;
  padding: 5em;
  margin: auto;
}
.pwd_title {
  margin: 0.3em 0;
  font-size: 40px;
}
.pwd_divide {
  width: 20%;
  height: 3px;
  background-color: black;
}
.pwd_notice {
  font-size: 24px;
}

.pwdInput_box {
  margin: 3em;
  text-align: center;
}
.pwd_input {
  width: 15rem;
  height: 2rem;
  border: 2px solid;
  border-radius: 0.25em;
}
.pwdBtn_box {
  text-align: end;
}
.pwd_btn {
  height: 2rem;
  margin: 0 0.3em;
  font-size: 18px;
  color: #454545;
  background-color: #fdfdfd;
  border: 2px solid #454545;
  border-radius: 0.25em;
  transition: all 150ms ease-in;
  cursor : pointer;
}
.pwd_btn:hover {
  background-color: #e2e2e2;
}
	</style>
</head>
<body >
	<section class="pwd">
        <h1 class="pwd_title">Password 입력</h1>
        <div class="pwd_divide"></div>
        <h3 class="pwd_notice">
          개인정보 보호를 위해 비밀번호를 다시 한번 입력해주세요
        </h3>
        <form
          action="${pageContext.request.contextPath}/pwdCheck.do"
          method="POST"
        >
          <div class="pwdInput_box">
            <input
              class="pwd_input"
              type="password"
              name="userPwd"
              id="userPwd"
            />
          </div>
          <div class="pwdBtn_box">
            <button class="pwd_btn" type="submit">확인</button>
          </div>
        </form>
      </section>
</body>
</html>