<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.board.BoardVo"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<% session.setAttribute("userId","test_user01");
	pageContext.setAttribute("CR", "\n");
pageContext.setAttribute("BR", "<br>"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 게시글</title>
<style>
table {
	width: 700px;
	border-collapse: collapse;
	margin: 50px auto;
	font-size: 12px;
}

table, tr, th, td {
	border: 1px solid pink;
	text-align: center;
}

th, td {
	height: 35px;
}

h3 {
	text-align: center;
}

#paging {
	width: 200px;
	margin: 10px auto;
}

#search {
	margin: 10px auto;
	width: 500px;
}

.highlight {
	background-color: yellow;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

</head>
<body>

	<h3>나의 게시글</h3>
	
		<table>
		<thead>
			<th>제목</th><th>닉네임</th><th>작성일</th><th>조회수</th><th>추천</th><th>댓글</th>
		</thead>
		<c:if test="${empty requestScope.boards}">
				<tr>
					<td colspan="7">등록된 게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty requestScope.boards}">
				
			<c:forEach var="board" items="${requestScope.boards}"
				varStatus="loop">
				<c:url var="url" value="/board/detailBoard.do">
					<c:param name="boardNo" value="${pageScope.board.boardNo}"></c:param>
					<c:param name="cateNo" value="${pageScope.board.cateNo}"></c:param>
				</c:url>
				<tr>
					<td><a href="${pageScope.url}">${pageScope.board.boardTitle }</a></td>
					<td>${pageScope.board.userNick }</td>
					<td>${pageScope.board.boardWdate }</td>
					<td>${pageScope.board.boardCount }</td>
					<td>${pageScope.board.recomCount }</td>
					<td>${pageScope.board.commentCount }</td>
				</tr>
			</c:forEach>
			</c:if>
		</table>
		
		<%-- 페이징 처리 --%>

	<div id="paging">
	<c:set var="pageBlock" value="${requestScope.pageBlock}" scope="page" />
		<c:set var="startPage" value="${requestScope.startPage}" scope="page" />
		<c:set var="endPage" value="${requestScope.endPage}" scope="page" />
		<c:set var="totalPage" value="${requestScope.totalPage}" scope="page" />
		<c:set var="currentPage" value="${param.currentPage }" scope="page" />

		<c:if test="${startPage > pageBlock }">
			<c:url var="prevUrl" value="/board/myBoard.do">
				<c:param name="currentPage" value="${startPage - pageBlock }"></c:param>
			</c:url>
			<a href="${prevUrl}">[Prev]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<c:if test="${i == currentPage}">
			&nbsp;${i} &nbsp;
			</c:if>
			<c:if test="${i != currentPage}">
				<c:url var="url" value="/board/myBoard.do">
				<c:param name="currentPage" value="${i}" />
				</c:url>
				<a href="${url}">&nbsp;${i} &nbsp;</a>
			</c:if>
		</c:forEach>
		<c:if test="${endPage < totalPage}">
			<c:url var="nextUrl" value="/board/myBoard.do">
				<c:param name="currentPage" value="${endPage + 1 }"></c:param>
			</c:url>
			<a href="${nextUrl}">[Next]</a>
		</c:if>
	</div>
</body>
</html>