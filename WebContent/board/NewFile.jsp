<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 페이징 --%>

<div id = "paging">
		<c:set var = "pageBlock" value = "${requestScope.pageBlock}" scope="page"/>
		<c:set var = "startPage" value = "${requestScope.startPage}" scope="page"/>
		<c:set var = "endPage" value = "${requestScope.endPage}" scope="page"/>
		<c:set var = "totalPage" value = "${requestScope.totalPage}" scope="page"/>
		<c:set var = "currentPage" value = "${requestScope.currentPage}" scope="page"/>

		<c:if test="${startPage > pageBlock}">
			<c:url var="preUrl" value = "/board/listBoard.do">
				<c:param name = "currentPage" value = "${startPage - pageBlock}"/>
			</c:url>
			<a href = "${preUrl}">[Prev]</a>
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








<tr>
	<td colspan="5">		
<%
				//현재 페이지 번호를 구한다.
				int currentPage = Integer.parseInt(request.getParameter("currentPage"));
				//총 게시글 수를 구한다.
				int totalPostCount = (Integer)request.getAttribute("totalPostCount");
				//한 페이지에 보여줄 게시글의 수를 구한다.
				int postSize = (Integer)request.getAttribute("postSize");									
				//한 페이지에 보여줄 페이지 블럭의 수를 설정한다.
				int pageBlock = 2;								
				
				//게시글이 존재하면
				if (totalPostCount > 0) {			
						
				//한 페이지에 보여줄 시작 페이지번호와 마지막 페이지 번호를 구한다
				int currentBlock = currentPage % pageBlock == 0? currentPage / pageBlock : (currentPage / pageBlock)  + 1;
				int startPage = 1 + (currentBlock - 1) * pageBlock;
				int endPage = startPage + (pageBlock - 1);
						
				//총 페이지의 수를 구한다.
				int totalPages = totalPostCount % postSize == 0?  totalPostCount / postSize : (totalPostCount / postSize) + 1;
						
				if (endPage > totalPages) {
				endPage = totalPages;
			}
			
			if (startPage > pageBlock) { 	
			
				%>	
						<a href="/board/listBoard.do?currentPage=<%=startPage - pageBlock%>">[Prev]</a>
	<%		
						}
					
		
						for (int i = startPage; i <= endPage; i++) {				
							if (i == currentPage) {
				%>
								[<%=i%>]
				
				<%
							} else {
								
				%>
								<a href="/board/listBoard.do?currentPage=<%=i%>"><%=i %></a>
				
				<%			}
						}
						
						if (endPage < totalPages) {
							
				%>
							<a href="/board/listBoard.do?currentPage=<%=endPage + 1%>">[Next]</a>
				<%			
							
						}						
							
					}
				%>	
</td>
</tr>



</body>
</html>