<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Movie Info</title>
<style>
.content_title {
  padding: 2em 0 0 2em;
}

.movie_input {
  display: flex;
  width: 100%;
  padding: 2em 5em;
  justify-content: space-evenly;
  align-items: center;
}

.form_imgBox {
  width: 20rem;
  height: 25rem;
  border: 2px solid grey;
  border-radius: var(--size-border-radius);
}

.form_imgBtn label {
  margin-top: 1em;
  display: inline-block;
  padding: 0.5em 0.75em;
  color: #999;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #fdfdfd;
  cursor: pointer;
  border: 1px solid #ebebeb;
  border-bottom-color: #e2e2e2;
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

.form_imgInputText {
  text-align: center;
  line-height: 25rem;
  color: #b1b1b1;
}

.form_inputSection {
  display: flex;
  align-items: center;
  width: 20rem;
}

.input_title {
  flex: 2;
}

.input_box {
  flex: 3;
  height: 2em;
  border: 3px solid black;
  border-radius: 0.25em;
}

.form_btn {
  margin-left: 25em;
  padding: 0.5em 0.75em;
  color: #b1b1b1;
  font-size: 20px;
  background-color: #fdfdfd;
  border: 2px solid #b1b1b1;
  border-bottom-color: #b1b1b1;
  border-radius: 0.25em;
  transition: all 150ms ease-in;
}
.form_btn:hover {
  background-color: #e2e2e2;
}
</style>
</head>
<body>
<h1 class="content_title">영화 정보 등록</h1>
       <form  action="${pageContext.request.contextPath }/uploadMovieFile" method="POST" enctype="multipart/form-data">
         <div class="movie_input">
           <div class="form_left">
             <div class="form_imgBox">
               <p class="form_imgInputText">이미지를 추가하세요</p>
             </div>
             <div class="form_imgBtn">
               <label for="imgInput">이미지 등록</label>
               <input type="file" class="form_imgInput" id="imgInput" name="imgInput"/>
             </div>
           </div>
           <div class="form_right">
             <div class="form_inputSection">
               <h3 class="input_title">제목</h3>
               <input type="text" class="input_box" name="title"/>
             </div>
             <div class="form_inputSection">
               <h3 class="input_title">감독</h3>
               <input type="text" class="input_box" name="director"/>
             </div>
             <div class="form_inputSection">
               <h3 class="input_title">배우</h3>
               <input type="text" class="input_box" name="actor"/>
             </div>
             <div class="form_inputSection">
               <h3 class="input_title">장르</h3>
               <input type="text" class="input_box" name="genre"/>
            </div>
             <div class="form_inputSection">
               <h3 class="input_title">러닝타임</h3>
               <input type="text" class="input_box" name="runtime"/>
            </div>
             <div class="form_inputSection">
               <h3 class="input_title">예매처 링크</h3>
               <input type="text" class="input_box" name="link"/>
             </div>
             <div class="form_inputSection">
               <h3 class="input_title">관람가</h3>
               <select name="age" class="input_box">
                  <option value="0">관람가 지정</option>
                  <option value="A">전체 관람가</option>
                  <option value="B">12세 이상 관람가</option>
                  <option value="C">15세 이상 관람가</option>
                  <option value="D">청소년 관람불가</option>
                </select>
             </div>
             <div class="form_inputSection">
               <h3 class="input_title">상영날짜</h3>
               <input type="text" class="input_box" name="date" placeholder="YYYY-MM-DD로 작성해주세요"/>
             </div>
           </div>
         </div>
         <button type="submit" class="form_btn">등록</button>
       </form>
</body>
</html>