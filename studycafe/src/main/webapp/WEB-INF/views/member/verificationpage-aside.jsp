<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보검증사이드바</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction : column;
	justify-content: center;
	align-items: center;
	height: 100vh;
}
aside {
	display : flex;
	flex-direction : column;
	margin-right: 20px;
	border-right: 5px solid black;
	background-color: #e9e9e9;
}

aside ul {
	display : flex;
	flex-direction : column;
	list-style: none;
	font-size: 22px;
	font-weight: bold;
}
aside ul.main{
	flex: 1;
	justify-content: flex-end;

}
aside ul li {
	margin: 20px;
}

aside ul li a {
	text-decoration: none;
	color: black;
}

aside ul li a:hover {
	color: #0077ff;
}


</style>
</head>
<body>
	<aside>
		<ul>
			<li>
				<a href="/member/verificationpage">회원정보변경</a>
			</li>
			<li>
				<a href="/member/changepwd">비밀번호변경</a>
			</li>
		</ul>
		<ul>
			<li></li>
		</ul>
		<ul class="main">
			<li><a href="/">메인으로</a></li>
		</ul>
	</aside>
</body>
</html>