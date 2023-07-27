<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- <link rel="stylesheet" href="/css/loginform.css"> -->
<style type="text/css">
*{
	box-sizing: border-box;
}
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.container {
	border: 1px solid #ccc;
	width: 500px;
	padding: 20px;
	text-align: center;
}

input {
	width: 100%;
	padding: 0;
}

#username, #password{
	margin: 10px 0;
}

.btn-login {
	width: 100%;
}

input, button, a {
	padding: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="main-form">
			<form id="loginForm" method="post" action="/loginform">

				<div class="input-box">
					<div class="username-box">
						<input type="text" id="username" name="username" class="insert" maxlength="30" placeholder="아이디">
					</div>
					<div class="password-box">
						<input type="password" id="password" name="password" class="insert" maxlength="20" placeholder="비밀번호">
					</div>
				</div>

				<div class="login-join-box">
					<div class="login-div">
						<button type="submit" class="btn-login">로그인</button>
					</div>
					<div>
						아직 회원이 아니신가요? <a href="${contextPath}/joinform">회원가입 바로가기</a>
					</div>
					<div>
						회원 정보를 잊으셨나요? <a href="/member/findAccountForm">회원정보 찾기</a>
					</div>
				</div>

				<div class="sns-login">
					<div id="kakao_id_login">
						<a
							href="https://kauth.kakao.com/oauth/authorize?client_id=c96d28f0d334c324686022712a4e5ed0&redirect_uri=http://localhost:8080/kakaoLoginCallback&response_type=code">
							카카오로그인 </a>
					</div>
					<div id="git_id_login">
						<a href="https://github.com/login/oauth/authorize?client_id=Iv1.427e6b094359a979"> 깃허브 로그인</a>
					</div>
				</div>

				<!-- end loginForm -->
				<div class="validate-box">
					<p id="validate-message"></p>
				</div>

			</form>
		</div>
	</div>
</body>
</html>