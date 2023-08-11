<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 재설정</title>
<link rel="stylesheet" href="/css/searchpwd.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/searchPwd3.js"></script>
</head>
<body>
	<div class="find-by-pwd">
		<h2>비밀번호 재설정</h2>
		<label>새로운 비밀번호</label>
		<input type="password" id="password" name="password" placeholder="영문,숫자,특수문자 조합 (8~15자)" />
		<label>새로운 비밀번호 확인</label>
		<input type="password" id="rePassword" name="rePassword" placeholder="비밀번호를 재확인" />
		<button type="button" value="다음" onclick="updatePassword()" >다음</button>
	</div>
</body>
</html>