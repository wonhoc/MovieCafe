<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, domain.board.BoardVo" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>영화리뷰 게시글 목록</title>
	<style>
		.list_top {
			display: flex;
			align-items: center;
			padding: 0 8em;
			margin-top: 3em;
		}

		.list_title {
			flex: 1;
			font-size: 34px;
		}

		.list_search {
			display: contents;
		}

		#keyfield {
			height: 3em;
		}

		#keyword {
			height: 3em;
		}

		#searchBtn {
			height: 2.5em;
			width: 3em;
			padding: 0;
		}

		.table {
			height: 60%;
		}

		table {
			width: 85%;
			border-collapse: collapse;
			margin: 50px auto;
			font-size: 12px;
		}

		tr,
		th,
		td {
			border: 2px solid black;
			text-align: center;
		}

		th,
		td {
			height: 35px;
		}

		thead>tr {
			font-size: 1.8rem;
		}

		tbody>tr {
			font-size: 1.2rem;
		}

		.thead_num,
		.thead_writer,
		.thead_count,
		.thead_like,
		.thead_comm {
			width: 6rem;
		}

		.thead_title {
			width: 60rem;
		}

		.thead_date {
			width: 20rem;
		}

		.notice {
			font-weight: 600;
			color: red;
		}

		.bottom {
			display: flex;
			padding-right: 10em;
			align-items: center;
		}

		#paging {
			width: 200px;
			margin: 10px auto;
			display: flex;
			align-items: center;
			justify-content: center;
		}

		#search {
			width: 30em;
		}

		.page_num {
			font-size: 20px;
			margin: 0 0.3em;
			font-weight: 600;
		}

		.now_page {
			color: gray;
		}

		.write_btn {
			height: 3em;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>

</head>

<body>
	<div class="list_top">
		<h3 class="list_title">이벤트</h3>
		<form action="${pageContext.request.contextPath}/searchBoard.do" method="GET">
			<input type="hidden" name="cateNo" value="${requestScope.cateNo }">
			<div id="search">
				<select name="keyfield" id="keyfield">
					<option value="all">전체</option>
					<option value="board_wdate">작성일자</option>
				</select>
				<input type="search" placeholder="검색어를 입력하세요" size="30" name="keyword" id="keyword">		
				<button type="submit" id="searchBtn">검색</button>
			</div>
		</form>
	</div>
	<div class="table">
		<table>
			<thead>
				<tr>
					<th class="thead_num">번호</th>
					<th class="thead_writer">작성자</th>
					<th class="thead_title">제목</th>
					<th class="thead_date">작성일자</th>
					<th class="thead_count">조회</th>
					<th class="thead_like">추천</th>
					<th class="thead_comm">댓글</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${empty requestScope.boards}">
					<tr>
						<td colspan="7">등록된 게시글이 없습니다.</td>
					</tr>
				</c:if>

				<c:if test="${not empty requestScope.boards}">
					<c:forEach var="board" items="${requestScope.boards}" varStatus="loop">
						<c:url var="url" value="/detailBoard.do">
							<c:param name="boardNo" value="${pageScope.board.boardNo}"></c:param>

							<c:param name="cateNo" value="${param.cateNo}"></c:param>
						</c:url>
						<tr>
							<td>${requestScope.totalPostCount - (param.currentPage -1) *
								requestScope.postSize - loop.index }</td>
							<td>${pageScope.board.userId }</td>
							<c:if test="${pageScope.board.boardNotice ==1 }">
								<td><a href="${pageScope.url}"
										class="notice">${pageScope.board.boardTitle }</a></td>
							</c:if>
							<c:if test="${pageScope.board.boardNotice==0 }">
								<td><a href="${pageScope.url}">${pageScope.board.boardTitle }</a></td>
							</c:if>
							<td>${pageScope.board.boardWdate }</td>
							<td>${pageScope.board.boardCount }</td>
							<td>${pageScope.board.recomCount}</td>
							<td>${pageScope.board.commentCount}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>

	<div class="bottom">
		<%-- 페이징 처리 --%>

			<div id="paging">
				<c:set var="cateNo" value="${param.cateNo }" scope="page" />
				<c:set var="pageBlock" value="${requestScope.pageBlock}" scope="page" />
				<c:set var="startPage" value="${requestScope.startPage}" scope="page" />
				<c:set var="endPage" value="${requestScope.endPage}" scope="page" />
				<c:set var="totalPage" value="${requestScope.totalPage}" scope="page" />
				<c:set var="currentPage" value="${param.currentPage }" scope="page" />

				<c:if test="${startPage > pageBlock }">
					<c:url var="prevUrl" value="/listBoardEvent.do">
						<c:param name="cateNo" value="${param.cateNo }"></c:param>
						<c:param name="currentPage" value="${startPage - pageBlock }"></c:param>
					</c:url>
					<a href="${prevUrl}">[Prev]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<c:if test="${i == currentPage}">
						<p class="page_num now_page">${i}</p>
					</c:if>
					<c:if test="${i != currentPage}">
						<c:url var="url" value="/listBoardEvent.do">
							<c:param name="cateNo" value="${param.cateNo }"></c:param>
							<c:param name="currentPage" value="${i}" />
						</c:url>
						<a href="${url}" class="page_num">${i} </a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage < totalPage}">
					<c:url var="nextUrl" value="/listBoardEvent.do">

						<c:param name="cateNo" value="${param.cateNo }"></c:param>
						<c:param name="currentPage" value="${endPage + 1 }"></c:param>
					</c:url>
					<a href="${nextUrl}">[Next]</a>
				</c:if>
			</div>

			<c:url var="writeUrl" value="/eventWriteBoardForm.do">
				<c:param name="cateNo" value="${param.cateNo}"></c:param>
			</c:url>
			<button class="write_btn">
				<a href="${writeUrl}">글쓰기</a>
			</button>
			</form>
	</div>

</body>

</html>