<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html class="header-body">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>헤더</title>
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
</head>
<body class="header-body">
	<div class="wrapper">
		<nav>
			<input type="checkbox" id="show-search">
			<input type="checkbox" id="show-menu">
			<label for="show-menu" class="menu-icon">
				<i class="fas fa-bars"></i>
			</label>
			<div class="content">
				<div class="logo">
					<a href="/">사이트이름</a>
				</div>
				<ul class="links">
					<li>
						<a href="/gpt/opengpt" target="_blank">CharGPT</a>
					</li>
					<li>
						<a href="#" class="desktop-link">스터디</a>
						<input type="checkbox" id="show-features">
						<label for="show-features">스터디</label>
						<ul>
							<li>
								<a href="/study">스터디모집</a>
							</li>
							<li>
								<a href="${contextPath}/team/teamboard">팀등록</a>
							</li>
							<li>
								<a href="#">인증게시판</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#" class="desktop-link">팀</a>
						<input type="checkbox" id="show-services">
						<label for="show-services">팀</label>
						<ul>
							<li>
								<a href="#">팀관리</a>
							</li>
							<li>
								<a href="/chatRoom/moveChating?teamNumber=${teamNumber}">팀채팅방</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">고객센터</a>
					</li>
					<li class="member-li">
						<a href="#" class="desktop-link">회원</a>
						<input type="checkbox" id="show-memberInfo">
						<label for="show-memberInfo">회원</label>
						<ul class="member-dropdown">
							<li>
								<a href="${contextPath}/loginform">로그인</a>
							</li>
							<li>
								<a href="#">회원정보관리</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</nav>
	</div>
</body>
</html>
