<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<link rel="stylesheet" href="/css/verification.css">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../member/verificationpage-aside.jsp"></jsp:include>
		<div class="password-wrapper">
			<h2>회원정보변경</h2>
			<p>회원님의 소중한 개인정보 보호를 위해 회원가입시 입력한 비밀번호를 입력해주세요</p>
			<div class="password-div">
				<input type="password" name="password" id="password">
				<a href="javascript:verification()">확인</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/js/verificationpage.js"></script>
</body>
</html>