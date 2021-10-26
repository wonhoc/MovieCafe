<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.board.BoardVo"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천 리뷰</title>
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

	<h3>추천 리뷰</h3>
	
		<table>
		<thead>
			<th>제목</th><th>닉네임</th><th>작성일</th><th>조회수</th><th>추천</th><th>댓글</th>
		</thead>
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
		</table>
		<h3>공지사항</h3>
		<table>
		<thead>
			<th> </th><th>제목</th><th>닉네임</th><th>작성일</th><th>조회수</th><th>추천수</th>
		</thead>
		<c:forEach var="noticeBoard" items="${requestScope.noticeBoards}"
				varStatus="loop">
				<c:url var="url" value="/board/detailBoard.do">
					<c:param name="boardNo" value="${pageScope.noticeBoard.boardNo}"></c:param>
					<c:param name="cateNo" value="${pageScope.noticeBoard.cateNo}"></c:param>
				</c:url>
				<tr>
					<td style="font-style:red;">필독!!</td>
					<td><a href="${pageScope.url}">${pageScope.noticeBoard.boardTitle }</a></td>
					<td>${pageScope.noticeBoard.userNick }</td>
					<td>${pageScope.noticeBoard.boardWdate }</td>
					<td>${pageScope.noticeBoard.boardCount }</td>
					<td>${pageScope.noticeBoard.recomCount }</td>
				</tr>
			</c:forEach>
		</table>
	
</body>
</html>