<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 확인</title>
<link rel="stylesheet" href="/css/searchpwd.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/searchPwd2.js"></script>
</head>
<body>
	<div class="find-by-pwd">
		<p>회원가입 시 입력한 이메일을 입력해주세요.</p>
		<input type="text" id="email" name="email" placeholder="이메일을 입력해주세요." />
		<button type="button" onclick="searchPwdFindUsername()">다음</button>
	</div>
</body>
</html>