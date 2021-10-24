<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aside</title>
<style>
.aside_login {
  padding: 1em;
  font-weight: 400;
}

.login_divide {
  background-color: #ed7c31;
  width: 100%;
  height: 5px;
}

.login_orangeBox {
  padding: 0.3em;
  background-color: #ed7c31;
  border-radius: 30px;
}

.login_whiteBox {
  padding: 1em;
  background-color: white;
  border-radius: 30px;
}

.login_box {
  padding: 1em;
}

.login_icon {
  width: 2em;
  height: 2em;
}

.login_title {
  font-size: 28px;
  font-weight: 700;
}

.login_input {
  margin-top: 1em;
  text-align-last: justify;
}

.login_inputBox {
  width: 8em;
  outline: auto;
}

.login_btnBox {
  margin-top: 1em;
  text-align: center;
}

.login_btn {
  margin: 0 1em;
}

.aside_category {
  padding: 0.8em;
}

.category_orangeBox {
  padding: 0.3em;
  background-color: #ed7c31;
  border-radius: 30px;
}

.category_whiteBox {
  padding: 2em;
  background-color: white;
  border-radius: 30px;
}

.category_section {
  display: flex;
  align-items: center;
}

.categoty_title {
  cursor: pointer;
  color: #ed7c31;
}
.categoty_title:hover {
  color: #f9da78;
}

.category_icon {
  width: 2em;
  height: 2em;
  margin-left: 0.5em;
}
</style>
</head>
<body>
<div class="aside_login">
        <div class="login_orangeBox">
          <div class="login_whiteBox">
            <img src="images/login.png" alt="login_icon" class="login_icon" />
            <span class="login_title">로그인</span>
            <div class="login_divide"></div>
            <div class="login_input">
              <span>아이디</span>
              <input class="login_inputBox" type="text" />
            </div>
            <div class="login_input">
              <span>비밀번호</span>
              <input class="login_inputBox" type="password" />
            </div>
            <div class="login_btnBox">
              <button class="login_btn">로그인</button>
              <button class="login_btn">회원가입</button>
            </div>
          </div>
        </div>
      </div>
      <div class="aside_category">
        <div class="category_orangeBox">
          <div class="category_whiteBox">
            <div class="category_section">
              <h2 class="categoty_title">새싹 게시판</h2>
              <img
                src="images/sprout.png"
                alt="category_icon"
                class="category_icon"
              />
            </div>
            <div class="category_section">
              <h2 class="categoty_title">영화리뷰</h2>
              <img
                src="images/review.png"
                alt="category_icon"
                class="category_icon"
              />
            </div>
            <div class="category_section">
              <h2 class="categoty_title">시사회정보</h2>
              <img
                src="images/theater.png"
                alt="category_icon"
                class="category_icon"
              />
            </div>
            <div class="category_section">
              <h2 class="categoty_title">영화관람 팁</h2>
              <img src="images/tip.png" alt="category_icon" class="category_icon" />
            </div>
            <div class="category_section">
              <h2 class="categoty_title">티켓마켓</h2>
              <img
                src="images/ticket.png"
                alt="category_icon"
                class="category_icon"
              />
            </div>
            <div class="category_section">
              <h2 class="categoty_title">이벤트</h2>
              <img src="images/party.png" alt="category_icon" class="category_icon" />
            </div>
          </div>
        </div>
      </div>
</body>
</html>