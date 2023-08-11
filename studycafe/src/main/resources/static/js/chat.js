window.resizeTo(600, 700);
var username1 = sessionStorage.getItem("username");
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

		$.ajax({
			type: "get",
			url: "/chatRoomMessage/getMessage",
			dataType: "json",
			data: {
				"roomIdx": $("#roomIdx").val()
			},
			success: function(e) {

				for (var i = 0; i < e.length; i++) {
					if (e[i].username == username1) {

						$("#chating")
							// // // // // // 나
							.append(
								"<div class='my-chat-box'>"
								+ "<label>" + e[i].nickName + "</label>"
								+ "<div class='my-msg-box'>"
								+ "<p class='me'>" + e[i].msg + "</p>"
								+ "</div>"
								+ "</div>"
							);
					} else {
						// // // // // // 상대
						$("#chating")
							.append(
								"<div class='another-chat-box'>"
								+ "<label>" + e[i].nickName + "</label>"
								+ "<div class='others-msg-box'>"
								+ "<p class='others'>" + e[i].msg + "</p>"
								+ "</div>"
								+ "</div>"
							);
					}
				}
				scrollToBottom();

			},
			error: function() {
				alert("알수없는오류");
			}
		});
	}

	ws.onmessage = function(data) {
		//메시지를 받으면 동작

		var msg = data.data;
		if (msg != null && msg.trim() != '') {
			var d = JSON.parse(msg);
			if (d.type == "getId") {
				var si = d.username != null ? d.username : "";
				if (si != '') {
					$("#username").val(si);
				}
			} else if (d.type == "message") {
				if (d.username == username1) {
					$("#chating")
						.append(
							"<div class='my-chat-box'><label>" 
							+ d.nickName + "</label>"
							+ "<div class='my-msg-box'>"
							+ "<p class='me'>" + d.msg + "</p>"
							+ "</div>"
							+ "</div>"
						);
				} else {
					$("#chating")
						.append(
							 "<div class='another-chat-box'>"
							+"<label>"+d.nickName+"</label>"
							+ "<div class='others-msg-box'>"
							+ "<p class='others'>" + d.msg + "</p>"
							+ "</div>"
							+ "</div>"
						);
				}

			} else {
				console.warn("unknown type!")
			}
		}
		$("#sendBtn").hide();
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
	var option = {
		type: "message",
		username: username1,
		nickName: $("#nickName").val(),
		roomIdx: $("#roomIdx").val(),
		msg: $("#chatting").val()
	}

	$.ajax({
		type: "POST",
		url: "/chatRoomMessage/insertMessage",
		dataType: "json",
		data: {
			"username": username1,
			"nickName": $("#nickName").val(),
			"roomIdx": $("#roomIdx").val(),
			"msg": $("#chatting").val(),
		},
		success: function(data) {

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
			// // // // // // btn btn
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

// 엔터 : 전송 , 엔터+쉬프트 : 줄바꿈
$('#chatting').on('keydown', function(event) {
	if (event.keyCode == 13)
		if (!event.shiftKey) {
			event.preventDefault();
			$('#userInputForm').submit();
		}
});