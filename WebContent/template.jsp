<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contentTemplate" value="/${param.contentTemplate }.jsp" />

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
	width : 100%;
}
aside {
  width: 20%;
  height: 100%;
  background-color: #eab48a;
}
section {
	width : 100%;
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
	<jsp:include page="/header.jsp" flush="false"></jsp:include>
</header>
<main>
	<aside>
		<jsp:include page="/aside.jsp" flush="false"></jsp:include>
	</aside>
	<section>
		<jsp:include page="${contentTemplate }" ></jsp:include>
	</section>
</main>
<footer>
	<jsp:include page="/footer.jsp" flush="false"></jsp:include>
</footer>
</body>
</html>