var username = sessionStorage.getItem("username");

function updatePassword() {
	var password = $("#password").val();
	var rePassword = $("#rePassword").val();
	var passwordRegExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
	if (password == "" || password.trim() == "") {
		alert("비밀번호를 입력해주세요.");
		return false;
	} else if (password != rePassword) {
		alert("비밀번호가 다릅니다 다시 입력해주세요.");
		return false;
	} else if (!passwordRegExp.test(password)) {
		alert("8~15자의 영문,숫자,특수문자 조합해서 설정해주세요.");
		return false;
	}

	$.ajax({
		type: "post",
		url: "/updatePassword",
		data: {
			"username": username,
			"password": password,
		},
		dataType: "json",
		success: function(data) {
			if (data == true) {
				alert("비밀번호가 변경되었습니다.");
				window.close();
			} else {
				alert("기존 비밀번호와 같습니다. 다르게 설정해주세요.");
			}
		}
	})
}