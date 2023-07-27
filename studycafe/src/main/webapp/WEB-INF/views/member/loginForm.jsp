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
<style>
	.container{
	min-height: calc(100% - 300px);
	margin-top: 270px;
	margin-bottom: -190px;
	}
	
	.main-form{
	min-height: calc(100% - 200px);
	border: solid 1px #bbb;
	width: 800px;
	margin: auto;
	}
	
	.login-box{
	text-align: center;
	}
	.login-box input{
	text-align: center;
	width: 200px;
	margin-bottom:10px;
	}
	

	table {
    margin-left:auto; 
    margin-right:auto;
	}
	
	table td{
	padding: 10px;
	}
	
	
</style>

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
	
	align-items: center;
	width: 200px;
	height: 120px;
	margin: auto;
}



.login-btn {
	height: 70px;
	width: 100px;
	border-radius: 0rem;
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

<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>
	<br/>
	<div class="container">
		

		<div class="main-form">
			<form id="loginForm" method="post" action="/loginform">

				<div class="hell">

					<div>
						<ul>
							
							<li><a href="${contextPath}/joinform">회원가입 바로가기</a></li>
						</ul>

					</div>

					<div class="login-form">
						<div class=input-box>
							<div class="insertbox">
								<!-- <label for="memberEmail">아이디(이메일)</label> -->
								<input type="text" id="username" name="username" class="insert" maxlength="30" placeholder="아이디">
							</div>
							<div class="insertbox">
								<!-- <label for="memberPassword">비밀번호</label> -->
								<input type="password" id="password" name="password" class="insert" maxlength="20" placeholder="비밀번호">
							</div>
						</div>
					</div>
					
					<div class ="login-box">
						<div>
							<input type="submit" value="로그인">					
						</div>
					</div>
					
					<div class="sns-login">
						<table>
							<tr>
								<td>
									<div id="kakao_id_login" style="text-align: center">
											<a href="https://kauth.kakao.com/oauth/authorize?client_id=c96d28f0d334c324686022712a4e5ed0&redirect_uri=http://localhost:8080/kakaoLoginCallback&response_type=code"> 
											<img width="50" src="/img/kakao_login_small.png"/></a>
									</div>
								</td>
								<td>
									<div id="git_id_login" style="text-align: center">
									<a href="https://github.com/login/oauth/authorize?client_id=Iv1.427e6b094359a979"> 
									<img width="50" src="/img/github.png"/></a>
						</div>
								</td>
								
							</tr>
						</table>
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
<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>	
</body>
</html>