<%@ page contentType="text/html; charset=utf-8" %>


<jsp:forward page="/template.jsp">
	<jsp:param name="headerTemplate" value="/header" />
	<jsp:param name="asideTemplate" value="/aside" />
	<jsp:param name="footerTemplate" value="/footer" />
	
	<jsp:param name="movieInsert" value="/movie/movieBoardForm" />
</jsp:forward> 
