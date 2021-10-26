<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>


body {
  width : 100%;
}
.review {
  padding: 3em 5em;
  display: flex;
  justify-content: center;
}

.review_img {
  width: 20rem;
  height: 25rem;
  margin-right: 5em;
}

.review_title {
  font-size: 30px;
  text-align: initial;
}

.review_right {
  width: 80%;
}

.review_input {
  display: flex;
  width: 100%;
  align-items: center;
}

.input_box,
.select_box {
  margin-left: 2em;
  width: 25rem;
  height: 3rem;
  border: 3px solid black;
  border-radius: 0.25em;
}

.rating_title,
.input_title {
  font-size: 24px;
}

.review_rating {
  display: flex;
  width: 100%;
  align-items: center;
}

.select_box {
  margin-left: 7em;
  width: 15rem;
}

.review_btn {
  height: 2rem;
  margin: 0 0.5em;
  font-size: 18px;
  color: #a7a6a6;
  background-color: #fdfdfd;
  border: 2px solid #b1b1b1;
  border-bottom-color: #b1b1b1;
  border-radius: 0.25em;
  transition: all 150ms ease-in;
  cursor : pointer;
}
.review_btn:hover {
  background-color: #e2e2e2;
}
.review_bottom {
padding : 10em 10em 0 0;
  text-align : end;
}
</style>
</head>
<body>
<div class="review">
          <div class="review_left">   
            <img src="upload/movie/${movieImg}" alt="movieImg" class="review_img" />
          </div>
        <form class="review_right" id="form" action="${pageContext.request.contextPath }/registerGuanram.do" method="POST" >   
        <input type="hidden" name="movieNo" value="${param.movieNo }" />  
        <input type="hidden" name="userId" value="${userId }" />    
            <h1 class="review_title">영화 제목</h1>
            <div class="review_input">
              <h3 class="input_title">평가 한 줄</h3>
              <input type="text" class="input_box" name="review" />
            </div>
            <div class="review_rating">
              <h3 class="rating_title">평점</h3>
              <select name="rating" class="select_box" id="input_rating">
                <option value="1">⭐️</option>
                <option value="2">⭐️⭐️</option>
                <option value="3">⭐️⭐️⭐️</option>
                <option value="4">⭐️⭐️⭐️⭐️</option>
                <option value="5">⭐️⭐️⭐️⭐️⭐️</option>
              </select>
            </div>
          
          <div class="review_bottom">
        	<button class="review_btn" type="submit">등록</button>
          </div>
        </form>
       </div>
     
</body>
</html>