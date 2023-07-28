<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	window.resizeTo(500, 500);
	$.ajax({
		type:"post",
		url:""
	})
</script>
</head>
<body>
	<p>회원가입 시 입력한 이메일을 입력해주세요.</p>
	<label>이메일</label>
	<input type="text" id="email" name="email" placeholder="회원가입 시 입력한 이메일을 입력해주세요." />
	<input type="button" value="다음" onclick="" />
	<input type="text" id="username" name="username" value="${username }" style="display: none;">
</body>
</html>