<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authentication property="principal.member" var="member" />
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<meta charset="UTF-8">
<title>Chatting</title>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" href="/css/chat.css">
</head>

<script src="/js/chat.js"></script>

<body>
	<div id="container" class="container">
		<h1>${room.roomName}</h1>
		<div id="chating" class="chating"></div>
		<input type="hidden" id="username" value="${member.username}">
		<input type="hidden" id="nickName" value="${member.nickName }">
		<input type="hidden" id="roomIdx" value="${room.roomIdx}">
		<input type="hidden" id="teamNumber" value="${member.teamNumber.teamNumber}">


		<div id="yourMsg">
			<table class="inputTable">
				<tr>
					<th><textarea id="chatting" rows="1"></textarea></th>
					<th>
						<button onclick="send()" id="sendBtn">Send</button>
					</th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>