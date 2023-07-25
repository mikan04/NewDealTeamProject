<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<style>
#validate-message {
	color: red;
	font-weight: bold;
	margin: 0px;
}

.sub-box ul li {
	list-style-type: none;
	flex-direction: row;
	display: flex;
}

.sub-box ul {
	display: flex;
	justify-content: space-evenly;
}

.input-box .insertbox {
	padding: 5px;
}

.input-box input {
	height: 30px;
	width: 200px;
}

.login-form {
	display: flex;
	flex-direction: row;
	align-items: center;
	width: 300px;
	height: 120px;
	margin: auto;
}

.main-form {
	border: solid 1px #bbb;
	width: 800px;
	margin: auto;
}

.login-btn {
	height: 70px;
	width: 100px;
	border-radius: 0rem;
}

.container {
	margin-top: 150px;
	margin-bottom: 100px;
}

li {
	list-style-type: none;
	display: flex;
}

.validate-box {
	margin: auto;
}
</style>
</head>
<body>

	<div class="container">
		<div class="subMenu">
			<div class="siteWidth">
				<span>로그인</span>
			</div>
		</div>

		<div class="main-form">
			<form id="loginForm" method="post" action="/loginform">

				<div class="hell">

					<div>
						<ul>
							<!-- <li>아직 이음 아이디가 없다면</li> -->
							<li><a href="/joinform">회원가입 바로가기</a></li>
						</ul>

					</div>

					<div class="login-form">
						<div class=input-box>
							<div class="insertbox">
								<!-- <label for="memberEmail">아이디(이메일)</label> -->
								<input type="text" id="username" name="username" class="insert" maxlength="30" placeholder="아이디(이메일)">
							</div>
							<div class="insertbox">
								<!-- <label for="memberPassword">비밀번호</label> -->
								<input type="password" id="password" name="password" class="insert" maxlength="20" placeholder="비밀번호">
							</div>
						</div>

						<div class="button-box">
							<!-- <button class="btn btn-danger login-btn" type="submit">	<span>로그인</span></button> -->
							<input type="submit" value="로그인">
						</div>
						<div id="kakao_id_login" style="text-align: center">
							<a href="https://kauth.kakao.com/oauth/authorize?client_id=c96d28f0d334c324686022712a4e5ed0&redirect_uri=http://localhost:8080/kakaoLoginCallback&response_type=code"> 
							<img width="50" src="/img/kakao_login_small.png"/></a>
						</div>

					</div>
					<!-- end loginForm -->
					<div class="validate-box">
						<p id="validate-message"></p>
					</div>
					<div class="sub-box">
						<ul>
							<li><input type="checkbox" value="아이디 저장">아이디 저장</li>
							<li><a href="/member/findAccountForm">아이디 찾기/비밀번호 찾기</a></li>
						</ul>
					</div>
				</div>
			</form>

		</div>
	</div>
</body>
</html>