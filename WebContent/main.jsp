<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
section {
  width : 90%;
}
.content_title {
  padding: 2em 0 0 2em;
}
.movieList {
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.listSection{
  display : flex;
}
.list_icon {
  font-size: 2rem;
  cursor: pointer;
}
.content_text {
  text-align : center;
  line-height : 20rem;
}
.paging {
  text-align: center;
}
.paging_icon{
  transition: transform 200ms linear;
}
.paging_icon:hover {
   transform: scale(1.2);
}

.deleteBtn {
  height: 1.5rem;
  margin-left : 1em;
  color: #b1b1b1;
  background-color: #fdfdfd;
  border: 2px solid #b1b1b1;
  border-bottom-color: #b1b1b1;
  border-radius: 0.25em;
  transition: all 150ms ease-in;
  cursor: pointer;
}
.deleteBtn:hover{
  background-color: #e2e2e2;
}

.movieImg {
	width : 10rem;
	height : 15rem;
}
.main_top {
  display: flex;
  align-items: center;
  justify-content: space-between;
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
  cursor : pointer;
}
.add_btn:hover {
  background-color: #e2e2e2;
}
</style>
<script
      src="https://kit.fontawesome.com/69749f5203.js"
      crossorigin="anonymous"></script>
</script>
</head>
<body>

<c:set var="pageBlock" value="${requestScope.pageBlock }" scope="page" />
<c:set var="startPage" value="${requestScope.startPage }" scope="page" />
<c:set var="endPage" value="${requestScope.endPage }" scope="page" />
<c:set var="totalPage" value="${requestScope.totalPage }" scope="page" />
<c:set var="currentPage" value="${param.currentPage }" scope="page" />

<%-- <!-- 임의로 관리자 정보를 세션에 바인딩 -->--%>
<c:set var="userLank" value="A" scope="session" /> 

<!-- 임의로 사용자 아이디를 세션에 바인딩 -->
<c:set var="userId" value="test_user01" scope="session" />


<%-- 세션의 유저 정보 확인
${sessionScope.userInfo.userId} <br>
${sessionScope.userInfo.userNick} <br>
${sessionScope.userInfo.rankType} <br>
--%>

<section>
	<c:if test="${empty requestScope.movieList }">
		<p class="content_text"> 등록된 영화가 없습니다. </p>
	</c:if>
		<div class="main_top">
          <h1 class="content_title">이 달의 영화</h1>
          
          <c:if test="${userLank.equals('A') }" >
            	<c:url var="addMovieUrl" value="/indexControl.jsp?contentTemplate=/movie/movieBoardForm"></c:url> 
            	<a href="${addMovieUrl }">
            		<button class="add_btn">등록</button>
            	</a>          	
          	</c:if>
          
        </div>
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
          	<c:param name="movieNo" value="${pageScope.movie.movieNo }"/>
          	<c:param name="userId" value="${userId }"/>
          	<c:param name="userLank" value="${userLank }"/>
          </c:url>
     
          	<div class="sectionMovie" id="movie">
          		<a href="${detailUrl }">
	          		<img src="upload/movie/${pageScope.movie.posterSys }" alt="movie" class="movieImg" />
	          	</a>
    	        <h3 class="movie_title">${pageScope.movie.movieTitle }</h3> 	   
         	 </div>
            <c:if test="${userLank.equals('A') }" >
            	<c:url var="deleteUrl" value="/removeMovie.do">
            		<c:param name="movieNo" value="${pageScope.movie.movieNo }" />
            	</c:url> 
            	<a href="${deleteUrl }">
            		<button id="deleteBtn" class="deleteBtn"><i class="fas fa-minus"></i></button>
            	</a>
            	
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
				<c:param name="currentPage" value="${i}"/>
			</c:url>
			<a href="${url}"><i class="far fa-circle paging_icon"></i></a>
		</c:if>	
	</c:forEach>
	</div>
</section>
</body>
</html>