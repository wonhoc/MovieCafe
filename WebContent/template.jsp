<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<c:set var="headerTemplate" value="${param.headerTemplate }.jsp" />
<c:set var="asideTemplate" value="${param.asideTemplate }.jsp" />
<c:set var="footerTemplate" value="${param.footerTemplate }.jsp" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
header {
  padding-top: 1em;
}
aside {
  width: 20%;
  height: 100%;
  background-color: #eab48a;
}
footer {
  display: flex;
  background-color: #f3f2f2;
  justify-content: center;
  padding: 1em;
  align-items: center;
  color: #b1b1b1;
}
</style>
</head>
<body>
<header>
	<jsp:include page="${headerTemplate }" flush="false"></jsp:include>
</header>
<aside>
	<jsp:include page="${asideTemplate }" flush="false"></jsp:include>
</aside>
<footer>
	<jsp:include page="${footerTemplate }" flush="false"></jsp:include>
</footer>
</body>
</html>