<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
        <meta charset='UTF-8'>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <title>게시글 쓰기 폼</title>
        <style>
           body {                
                margin: 10px auto;
                width: 500px;
                padding-top: 50px;
                padding-bottom: 30px;
            }
        </style>
         <script>
            window.addEventListener('load', function() {
                const saveBtn = document.getElementById('saveBtn');
                saveBtn.addEventListener('click', function() {
                    const form = document.getElementById('form');
                    form.submit();
                }); 
            })

            <%-- 말머리정보 해결 --%>
            

        </script>
    </head>
    
<body>
 <article>
 	<div class="container" role="main">
<h1>게시판 작성</h1>

<%-- 게시글작성 --%>
	<form name="form" id="form" role="form" method="post" action="/movieCafeWepApp/fileUpload" enctype="multipart/form-data">
	
	 <%-- 세션에 있는 아이디  --%>
	 <input type="hidden" name = "userId" value="test_user01">
	 
	 <%-- 말머리(나중에 수정) --%>
	 <div class="mb-3">
            <label>말머리</label>
            <select name="horseNo" id="horseNo">
                <option value="10">할인정보</option>
                <option value="11">영화관 추천 </option>
            </select>
        </div>
        
        
        <div class="mb-3">
        	<input type="text" class="form-control" name="boardTitle" id = "boardTitle" placeholder="제목을 입력해주세요">
        </div>
        
        <div class="mb-3">
        	<input type="text" class="form-control" name="boardContent" id = "boardContent" placeholder="내용을 입력해주세요" style="height: 300px;">
        </div>
        
        
		<div class="mb-3">
			<label>파일</label>
			<input type="file" class="form-control" name = "fileList" multiple>
		
		</div>
		
		<div>
			<button type="button" class="btn btn-sm btn-primary" id="saveBtn">글쓰기</button>
			<button type="button" class="btn btn-sm btn-primary" id="listBtn">목록</button>
		</div>
		<%-- 관리자 아이디 체크박스 --%>
		
		<div>
			<input type="checkbox" value ="1" name="boardNotice"><label>공지</label>
		</div>
		
		
</form>
</div>
</article>
</body>
</html>