<%-- listBoard.jsp --%>
<%@page import="controller.category.Category"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관람 팁 게시판목록보기</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
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
            
            #paging {
            	margin: 10px auto;
            }

        </style>
</head>
<body>

 <center><h3>게시글 목록 조회</h3></center>
 
 <table>
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>작성일자</th>
		<th>조회</th>
		<th>추천</th>
		<th>댓글</th>
	</tr>
	<tbody>
		<c:if test="${empty requestScope.boards}">
			<tr><td colspan="7">등록된 게시글이 없습니다.</td></tr>	
		</c:if>
		<c:if test="${not empty requestScope.boards}">			
			<c:forEach var="board" items="${requestScope.boards}" varStatus="status">
				<tr>
					<c:url var="detailUrl" value="/board/detailBoard.do">
						<c:param name= "boardNo" value="${pageScope.board.boardNo}"/>
					</c:url>				
					<td>${requestScope.totalPostCount - ((param.currentPage - 1) * requestScope.postSize) - status.index } </td>
					<td>${board.userId}</td>
					<td><a href="${detailUrl}">${board.boardTitle}</a></td>
					<td>${board.boardWdate}</td>
					<td>${board.hitCount}</td>
					<td>${board.recomCount}</td>
					<td>${board.commentCount}</td>
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
			<c:url var="prevUrl" value = "/board/listBoard.do">
				<c:param name = "currentPage" value = "${startPage - pageBlock}"/>
			</c:url>
			<a href = "${prevUrl}">[Prev]</a>
		</c:if>
		<c:forEach var = "i" begin="${startPage}" end= "${endPage}">
		<c:if test ="${i == currentPage}">
				&nbsp;${i}&nbsp;
		</c:if>
		<c:if test="${i != currentPage}">
			<c:url var="url" value = "/board/listBoard.do">
				<c:param name="currentPage" value ="${i}"/>
			</c:url>
			<a href ="${url}">&nbsp;${i}&nbsp;</a>
		</c:if>
		</c:forEach>
		<c:if test="${endPage < totalPage}">
			<c:url var="nextUrl" value ="/board/listBoard.do">
					<c:param name="currentPage" value ="${endPage +1}"/>
			</c:url>
			<a href="${nextUrl}">[Next]</a>
		</c:if>
	</div>

</body>
</html>