<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		/* 전체 */
		section {
			width: 100%;
		}

		.main_top {
			align-items: center;
			justify-content: space-between;
		}

		.main_divide {
			width: 90%;
			margin-left: 5%;
			margin-top: 2em;
			border-bottom: 1px dotted #8c8c8c;
		}

		.main_bottom {
			display: flex;
			justify-content: space-around;
		}

		/* Movie */
		.top_title {
			display: flex;
			align-items: center;
			justify-content: space-between;
			padding: 0 4em;
		}

		.main_title {
			padding: 0.5em 0 0 0.2em;
			font-size: 28px;
		}

		.movieList {
			display: flex;
			justify-content: space-around;
			align-items: center;
		}

		.listSection {
			display: flex;
		}

		.list_icon {
			font-size: 2rem;
			cursor: pointer;
		}

		.content_text {
			text-align: center;
			line-height: 20rem;
		}

		.paging {
			text-align: center;
		}

		.paging_icon {
			transition: transform 200ms linear;
		}

		.paging_icon:hover {
			transform: scale(1.2);
		}

		.deleteBtn {
			height: 1.5rem;
			margin-left: 1em;
			color: #b1b1b1;
			background-color: #fdfdfd;
			border: 2px solid #b1b1b1;
			border-bottom-color: #b1b1b1;
			border-radius: 0.25em;
			transition: all 150ms ease-in;
			cursor: pointer;
		}

		.deleteBtn:hover {
			background-color: #e2e2e2;
		}

		.movieImg {
			width: 10rem;
			height: 15rem;
			border-radius: 0.25em;
			transition: transform 200ms linear;
		}

		.movieImg:hover {
			transform: scale(1.05);
		}

		.add_btn {
			height: 2rem;
			margin: 0 3em;
			font-size: 18px;
			color: #a7a6a6;
			background-color: #fdfdfd;
			border: 2px solid #b1b1b1;
			border-bottom-color: #b1b1b1;
			border-radius: 0.25em;
			transition: all 150ms ease-in;
			cursor: pointer;
		}

		.add_btn:hover {
			background-color: #e2e2e2;
		}

		/* Board */

		.review_title {
			margin: 1em 0.2em;
			font-size: 28px;
		}

		table {
			width: 700px;
			border-collapse: collapse;
		}

		table {
			text-align: center;
			margin-bottom: 3em;
		}

		th,
		td {
			height: 35px;
		}

		th {
			border-top: 3px solid #ed7c31;
			border-bottom: 3px solid #ed7c31;
			font-size: 22px;
			padding-bottom: 0.4em;
			background-color: blanchedalmond;
		}

		tbody>tr {
			background-color: #ffffe08f;
			font-size: 18px;
			border-bottom: 5px solid white;
		}

		.list_title {
			font-weight: 600;
		}

		a {
			color: black;
			text-decoration: none
		}
		a:hover {
			color: gray;
		}
		

		.list_notice {
			color: red;
			font-weight: 600;
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
	<script src="https://kit.fontawesome.com/69749f5203.js" crossorigin="anonymous"></script>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			$(".deleteBtn").on("click", function () {

				if (confirm("이 영화 정보를 삭제하시겠습니까?") == true) {
					location.replace('removeMovie.do?movieNo=' + $(this).val());
					alert("삭제되었습니다.")
				} else {
					return;
				}
			});
		});
	</script>
</head>

<body>

	<c:set var="pageBlock" value="${requestScope.pageBlock }" scope="page" />
	<c:set var="startPage" value="${requestScope.startPage }" scope="page" />
	<c:set var="endPage" value="${requestScope.endPage }" scope="page" />
	<c:set var="totalPage" value="${requestScope.totalPage }" scope="page" />
	<c:set var="currentPage" value="${param.currentPage }" scope="page" />

	<section>
		<div class="main_top">
			<div class="top_title">
				<h1 class="main_title">이 달의 영화</h1>

				<c:if test="${userInfo.rankType.equals('A') }">
					<c:url var="addMovieUrl"
						value="/indexControl.jsp?contentTemplate=/movie/movieBoardForm">
					</c:url>
					<a href="${addMovieUrl }">
						<button class="add_btn">등록</button>
					</a>
				</c:if>
			</div>

			<c:if test="${empty requestScope.movieList }">
				<p class="content_text"> 등록된 영화가 없습니다. </p>
			</c:if>

			<c:if test="${not empty requestScope.movieList }">
				<div class="movieList">
					<c:if test="${startPage > pageBlock }">
						<c:url var="prevUrl" value="/main.do">
							<c:param name="currentPage" value="${startPage - pageBlock }" />
						</c:url>
						<a href="${prevUrl}"><i class="fas fa-angle-double-left list_icon"></i></a>
					</c:if>
					<c:forEach var="movie" items="${requestScope.movieList }" varStatus="loop">
						<div class="listSection">

							<c:url var="detailUrl" value="/detailMovie.do">
								<c:param name="movieNo" value="${pageScope.movie.movieNo }" />
								<c:param name="userId" value="${userId }" />
								<c:param name="userLank" value="${userLank }" />
							</c:url>
							<div class="sectionMovie" id="movie">
								<a href="${detailUrl }">
									<img src="upload/movie/${pageScope.movie.posterSys }" alt="movie"
										class="movieImg" />
								</a>
								<h3 class="movie_title">${pageScope.movie.movieTitle }</h3>
							</div>
							<c:if test="${userInfo.rankType.equals('A') }">
								<c:url var="deleteUrl" value="/removeMovie.do">
									<c:param name="movieNo" value="${pageScope.movie.movieNo }" />
								</c:url>
								<button id="deleteBtn" class="deleteBtn"
									value="${pageScope.movie.movieNo }"><i
										class="fas fa-minus"></i></button>
							</c:if>
						</div>
					</c:forEach>

					<c:if test="${endPage < totalPage }">
						<c:url var="nextUrl" value="/main.do">
							<c:param name="currentPage" value="${endPage + 1 }" />
						</c:url>
						<a href="${nextUrl}"><i class="fas fa-angle-double-right list_icon"></i></a>
					</c:if>
				</div>
			</c:if>
			<div class="paging">
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<c:if test="${i == currentPage}">
						<i class="fas fa-circle"></i>
					</c:if>
					<c:if test="${i != currentPage}">
						<c:url var="url" value="/main.do">
							<c:param name="currentPage" value="${i}" />
						</c:url>
						<a href="${url}"><i class="far fa-circle paging_icon"></i></a>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="main_divide"></div>

		<div class="main_bottom">
			<div class="review">
				<h3 class="review_title">추천 리뷰</h3>

				<table>
					<thead>
						<th>제목</th>
						<th>닉네임</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>추천</th>
						<th>댓글</th>
					</thead>
					<c:forEach var="board" items="${requestScope.boards}" varStatus="loop">
						<c:url var="url" value="/board/detailBoard.do">
							<c:param name="boardNo" value="${pageScope.board.boardNo}"></c:param>
							<c:param name="cateNo" value="${pageScope.board.cateNo}"></c:param>
						</c:url>
						<tr>
							<td class="list_title"><a href="${pageScope.url}">${pageScope.board.boardTitle
									}</a></td>
							<td>${pageScope.board.userNick }</td>
							<td>${pageScope.board.boardWdate }</td>
							<td>${pageScope.board.boardCount }</td>
							<td>${pageScope.board.recomCount }</td>
							<td>${pageScope.board.commentCount }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="notice">
				<h3 class="review_title">공지사항</h3>
				<table>
					<thead>
						<th> </th>
						<th>제목</th>
						<th>닉네임</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>추천수</th>
					</thead>
					<c:forEach var="noticeBoard" items="${requestScope.noticeBoards}" varStatus="loop">
						<c:url var="url" value="/board/detailBoard.do">
							<c:param name="boardNo" value="${pageScope.noticeBoard.boardNo}"></c:param>
							<c:param name="cateNo" value="${pageScope.noticeBoard.cateNo}"></c:param>
						</c:url>
						<tr>
							<td class="list_notice">필독!!</td>
							<td class="list_title"><a
									href="${pageScope.url}">${pageScope.noticeBoard.boardTitle }</a></td>
							<td>${pageScope.noticeBoard.userNick }</td>
							<td>${pageScope.noticeBoard.boardWdate }</td>
							<td>${pageScope.noticeBoard.boardCount }</td>
							<td>${pageScope.noticeBoard.recomCount }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

	</section>
</body>

</html>