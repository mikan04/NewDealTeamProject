<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ID 찾기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">
	window.resizeTo(500, 500);
	function searchId() {
		var email = $("#email").val();
		$.ajax({
			type : "post",
			url : "/getUserId",
			data : {
				"email" : email
			},
			dataType : "json",
			success : function(data) {
				console.log(data);
				if (data != null) {
					var username = data.username;
					location.href = "/resultUsername/" + username;
				}
			},
			error : function() {
				alert(email + "로 등록되어있는 아이디가 없습니다.");
			}
		});
	}
</script>
</head>
<body>
	<p>회원 가입 시 등록한 이메일을 입력해주세요</p>
	<label>이메일</label>
	<input type="text" id="email" name="email" placeholder="이메일을 입력해주세요." />
	<input type="button" value="찾기" onclick="searchId()">
</body>
</html>