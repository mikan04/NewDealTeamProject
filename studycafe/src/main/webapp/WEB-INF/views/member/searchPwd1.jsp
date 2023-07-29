<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>비밀번호 찾기</title>
<link rel="stylesheet" href="/css/searchpwd.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	window.resizeTo(500, 500);
	function searchPwdFindUsername() {
		var username = $("#username").val();
		
		if (username == "" || username.trim() == "") {
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		$.ajax({
			type : "post",
			url : "/searchPwdFindUsername",
			data : {
				"username" : username
			},
			dataType : "json",
			success : function(data) {
				if (data != null) {
					var username = data.username;
					$.ajax({
						type:"post",
						url:"/searchPwd2".
						data : {
							"username":username
						},
						dataType:"json"
					})
				}
			},
			error : function() {
				alert("존재하지 않는 아이디 입니다. 아이디를 다시 확인해주세요.");
			}
		})
	}
</script>
</head>
<body>
	<jsp:include page="../member/findinfo-header.jsp"></jsp:include>
	<div class="find-by-pwd">
		<p>비밀번호를 찾기 위한 ID를 입력하세요</p>
		<input type="text" id="username" name="username" placeholder="아이디를 입력해주세요" />
		<button type="button"onclick="searchPwdFindUsername()">다음</button>
	</div>
</body>
</html>