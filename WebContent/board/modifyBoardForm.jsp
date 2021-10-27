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
			const boardfileNo = $(this).attr('id')
			location.href = '${pageContext.request.contextPath}/removeFile.do?boardfileNo=' + boardfileNo;
			
		});
	
	
	});

</script>
</head>
<body>
<div class="container" role="main">
<h1>게시글 수정</h1>
	<form action="${pageContext.request.contextPath}/modifyBoardForm" method="POST" enctype="multipart/form-data">
		 <%-- 세션에 있는 아이디  --%>
	 <input type="hidden" name = "userId" value="test_user01">
	<input type="hidden" id="apiX" value="${sessionScope.board.apiX}">
	<input type="hidden" id="apiY" value="${sessionScope.board.apiY}">
	 <%-- 말머리(나중에 수정) --%>
	 
	<c:if test="${param.cateNo == 1}">
	 <div class="mb-3">
			
	<input type="hidden" name="horseNo" value="1"> 
	<input type="hidden" name="cateNo" value= "2">           
        </div>
         </c:if>
        <c:if test="${param.cateNo == 2}">
	 <div class="mb-3">
			
            <label>말머리선택</label>
            <select name="horseNo" id="horseNo">
               
                <option value="2">로맨스</option>
                <option value="3">스릴러</option>
                <option value="4">공포</option>
                <option value="5">SF판타지</option>
                <option value="6">액션</option>
                <option value="7">코미디</option>
                
            </select>
            <input type="hidden" name="cateNo" value= "2">
        </div>
         </c:if>
         <c:if test="${param.cateNo == 3}">
	 <div class="mb-3">
			
            <label>말머리선택</label>
            <select name="horseNo" id="horseNo">
               
                <option value="8">다녀온 시사회</option>
                <option value="9">예정 시사회</option>
                
                
            </select>
            
            <input type="hidden" name="cateNo" value= "3">
        </div>
         </c:if>
         
         <c:if test="${param.cateNo == 5}">
	 <div class="mb-3">
			
            <label>말머리선택</label>
            <select name="horseNo" id="horseNo">
               
                <option value="12">판매</option>
                <option value="13">구매</option>
                <option value="14">나눔</option>
                
            </select>
            <input type="hidden" name="cateNo" value= "5">
        </div>
         </c:if>
         	<c:if test="${param.cateNo == 6}">
	 <div class="mb-3">
			
	<input type="hidden" name="horseNo" value="15">       
	<input type="hidden" name="cateNo" value= "6">     
        </div>
         </c:if>
         
        
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
		<input type="hidden" name= "cateNo" value="${requestScope.cateNo }">
			<button type="submit" class="btn btn-sm btn-primary" id="modifyBtn">
			 
			수정</button>
			
			<c:if test="${param.cateNo == 1}"> 
			<button type="button" class="btn btn-sm btn-primary" id="listBtn" onclick="location='/board/listBoard_NewMem.do'">목록</button>
			</c:if>
			<c:if test="${param.cateNo == 2}"> 
			<button type="button" class="btn btn-sm btn-primary" id="listBtn" onclick="location='/board/listBoard_MovieReview.do'">목록</button>
			</c:if>
			<c:if test="${param.cateNo == 3}"> 
			<button type="button" class="btn btn-sm btn-primary" id="listBtn" onclick="location='/board/listBoard_sisa.do'">목록</button>
			</c:if>
			
			<c:if test="${param.cateNo == 5}"> 
			<button type="button" class="btn btn-sm btn-primary" id="listBtn" onclick="location='/board/listBoard_Ticket.do'">목록</button>
			</c:if>
			<c:if test="${param.cateNo == 6}"> 
			<button type="button" class="btn btn-sm btn-primary" id="listBtn" onclick="location='/board/listBoard_Event.do'">목록</button>
			</c:if>
			<input type="checkbox" value ="1" name="boardNotice"><label>공지</label>
		</div>
		<%-- 관리자 아이디 체크박스 --%>
		<input type ="hidden" id="apiX" name = "apiX" value="">
		<input type ="hidden" id="apiY" name = "apiY" value="">
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
			<td>${file.boardfileSize}</td>
			<td><button class="removeBtn" id="${file.boardfileNo}">삭제</button></td>
		</tr>
		
		
		</c:forEach>
	</table>
</c:if>

</body>
</html>