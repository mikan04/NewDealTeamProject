<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>비밀번호 찾기</title>
<link rel="stylesheet" href="/css/searchpwd.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/searchPwd1.js"></script>
</head>
<body>
	<jsp:include page="../member/findinfo-header.jsp"></jsp:include>
	<div class="find-by-pwd">
		<p>비밀번호를 찾기 위한 ID를 입력하세요</p>
		<input type="text" id="username" name="username" placeholder="아이디를 입력해주세요" />
		<button type="button" onclick="searchPwdFindUsername()">다음</button>
	</div>
</body>
</html>