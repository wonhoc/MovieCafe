<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
      src="https://kit.fontawesome.com/69749f5203.js"
      crossorigin="anonymous">
</script>
<style>
section {
  width : 90%;
  margin: 2em;
}
.detail_top {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.top_left {
  display: flex;
  align-items: center;
}

.detail_img {
  margin: 1em;
  width: 13rem;
  height: 20rem;
}

.detail_content {
  margin-left: 2em;
}

.detail_title {
  margin: 0.5em 0;
}

.detail_text {
  font-size: 20px;
  margin: 0.3em;
}
.modify_btn {
  padding: 0.5em 0.5em;
  color: #b1b1b1;
  font-size: 18px;
  background-color: #fdfdfd;
  border: 2px solid #b1b1b1;
  border-bottom-color: #b1b1b1;
  border-radius: 0.25em;
  transition: all 150ms ease-in;
  margin-bottom: 15em;
  cursor : pointer;
}
.modify_btn:hover {
  background-color: #e2e2e2;
}
.detail_divide {
  margin-left: 5%;
  width: 90%;
  height: 2px;
  background-color: #b1b1b1;
}

.review_title {
  font-size: 26px;
}

.review_line {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  padding: 0 5em;
}
.review_profile {
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  margin-right: 2.5em;
}

.review_text {
  font-size: 20px;
}

.id,
.date,
.like,
.rank {
  flex: 1;
}

.content {
  flex: 2;
}

.like {
  display: flex;
  align-items: center;
}
.review_lett {
  display: flex;
}
.bottom_top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 4em;
}
.review_btn {
  height: 2rem;
  margin: 0 0.5em;
  color: #b1b1b1;
  background-color: #fdfdfd;
  border: 2px solid #b1b1b1;
  border-bottom-color: #b1b1b1;
  border-radius: 0.25em;
  transition: all 150ms ease-in;
  cursor : pointer;
}
.review_btn:hover {
  background-color: #e2e2e2;
}
</style>
</head>
<body>

<section>
<div class="detail_top">
	<div class="top_left">
	
          <img src="upload/movie/${movieDetail.posterSys }" alt="image" class="detail_img" />
          <div class="detail_content">
            <h1 class="detail_title">${movieDetail.movieTitle }</h1>
            <p class="detail_text">감독 : ${movieDetail.movieDir }</p>
            <p class="detail_text">배우 : ${movieDetail.movieActor }</p>
            <p class="detail_text">장르 : ${movieDetail.movieGenre }</p>
            <p class="detail_text">러닝타임 : ${movieDetail.movieRuntime }</p>
            <p class="detail_text">예매처 링크 : <a href="${movieDetail.movieLink }" >${movieDetail.movieLink }</a></p>

            <c:if test="${movieDetail.movieAge eq 'A' } ">
            	<p class="detail_text">관람가 : 전체 관람가</p>
            </c:if>
            <c:if test="${movieDetail.movieAge eq 'B' } ">
            	<p class="detail_text">관람가 : 12세 이상 관람가</p>
            </c:if>
            <c:if test="${movieDetail.movieAge eq 'C' } ">
            	<p class="detail_text">관람가 : 15세 이상 관람가</p>
            </c:if>
            <c:if test="${movieDetail.movieAge eq 'D' } ">
            	<p class="detail_text">관람가 : 청소년 관람 불가</p>
            </c:if>
            <p class="detail_text">상영 날짜 : ${movieDetail.movieRelease }</p>
            <p class="detail_text">평점 : ${movieDetail.movieAvg } / 5</p>
          </div>
         </div>
        
         <c:if test="${userLank.equals('A') }" >
            <c:url var="modifyUrl" value="/modifyMovieForm.do">
            	<c:param name="movieNo" value="${param.movieNo }" />
            	<c:param name="userId" value="${param.userId }" />
            </c:url> 
            <a href="${modifyUrl }">
            	<button class="modify_btn">수정</button>
            </a>         	
          </c:if>

        </div>
        <div class="detail_divide"></div>
        <div class="detail_bottom">
          <div class="bottom_top">
            <h1 class="review_title">관람평</h1>
            <div class="review_lett">
            
            <c:if test="${userLank.equals('R') }" >
              <button class="review_btn">내가 쓴 글</button>
              
              <c:url var="reviewRegisterUrl" value="/registerGuanramForm.do">
              	<c:param name="userId" value="${param.userId }" />
              	<c:param name="movieNo" value="${param.movieNo }" />
              </c:url>
              <c:set var="movieImg" value="${movieDetail.posterSys }" scope="session" />
              <a href="${reviewRegisterUrl }">
              	<button class="review_btn">평점 작성</button>
              </a>
     
             </c:if>
            </div>
          </div>
          <c:forEach var="review" items="${movieDetail.guanramList }">
          <div class="review_line">
            <img src="/party.png" alt="profile" class="review_profile" />
            <p class="review_text id">${review.userId }</p>
            <p class="review_text content">${review.guanramReview }</p>
            <p class="review_text date">${review.guanramWdate }</p>
            <div class="like">
              <i class="fas fa-heart review_text"></i>
              <!-- <i class="far fa-heart review_text"></i> -->
              <p class="review_text">${review.guanramLike }</p>
            </div>
            <div class="rank">
            <c:forEach var="i" begin="1" end="${review.guanramRating }">
              <i class="fas fa-star review_text"></i>
			</c:forEach>
            </div>
          </div>
          </c:forEach>
          
        </div>
        
</section>
</body>
</html>