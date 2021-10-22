<%-- listBoard.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*, domain.board.BoardVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang='ko'>
<head>
<meta charset='UTF-8'>
        <title>게시판 목록보기</title>


</head>
	<body>
	<center><h1>게시글 목록 조회</h1></center>

	<table>
	
	<thead>
	<tr>
		<th>번호</th><th>작성자</th><th>제목</th><th>작성일자</th><th>조회</th><th>추천</th><th>댓글</th>
	</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${requestScope.boards}" varStatus="loop">
			<c:url var="url" value="detailBoard.do">
				<c:param name ="no" value="${pageScope.board.no}"/>
			</c:url>
				<tr>
					<td>
				</tr>
		
		
		</c:forEach>	
	
	</tbody>
	
	
	
	</table>

	</body>
</html>


