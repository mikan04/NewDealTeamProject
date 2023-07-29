<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/searchPwd3.js"></script>
</head>
<body>
	<p>비밀번호 재설정</p>
	<label>비밀번호</label>
	<input type="password" id="password" name="password" placeholder="영문,숫자,특수문자 조합 (8~15자)" />
	<label>비밀번호 확인</label>
	<input type="password" id="rePassword" name="rePassword" placeholder="비밀번호를 재확인" />
	<input type="button" value="다음" onclick="updatePassword()" />
</body>
</html>