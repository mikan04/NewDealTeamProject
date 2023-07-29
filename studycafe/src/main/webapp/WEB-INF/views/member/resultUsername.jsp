<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/resultusername.css">
<script src="/js/resultUsername.js"></script>
</head>
<body>
	<div>
		회원님의 아이디는
		<h2 id="username"></h2>
		입니다.

	</div>
	<div>
		<a href="#" onclick="searchPwd()">비밀번호 찾기</a>
	</div>
</body>
</html>