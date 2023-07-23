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
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

.container {
	width: 500px;
	margin: 0 auto;
	padding: 25px;
}

.container h1 {
	text-align: left;
	padding: 5px 0px 5px 15px;
	color: black;
	margin-bottom: 20px;
	width: 500px;
	border: 2px solid black;
}

.chating {
	border: 2px solid black;
	width: 500px;
	height: 500px;
	overflow-y: scroll;
	background-color: #F4F4F4;
	padding: 10px;
}

.chating p {
	margin-bottom: 10px;
}

.inputTable {
	display: flex;
	margin-top: 10px;
	width: 500px;
	border: 2px solid black;
}

#chatting {
	flex: 1;
	font-size: 15px;
	font-weight: bold;
	border: none;
	border-radius: 20px;
	padding: 10px;
	resize: none;
	overflow: hidden;
	width: 400px;
	margin-left: 10px;
}

#sendBtn {
	background-color: darkgray;
	color: white;
	border: none;
	padding: 10px;
	border-radius: 20px;
	cursor: pointer;
	margin-left: 50%;
}

#sendBtn:hover {
	background-color: gray;
}

#userName {
	flex: 1;
	border: none;
	padding: 10px;
	border-radius: 20px;
	width: 400px;
	margin-left: 10px;
}

#startBtn {
	background-color: darkgray;
	color: white;
	border: none;
	padding: 10px;
	border-radius: 20px;
	cursor: pointer;
	margin-left: 10%;
}

#startBtn:hover {
	background-color: gray;
}

#yourName th, #yourMsg th {
	width: 75%;
}

#yourName button, #yourMsg button {
	width: 100%;
}

#yourName input:focus, #yourMsg textarea:focus {
	outline: none;
	border: 2px solid black;
}

.chating .me {
	color: black;
	text-align: right;
	margin-right: 10px;
}

.chating .others {
	width: auto;
	color: black;
	text-align: left;
	margin: 10px 10px 10px 10px;
}

.message {
	border: 1px solid black;
	border-radius: 1em;
}
</style>
</head>

<script>
	var ws;

	function wsOpen() {
		// WebSocket을 열고 연결을 설정
		// 웹소켓 주소는 현재 호스트와 roomIdx 값을 조합하여 생성.

		ws = new WebSocket("ws://" + location.host + "/chatRoom/chating/"
				+ $("#roomIdx").val());
		wsEvt();
	}

	function wsEvt() {

		// WebSocket 열릴 때, 메시지를 받을 때, 열려있는 상태에서 키 입력이 발생할 때 등의 이벤트에 대한 처리
		ws.onopen = function(data) {

			$
					.ajax({
						type : "get",
						url : "/chatRoomMessage/getMessage",
						dataType : "json",
						data : {
							"roomIdx" : $("#roomIdx").val()
						},
						success : function(e) {
							for (var i = 0; i < e.length; i++) {
								if (e[i].nickName === $("#nickName").val()) {

									$("#chating").append(
											"<p>"
											+ e[i].nickName
											+ "</p>"+
											"<div class='message'style='background-color:yellow'>"
													+ "<p class='me'>"
													+ e[i].msg + "</p>"
													+ "</div>");
								} else {
									$("#chating")
											.append(
													"<p>"
															+ e[i].nickName
															+ "</p>"
															+ "<div class='message'style='background-color:skyblue'>"
															+ "<p class='others'>"
															+ e[i].msg + "</p>"
															+ "</div>");
								}
							}
							scrollToBottom();

						},
						error : function() {
							alert("알수없는오류");
						}
					});
		}

		ws.onmessage = function(data) {
			//메시지를 받으면 동작
			// userId를 userName으로 userName을 nickName으로 변경해야 할 듯
			var msg = data.data;
			if (msg != null && msg.trim() != '') {
				var d = JSON.parse(msg);
				if (d.type == "getId") {
					var si = d.username != null ? d.username : "";
					if (si != '') {
						$("#username").val(si);
					}
				} else if (d.type == "message") {
					if (d.username == $("#username").val()) {
						$("#chating")
								.append(
										"<p>"+ d.nickName+"</p>"
												+ "<div class='message'style='background-color:yellow'>"
												+ "<p class='me'>나 :"+d.msg
												+ "</p>" + "</div>");
					} else {
						$("#chating")
								.append(
										"<p>"
												+ d.nickName
												+ "</p>"
												+ "<div class='message'style='background-color:skyblue'>"
												+ "<p class='others'>" + d.msg
												+ "</p>" + "</div>");
					}

				} else {
					console.warn("unknown type!")
				}
			}
			scrollToBottom();
		}
		document.addEventListener("keypress", function(e) {
			// Enter 키를 눌렀을 때 메시지를 전송
			if (e.keyCode == 13) {
				if ($("#chatting").val().trim() != "") {
					send();
				}
			}
		});
	}

	function send() {
		// userId는 userName, userName은 nickName으로 변경해야 할 듯
		var option = {
			type : "message",
			username : $("#username").val(),
			nickName : $("#nickName").val(),
			roomIdx : $("#roomIdx").val(),
			msg : $("#chatting").val()
		}
		$.ajax({
			type : "POST",
			url : "/chatRoomMessage/insertMessage",
			dataType : "json",
			data : {
				"username" : $("#username").val(),
				"nickName" : $("#nickName").val(),
				"roomIdx" : $("#roomIdx").val(),
				"msg" : $("#chatting").val(),
			},
			success : function(data) {

			}
		});
		ws.send(JSON.stringify(option));
		$('#chatting').val("");
		scrollToBottom();
	}

	$(document).ready(function() {
		
		wsOpen();
		
		$("#chatting").on("input", function() {
			// 메시지 입력 여부에 따라 전송 버튼을 표시하거나 숨김
			var msg = $(this).val();

			if (msg == null || msg.trim() === "") {
				$("#sendBtn").hide();
			} else {
				$("#sendBtn").show();
			}
		}).trigger("input");
	});

	function scrollToBottom() {
		// 채팅창 스크롤을 맨 아래로 이동시키는 함수
		var chatingDiv = document.getElementById("chating");
		chatingDiv.scrollTop = chatingDiv.scrollHeight;
	}
</script>

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