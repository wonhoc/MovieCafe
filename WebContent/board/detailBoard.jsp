<%@ page contentType="text/html; charset=utf-8" %>
   <%@ page import="java.util.*, domain.board.BoardVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
                width: 700px;
                border-collapse: collapse;
                margin: 50px auto;   
                font-size: 12px;             
            }

            table, tr, th, td {
                border: 1px solid blue;
                text-align: center;
            }
            
            th, td {
            	height: 35px;
            }

            h3 {
                text-align: center;
            }


        </style>


</head>
<body>

<div id = "div">
	<c:url var="modifyUrl" value="/modifyBoardForm.do">
		<c:param name="boardNo" value="${requestScope.board.boardNo}"/>
	</c:url>
	<a href="${modifyUrl}">수정</a>&nbsp;&nbsp;
	
	<c:url var="removeUrl" value="/removeBoard.do">
		<c:param name="boardNo" value="${requestScope.board.boardNo}"/>
	</c:url>
	<a href="${removeUrl}">삭제</a>&nbsp;&nbsp;
	
	<c:url var="listUrl" value="/board/listBoard.do">
		<c:param name="boardNo" value="${requestScope.board.boardNo}"/>
	</c:url>
	<a href="${listUrl}">목록보기</a>&nbsp;&nbsp;
</div>

<table>
		<tr>
			<td>장르</td>		
			<td>${requestScope.board.horseNo}</td>		
			<td>제목</td>		
			<td>${requestScope.board.boardTitle}</td>		
		</tr>
</table>
<table>
		<tr>
			<td>작성자</td>		
			<td>${requestScope.board.userId}</td>
			<td>${requestScope.board.boardWdate}</td>
		</tr>
</table>
<table>
		<tr>
			<td>${requestScope.board.boardContent}</td>		
		</tr>
</table>

<%-- 추천기능, 신고기능 --%>


<%-- 파일리스트 --%>
<c:if test = "${not empty requestScope.board.boardfileList}">
	<table id ="tbl">
		<tr>
			<th>파일명</th>
			<th>파일크기</th>
		</tr>
	<c:forEach var="file" items="${requestScope.board.boardfileList}">
			<c:url var = "downloadUrl" value="/fileDownload">
				<c:param name="boardfileOrigin" value="${file.boardfileOrigin}"/>
				<c:param name="boardfileSys" value="${file.boardfileSys}"/>
			</c:url>
		<tr>
			<td><a href="${downloadUrl}">${file.boardfileOrigin}</a></td>
			<td>${file.boardfileSize}</td>
		</tr>	
	</c:forEach>	
	</table>
</c:if>



</body>
</html>