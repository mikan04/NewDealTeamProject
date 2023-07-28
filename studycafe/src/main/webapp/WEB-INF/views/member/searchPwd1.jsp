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
	function searchPwdFindUsername() {
		var username = $("#username").val();
		if (username == "" || username.trim() == "") {
			alert("아이디를 입력해주세요.");
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
	<p>비밀번호를 찾기 위한 ID를 입력하세요</p>
	<label>아이디</label>
	<input type="text" id="username" name="username" placeholder="아이디를 입력해주세요" />
	<input type="button" value="다음" onclick="searchPwdFindUsername()" />
</body>
</html>