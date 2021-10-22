<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<c:set var="headerTemplate" value="${param.headerTemplate }.jsp" />
<c:set var="asideTemplate" value="${param.asideTemplate }.jsp" />
<c:set var="footerTemplate" value="${param.footerTemplate }.jsp" />

<c:set var="movieInsert" value="${param.movieInsert }.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
header {
  padding-top: 1em;
}
main {
	display : flex;
}
aside {
  width: 20%;
  height: 100%;
  background-color: #eab48a;
}
content {
	widrh : 100%
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
<main>
	<aside>
		<jsp:include page="${asideTemplate }" flush="false"></jsp:include>
	</aside>
	<content>
		<jsp:include page="${movieInsert }" flush="false"></jsp:include>
	</content>
</main>
<footer>
	<jsp:include page="${footerTemplate }" flush="false"></jsp:include>
</footer>
</body>
</html>