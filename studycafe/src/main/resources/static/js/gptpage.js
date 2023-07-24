/**
 * gpt js
 */
$(document).ready(function() {
	$("#userInputForm").submit(function(e) {

		// 새로고침 이벤트 초기화
		e.preventDefault();

		var userInput = $("#userInput").val();

		// 유효성검사
		if(userInput.length == 0 || userInput.trim() == "" ){
			alert("질문을 입력해주세요.");
			return false;
		}

		// submit시 검색창 글씨 사라짐
		$("#userInput").val('');

		// 대화처럼 이어지게
		appendToConversation('<label>User</label><br/>' + userInput, 'right', 'userResp');

		$.ajax({
			url: '/chat-gpt/question',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({
				question: userInput
			}),
			success: function(response) {
				
				console.log(response);
				
				let gptResp = response.choices[0].message.content;

				// gpt 응답내용
				appendToConversation('<label>ChatGPT</label><br/>' + gptResp , 'left', 'gptResp');

			},
			error: function() {

				appendToConversation('Error occurred while communicating with ChatGPT.');
			}
		});
	});

	function appendToConversation(message, floating, role) {

		// 메신저처럼 대화하게
		$("#conversation").append("<p style='float : " + floating + "'class=" + role + ">" + message + "</p>");

		scrollToBottom();

	}

	// 스크롤 최 하단 고정
	function scrollToBottom() {
		const conversationBox = document.getElementById('conversation');
		conversationBox.scrollTop = conversationBox.scrollHeight;
	}

	// 엔터 : 전송 , 엔터+쉬프트 : 줄바꿈
	$('#userInput').on('keydown', function(event) {
		if (event.keyCode == 13)
			if (!event.shiftKey) {
				event.preventDefault();
				$('#userInputForm').submit();
			}
	});

});