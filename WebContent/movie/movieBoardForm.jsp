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
#img {
	width : 20rem;
	height: 25rem;
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
<script
      src="https://code.jquery.com/jquery-3.6.0.min.js"
      integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
      crossorigin="anonymous"
    ></script>
<script type="text/javascript">
$(document).ready(function () {
    $("#imgInput").on("change", handleImgFileSelect);
    $('#form').submit(function() {
    	let flag = true;
    	const title = $('#title').val();
        const director = $('#director').val();
        const actor = $('#actor').val();
        const genre = $('#genre').val();
        const runtime = $('#runtime').val();
        const link = $('#link').val();
        const age = $('#age > option:selected').val();
        const date = $('#date').val();
        const image =  $("#imgInput").val()
        if (title == "" || director == "" || actor == "" || genre == "" || 
        		runtime == "" || link == "" || age == 0 || date == "") {
            alert("정보를 모두 입력해주세요");
            flag = false;
        } else if(image == "") {
        	alert("이미지 파일을 추가해주세요");
        	flag = false;
        } 
        return flag;
    });
  });
  function handleImgFileSelect(e) {
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
  }
</script>
</head>
<body>
<h1 class="content_title">영화 정보 등록</h1>
       <form id="form" action="${pageContext.request.contextPath }/uploadMovieFile" method="POST" enctype="multipart/form-data">
         <div class="movie_input">
           <div class="form_left">
             <img id="img">
             <div class="form_imgBtn">
               <label for="imgInput">이미지 등록</label>
               <input type="file" class="form_imgInput" id="imgInput" name="imgInput"/>
             </div>
           </div>
           <div class="form_right">
             <div class="form_inputSection">
               <h3 class="input_title">제목</h3>
               <input type="text" class="input_box" name="title" id="title"/>
             </div>
             <div class="form_inputSection">
               <h3 class="input_title">감독</h3>
               <input type="text" class="input_box" name="director" id="director" />
             </div>
             <div class="form_inputSection">
               <h3 class="input_title">배우</h3>
               <input type="text" class="input_box" name="actor" id="actor" />
             </div>
             <div class="form_inputSection">
               <h3 class="input_title">장르</h3>
               <input type="text" class="input_box" name="genre" id="genre"/>
            </div>
             <div class="form_inputSection">
               <h3 class="input_title">러닝타임</h3>
               <input type="text" class="input_box" name="runtime" id="runtime"/>
            </div>
             <div class="form_inputSection">
               <h3 class="input_title">예매처 링크</h3>
               <input type="text" class="input_box" name="link" id="link"/>
             </div>
             <div class="form_inputSection">
               <h3 class="input_title">관람가</h3>
               <select name="age" class="input_box" id="age">
                  <option value="0">관람가 지정</option>
                  <option value="A">전체 관람가</option>
                  <option value="B">12세 이상 관람가</option>
                  <option value="C">15세 이상 관람가</option>
                  <option value="D">청소년 관람불가</option>
                </select>
             </div>
             <div class="form_inputSection">
               <h3 class="input_title">상영날짜</h3>
               <input type="text" class="input_box" name="date" placeholder="YYYY-MM-DD로 작성해주세요" id="date"/>
             </div>
           </div>
         </div>
         <button type="submit" class="form_btn" id="submitBtn">등록</button>
       </form>
</body>
</html>