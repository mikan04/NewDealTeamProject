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
<link rel="stylesheet" href="/css/loginform.css">
<script src="/js/loginForm.js"></script>
</head>
<body>
	<div class="container">
		<div class="main-form">
			<div>
				<img alt="로고" src="/img/logo.png" width="250" height="80"">
			</div>
			<h2>DSSEAD에 오신것을 환영합니다.</h2>
			<h4>개발자를 위한 스터디 사이트입니다.</h4>
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
					<div align="right" class="join-find-div">
						회원 정보를 잊으셨나요?
						<a href="javascript:findAccount()">회원정보 찾기</a>
					</div>
					<div align="right" class="join-find-div">
						아직 회원이 아니신가요?
						<a href="${contextPath}/joinform">회원가입 바로가기</a>
					</div>
				</div>

				<fieldset>
					<legend align="left">소셜로그인</legend>
					<div class="sns-login">
						<div id="kakao_id_login">
							<img src="/img/kakao-logo.png" width="30" height="30">
							<a
								href="https://kauth.kakao.com/oauth/authorize?client_id=c96d28f0d334c324686022712a4e5ed0&redirect_uri=http://localhost:8080/kakaoLoginCallback&response_type=code">
								카카오 로그인 </a>
						</div>
						<div id="git_id_login">
							<img src="/img/github.png" width="30" height="30">
							<a href="https://github.com/login/oauth/authorize?client_id=Iv1.427e6b094359a979"> GitHub 로그인</a>
						</div>
					</div>
				</fieldset>

				<!-- end loginForm -->
				<div class="validate-box">
					<p id="validate-message"></p>
				</div>

			</form>
		</div>
	</div>

</body>
</html>