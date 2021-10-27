<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<style>
.header_logo {
	display: flex;
	width: 100%;
	justify-content: center;
}

.header_bottom {
	display: flex;
	margin-right: 5em;
}

.yellowBox {
	width: 100%;
	height: 2em;
	background-color: #f9da78;
}

.logo_1 {
	width: 10rem;
	height: 7rem;
	margin: 0.8em 2.5em 0 2em;
}

.logo_2 {
	width: 7rem;
	height: 7rem;
	margin-left: 1.5em;
}

.header_text {
	font-size: 12px;
	padding-right: 2em;
}

.header_title {
	font-size: 3.5rem;
	margin: 0;
	line-height: 124px;
}

.home {
	font-size: 36px;
}

.header {
	display: flex;
}

.home_logo {
	padding: 6em 0 0 1em;
}
</style>
</head>
<body>
	<div class="header">
		<c:url var="homeUrl" value="/main.do"></c:url>
		<div class="home_logo">
		<a href="${homeUrl }">
		<i class="fas fa-home home"></i>
		</a>
		</div>
		<div class="header_logo">
			<div class="header_left">
				<div class="header_top">
					<span class="header_text">철수야 같이 영화관 갈래?</span> <span
						class="header_text">팝콘은 너가 쏘는 거다?</span>
				</div>
				<div class="header_bottom">
					<img src="images/철수와 영희.png" alt="image" class="logo_1" />
					<h1 class="header_title">철수와 영화</h1>
				</div>
			</div>

			<img src="images/참잘함.png" alt="image_stamp"
				class="logo_2 header_right" />

		</div>
	</div>
	<div class="yellowBox"></div>

</body>
</html>