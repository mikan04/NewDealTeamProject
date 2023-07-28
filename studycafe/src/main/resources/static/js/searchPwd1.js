window.resizeTo(500, 500);
function searchPwdFindUsername() {
	var username = $("#username").val();
	$("#noUsername").css("display,none");

	if (username == "" || username.trim() == "") {
		alert("아이디를 입력해주세요.");
		return false;
	}

	$.ajax({
		type: "post",
		url: "/searchPwdFindUsername",
		data: {
			"username": username
		},
		dataType: "json",
		success: function(data) {
			if (data != null) {
				var username = data.username;
				sessionStorage.setItem("username", username);
				window.location.href = "/searchPwd2";
			}
		},
		error: function() {
			alert("존재하지 않는 아이디 입니다. 아이디를 다시 확인해주세요.");
		}
	})
}