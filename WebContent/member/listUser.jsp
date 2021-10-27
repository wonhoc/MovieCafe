
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*, domain.member.UserInfoVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang='ko'>
<head>
<meta charset='UTF-8'>
<title>게시글 목록보기</title>
<style>
.list_table {
  margin: auto;
  width: 70%;
  text-align: center;
}
.list_devide {
  width: 30%;
  height: 2px;
  background-color: black;
}
.list_title_box {
  margin: 3em 5em;
}
.list_title {
  font-size: 36px;
  font-weight: bolder;
  margin: 0.3em 0;
}
.list_devide_dot {
  margin-left: 10%;
  border: 1px dotted black;
  width: 80%;
}
#paging {
    text-align: center;
    margin: 5em;
}
   

</style>
</head>

<body>
	<div class="list_title_box">
		<h1 class="list_title">사용자정보조회</h1>
         <div class="list_devide"></div>
         <h3 class="list_notice">회원 정보 목록을 조회합니다.</h3>
	</div>

	<table class="list_table">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>아이디</th>
				<th>신고횟수</th>
				<th>회원등급</th>
				<th>탈퇴유형</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${requestScope.users}" varStatus="loop">
				<tr>
					<td>${requestScope.totalPostCount - (param.currentPage - 1) * requestScope.postSize - loop.index }</td>
					<td>${pageScope.user.userName}</td>
					<td>${pageScope.user.userId}</td>
					<td>${pageScope.user.reportCount}회</td>
					<td><c:choose>
							<c:when test="${pageScope.user.rankType == 'A'}">관리자</c:when>
							<c:when test="${pageScope.user.rankType == 'N'}">새싹회원</c:when>
							<c:when test="${pageScope.user.rankType == 'R'}">정회원</c:when>
							<c:when test="${pageScope.user.rankType == 'B'}">제재회원</c:when>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${pageScope.user.exitType == 'S'}">자진탈퇴</c:when>
							<c:when test="${pageScope.user.exitType == 'F'}">강제탈퇴</c:when>
						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<%-- 페이징 처리 --%>
	<div id="paging">
		<c:set var="pageBlock" value="${requestScope.pageBlock}" scope="page" />
		<c:set var="startPage" value="${requestScope.startPage}" scope="page" />
		<c:set var="endPage" value="${requestScope.endPage}" scope="page" />
		<c:set var="totalPage" value="${requestScope.totalPage}" scope="page" />
		<c:set var="currentPage" value="${param.currentPage}" scope="page" />

		<c:if test="${startPage > pageBlock}">
			<c:url var="prevUrl" value="/listUser.do">
				<c:param name="currentPage" value="${startPage - pageBlock}" />
			</c:url>
			<a href="${prevUrl}">[Prev]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<c:if test="${i == currentPage}">
			&nbsp;${i}&nbsp;
		</c:if>
			<c:if test="${i != currentPage}">
				<c:url var="url" value="/listUser.do">
					<c:param name="currentPage" value="${i}" />
				</c:url>
				<a href="${url}">&nbsp;${i}&nbsp;</a>
			</c:if>
		</c:forEach>
		<c:if test="${endPage < totalPage}">
			<c:url var="nextUrl" value="/listUser.do">
				<c:param name="currentPage" value="${endPage + 1}" />
			</c:url>
			<a href="${nextUrl}">[Next]</a>
		</c:if>
	</div>
</body>
</html>