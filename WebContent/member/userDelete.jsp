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
<style>
section {
  width: 100%;
}

.exit_devide {
  width: 30%;
  height: 4px;
  background-color: black;
}
.exit_text {
  margin: 0.25em;
  font-weight: 600;
  font-size: 20px;
}
.exit_text_box {
  border: 2px solid black;
  border-radius: 20px;
  padding: 1em;
  width: 70%;
  margin: auto;
}
.exit_btn {
  cursor: pointer;
  height: 2rem;
  font-size: 22px;
  color: #454545;
  background-color: #fdfdfd;
  border: 2px solid #454545;
  border-radius: 0.25em;
  transition: all 150ms ease-in;
}
.exit_btn:hover {
  background-color: #e2e2e2;
}
.exit_title_box {
  margin: 3em 5em;
}
.btn_box {
  text-align: end;
  margin: 3em 5em;
}
</style>
</head>

<body>
<section>
	<div class="exit_title_box">
          <h1 class="exit_title">회원탈퇴</h1>
          <div class="exit_devide"></div>
          <h3 class="exit_notice">★정말로 탈퇴하시겠습니까?</h3>
        </div>

        <form
          action="${pageContext.request.contextPath}/userDelete.do"
          method="POST"
        >
          <div class="exit_text_box">
            <p class="exit_text">
              지금 탈퇴하시면 기존의 작성한 게시글, 댓글 등 회원님의 카페 관련
              활동내역들이 남아있게 됩니다. 기록이 남지 않기를 원하신다면 직접
              삭제 후 탈퇴 과정을 진행하시길 바랍니다. 탈퇴 후 재가입은 7일
              이후에 가능합니다 <br />
              정말 탈퇴하시겠습니까?
            </p>
          </div>
          <div class="btn_box">
            <button class="exit_btn" type="submit">확인</button>
          </div>
        </form>
        </section>
</body>
</html>