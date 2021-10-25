<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정폼</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
 <style>
 body {                
       margin: 10px auto;
       width: 500px;
       padding-top: 50px;
       padding-bottom: 30px;
       }
</style>

<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>
	$(document).ready(function() {
		$('.removeBtn').on('click' , function() {
			const boardfileNo = $(this).attr('id');
			console.log('boardfileNo : ', boardfileNo);
			$('location').attr('href', '${pageContext.request.contextPath}/removeFile.do?boardfileNo=' + boardfileNo);
		});
	
	
	});

</script>
</head>
<body>
<div class="container" role="main">
<h1>게시글 수정</h1>
	<form action="${pageContext.request.contextPath}/modifyBoard" method="POST" enctype="multipart/form-data">
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
        	<input type="text" class="form-control" name="boardTitle" id = "boardTitle" value="${sessionScope.board.boardTitle}">
        </div>
        
        <div class="mb-3">
        	<input type="text" class="form-control" name="boardContent" id = "boardContent" value="${sessionScope.board.boardContent}" style="height: 300px;">
        </div>
        
        
		<div class="mb-3">
			<label>파일</label>
			<input type="file" class="form-control" name = "fileList" multiple>
		</div>
		
		<div>
			<button type="submit" class="btn btn-sm btn-primary" id="modifyBtn">수정</button>
			<button type="button" class="btn btn-sm btn-primary" id="listBtn">목록</button>
			<input type="checkbox" value ="1" name="boardNotice"><label>공지</label>
		</div>
		<%-- 관리자 아이디 체크박스 --%>
		
	</form>
</div>

<c:if test="${not empty sessionScope.board.boardfileList}">
	<table id ="tbl">
		<tr>
			<th>파일명</th><th>파일크기</th><th>비고</th>
		</tr>
		
		<c:forEach var="file" items="${sessionScope.board.boardfileList}">
		<tr>
			<td>${file.boardfileOrigin}</td>
			<td>${file.boardfileSys}</td>
			<td><button class="removeBtn" id="${file.boardfileNo}">삭제</button></td>
		</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>