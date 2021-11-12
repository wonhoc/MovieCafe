<%-- listBoard.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*, domain.board.BoardVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang='ko'>
<head>
<meta charset='UTF-8'>
<title>게시판 목록보기</title>

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
<%-- 테스트--%>

</head>
<body>
	<center>
		<h1>게시글 목록 조회</h1>
	</center>

	<table>

		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일자</th>
				<th>조회</th>
				<th>추천</th>
				<th>댓글</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty requestScope.boards}">
				<tr>
					<td colspan="4">등록된 게시글이 없습니다.</td>
				</tr>
			</c:if>
			
			<c:if test="${not empty requestScope.boards}">
			<c:forEach var="board" items="${requestScope.boards}" varStatus="loop">
				<tr>
					<td>${requestScope.totalPostCount - (param.currentPage-1)*requestScope.postSize - loop.index }</td>
					<td>${pageScope.board.userId} </td>
					<td><a href="${pageScope.url}">${pageScope.board.boardTitle}</a></td>
					<td>${pageScope.board.boardWdate}</td>
					<td>${pageScope.board.hitcount}</td>
					<td>${pageScope.board.recomCount}</td>
					<td>${pageScope.board.commentCount}</td>
				</tr>
			</c:forEach>
			</c:if>
			
		</tbody>
	</table>

	<%-- 페이징 --%>
	<div id = "paging">
		<c:set var = "pageBlock" value = "${requestScope.pageBlock}" scope="page"/>
		<c:set var = "startPage" value = "${requestScope.startPage}" scope="page"/>
		<c:set var = "endPage" value = "${requestScope.endPage}" scope="page"/>
		<c:set var = "totalPage" value = "${requestScope.totalPage}" scope="page"/>
		<c:set var = "currentPage" value = "${requestScope.currentPage}" scope="page"/>

		<c:if test="${startPage > pageBlock}">
			<c:url var="preUrl" value = "/listBoard.do">
				<c:param name = "currentPage" value = "${startPage - pageBlock}"/>
			</c:url>
			<a href = "${preUrl}">[Prev]</a>
		</c:if>
		<c:forEach var = "i" begin="${startPage}" end= "${endPage}">
		<c:if test ="${i == currentPage}">
				&nbsp;${i}&nbsp;
		</c:if>
		<c:if test="${i != currentPage}">
			<c:url var="url" value = "/listBoard.do">
				<c:param name="currentPage" value ="${i}"/>
			</c:url>
			<a href ="${url}">&nbsp;${i}&nbsp;</a>
		</c:if>
		</c:forEach>
		<c:if test="${endPage < totalPage}">
			<c:url var="nextUrl" value ="/listBoard.do">
					<c:param name="currentPage" value ="${endpage +1}"/>
			</c:url>
			<a herf="${nextUrl}">[Next]</a>
		</c:if>
	</div>

</body>
</html>


