window.resizeTo(500, 500);
var username = sessionStorage.getItem("username");

function searchPwdFindUsername() {

	var email = $("#email").val();

	var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;

	if (email == "" || email.trim() == "") {

		alert("이메일을 반드시 입력해주세요.");
		return false;
	}

	if (!emailRegExp.test(email)) {
		alert("이메일 주소를 올바르게 입력해 주세요.");
		return false;
	}

	$.ajax({
		type: "post",
		url: "/searchPwdFindUsernameEmail",
		data: {
			"username": username,
			"email": email
		},
		dataType: "json",
		success: function(data) {
			var username = data.username;
			sessionStorage.setItem("username", username);
			window.location.href = "/searchPwd3";
		},
		error: function() {
			alert("이메일을 다시 확인해주세요.");
		}

	});
}