<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.board.BoardVo" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색결과</title>
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
            
            .highlight { background-color: yellow; }
          
 

        </style>
</head>
<body>

<c:if test="${requestScope.cateNo==1}">
   <h3>새싹 게시판</h3>
   <form action="${pageContext.request.contextPath}/board/searchBoard.do" method="GET">
  
   <input type="hidden" name= "cateNo" value="${requestScope.cateNo }">
   <div id="search">
      <select name= "keyfield" id="keyfield" >
         <option value="all">전체</option>
         <option value="user_id">작성자</option>
         <option value="board_title">제목</option>
         <option value="board_content">내용</option>
      </select>
   
      
         <input type="search" placeholder="검색어를 입력하세요" size="30" name= "keyword" id="keyword"   >
      
         <button type="submit" id="searchBtn" >검색</button>
      
   </div>
   </form>
   </c:if>
   
   <c:if test="${requestScope.cateNo==2}">
   <h3>영화 리뷰 게시판</h3>
   <form action="${pageContext.request.contextPath}/board/searchBoard.do" method="GET">
   <input type="hidden" name= "cateNo" value="${requestScope.cateNo }">
   <div id="search">
      <select name= "keyfield" id="keyfield" style="height: 30px;">
         <option value="all">전체</option>
         <option value="user_id">작성자</option>
         <option value="2">로맨스</option>
         <option value="3">스릴러</option>
         <option value="5">SF</option>
         <option value="7">코미디</option>
         <option value="6">액션</option>
         <option value="4">공포</option>
      </select>
   
      
         <input type="search" placeholder="검색어를 입력하세요" size="30" name= "keyword" id="keyword">
      
         <button type="submit" id="searchBtn" >검색</button>
      
   </div>
   </form>
   </c:if>
   
   <c:if test="${requestScope.cateNo==3}">
   <h3>시사회 정보 게시판</h3>
   <form action="${pageContext.request.contextPath}/board/searchBoard.do" method="GET">
   <input type="hidden" name= "cateNo" value="${requestScope.cateNo }">
   <div id="search">
      <select name= "keyfield" id="keyfield">
         <option value="all">전체</option>
         <option value="8">다녀온 시사회</option>
         <option value="9">예정 시사회</option>
      </select>
   
      
         <input type="search" placeholder="검색어를 입력하세요" size="30" name= "keyword" id="keyword">
      
         <button type="submit" id="searchBtn">검색</button>
      
   </div>
   </form>
   </c:if>
   
   <c:if test="${requestScope.cateNo==4}">
   <h3>영화 관람 팁</h3>
   <form action="${pageContext.request.contextPath}/board/searchBoard.do" method="GET">
   <input type="hidden" name= "cateNo" value="${requestScope.cateNo }">
   <div id="search">
      <select name= "keyfield" id="keyfield">
         <option value="all">전체</option>
         <option value="user_id">작성자</option>
         <option value="board_title">제목</option>
         <option value="11">영화관 리뷰</option>
         <option value="10">할인 정보</option>
      </select>
   
      
         <input type="search" placeholder="검색어를 입력하세요" size="30" name= "keyword" id="keyword">
      
         <button type="submit" id="searchBtn" >검색</button>
      
   </div>
   </form>
   </c:if>
   
   <c:if test="${requestScope.cateNo==5}">
   <h3>티켓마켓</h3>
   <form action="${pageContext.request.contextPath}/board/searchBoard.do" method="GET">
   <input type="hidden" name= "cateNo" value="${requestScope.cateNo }">
   <div id="search">
      <select name= "keyfield" id="keyfield">
         <option value="all">전체</option>
         <option value="13">구매</option>
         <option value="14">나눔</option>
         <option value="12">판매</option>
      </select>
   
      <div>
         <input type="search" placeholder="검색어를 입력하세요" size="30" name= "keyword" id="keyword"   >
      </div>
      <div>
         <button type="submit" id="searchBtn" >검색</button>
      </div>
   </div>
   </form>
   </c:if>
   
   <c:if test="${requestScope.cateNo==6}">
   <h3>이벤트</h3>
   <form action="${pageContext.request.contextPath}/board/searchBoard.do" method="GET">
   <input type="hidden" name= "cateNo" value="${requestScope.cateNo }">
   <div id="search">
      <select name= "keyfield" id="keyfield"">
         <option value="all">전체</option>
         <option value="board_wdate">작성일자</option>
      </select>
   
      
         <input type="search" placeholder="검색어를 입력하세요" size="30" name= "keyword" id="keyword"   >
      
         <button type="submit" id="searchBtn">검색</button>
      
   </div>
   </form>
   </c:if>
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
               <td colspan="7">검색된 게시글이 없습니다.</td>
            </tr>
         </c:if>

         <c:if test="${not empty requestScope.boards}">
            <c:forEach var="board" items="${requestScope.boards}"
               varStatus="loop">
               <c:url var="url" value="/detailBoard.do">
               		<c:param name="cateNo" value="${requestScope.cateNo }" />
               		<c:param name="boardNo" value="${pageScope.board.boardNo}" />
               </c:url>
               <tr>
                  <td>${requestScope.totalPostCount - (param.currentPage -1) * requestScope.postSize - loop.index }</td>
                  <td>${pageScope.board.userId }</td>
                  <td><a href="${pageScope.url}">${pageScope.board.boardTitle }</a></td>
                  <td>${pageScope.board.boardWdate }</td>
                  <td>${pageScope.board.boardCount }</td>
                  <td>1</td>
                  <td>1</td>
               </tr>
            </c:forEach>
         </c:if>
      </tbody>
   </table>

   <%-- 페이징 처리 --%>

   <div id="paging">
      <c:set var="pageBlock" value="${requestScope.pageBlock}" scope="page" />
      <c:set var="startPage" value="${requestScope.startPage}" scope="page" />
      <c:set var="endPage" value="${requestScope.endPage}" scope="page" />
      <c:set var="totalPage" value="${requestScope.totalPage}" scope="page" />
      <c:set var="currentPage" value="${param.currentPage }" scope="page" />

      <c:if test="${startPage > pageBlock }">
         <c:url var="prevUrl" value="/board/findBoard.do">
            <c:param name="currentPage" value="${startPage - pageBlock }"></c:param>
         </c:url>
         <a href="${prevUrl}">[Prev]</a>
      </c:if>
      <c:forEach var="i" begin="${startPage}" end="${endPage}">
         <c:if test="${i == currentPage}">
         &nbsp;${i} &nbsp;
         </c:if>
         <c:if test="${i != currentPage}">
            <c:url var="url" value="/board/findBoardListBoard.do">
               <c:param name="currentPage" value="${i}" />
            </c:url>
            <a href="${url}">&nbsp;${i} &nbsp;</a>
         </c:if>
      </c:forEach>
      <c:if test="${endPage < totalPage}">
         <c:url var="nextUrl" value="/board/findBoardListBoard.do">
            <c:param name="currentPage" value="${endPage + 1 }"></c:param>
         </c:url>
         <a href="${nextUrl}">[Next]</a>
      </c:if>
   </div>



</body>
</html>