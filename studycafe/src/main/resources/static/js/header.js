/**
 * 헤더 js
 */
 
function restrictGPT(){
	alert("Chat GPT 이용은 로그인 한 회원만 이용이 가능합니다.");
	return false;
}

function openTeamChat(teamNum) {
	window.open("/chatRoom/moveChating?teamNumber=" + teamNum,
		"top=50, left=50", "toolbar=no,menubar=no");
}