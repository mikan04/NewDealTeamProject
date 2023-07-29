<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 비번찾기 헤더</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<style type="text/css">
ul {
	position: fixed;
	top: 0;
	right: 0;
	left: 0;
	display: flex;
	flex-direction: row;
	margin: 0;
	padding: 0;
	list-style-type: none;
	border-bottom: 1px solid #e9e9e9;
}

ul>li {
	padding: 30px;
	margin-top: 10px;
	border: 1px solid #bebebe;
	border-bottom: none;
	background-color: #e9e9e9;
}

a {
	text-decoration: none;
	color: #999999;
}
</style>
</head>
<body>
	<ul>
		<li class="search-id">
			<a href="/findAccount" id="location-id">아이디 찾기</a>
		</li>
		<li class="search-pwd">
			<a href="/searchPwd1" id="location-pwd">비밀번호 찾기</a>
		</li>
	</ul>
	<script src="/js/findinfo-header.js"></script>
</body>
</html>