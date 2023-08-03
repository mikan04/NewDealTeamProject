
document.addEventListener("keyup", function() {
	var username = $("#username").val();

	var usernameRegExp = /^[a-zA-z0-9]{6,12}$/;

	// 아이디 중복체크 버튼
	if (username == "" || username.trim() == "" || !usernameRegExp.test(username)) {
		$("#idCheckBtn").attr("disabled", true);
	} else {
		$("#idCheckBtn").attr("disabled", false);
	}

	// 닉네임 중복체크 버튼
	if ($("#nickName").val().trim() != "") {
		$("#nickCheckBtn").attr("disabled", false);
	} else {
		$("#nickCheckBtn").attr("disabled", true);
	}
});

// 닉네임 중복 체크
function nickCheck() {
	var nickName = $("#nickName").val();
	$("#notUsername").css("display", "none");
	$("#notvaliUsername").css("display", "none");
	$("#notIdCheck").css("display", "none");
	$.ajax({
		type: "post",
		url: "/nickCheck",
		data: {
			"nickName": nickName
		},
		dataType: "json",
		success: function(e) {
			if (e == true) {
				$("#useNick").css("display", "none");
				$("#notUseNick").css("display", "block");
			} else {
				$("#notNickCheck").css("display", "none");
				$("#useNick").css("display", "block");
				$("#notUseNick").css("display", "none");
				$("#nickCheck").val("1");
			}
		},
		error: function() {
			alert("알수없는에러");
		}
	});
}

// 회원가입
function join() {

	var username = $("#username").val();
	var password = $("#password").val();
	var nickName = $("#nickName").val();
	var name = $("#name").val();
	var zipcode = $("#zipcode").val();
	var address1 = $("#address1").val();
	var address2 = $("#address2").val();

	var memberEntity = { username, password, nickName, name };

	var memberAddressEntity = { zipcode, address1, address2 };

	var data = {
		memberEntity: memberEntity,
		memberAddressEntity: memberAddressEntity
	};
	console.log(data);
	$.ajax({
		type: "post",
		url: "/joinPro",
		contentType: 'application/json',
		data: JSON.stringify(data),
		dataType: "json",
		success: function(e) {
			if (e == true) {
				alert("회원가입이 완료되었습니다.");
				location.href = "/";
			}
		},
		error: function() {
			alert("회원가입 실패");
		},
	});
}

// 유효성 검사
function checkAll() {

	var username = $("#username").val();
	var idCheck = $("#idCheck").val();
	var password = $("#password").val();
	var rePassword = $("#rePassword").val();
	var nickName = $("#nickName").val();
	var nickCheck = $("#nickCheck").val();
	var name = $("#name").val();
	var zipcode = $("#zipcode").val();
	var address1 = $("#address1").val();

	if (!checkPassword(username, password, rePassword)) {
		return false;
	} else if (!checkNickName(nickName, nickCheck)) {
		return false;
	} else if (!checkName(name)) {
		return false;
	} else if (!checkAddress(zipcode, address1)) {
		return false;
	}

	return join();
}

function checkPassword(username, password, rePassword) {
	$("#notPwd").css("display", "none");
	$("#notValiPwd").css("display", "none");
	$("#notEqualPwd").css("display", "none");
	$("#equalUsername").css("display", "none");
	if (password == "" || password.trim() == "") {
		$("#notPwd").css("display", "block");
		return false;
	}

	var passwordRegExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;

	if (password != rePassword || rePassword == "" || rePassword.trim() == "") {
		$("#notEqualPwd").css("display", "block");
		return false;
	}
	if (!passwordRegExp.test(password)) {
		$("#notValiPwd").css("display", "block");
		return false;
	}
	if (username == password) {
		$("#equalUsername").css("display", "block");
		return false;
	}

	return true;
}

function checkNickName(nickName, nickCheck) {
	$("#notNickName").css("display", "none");
	$("#notValiNickName").css("display", "none");
	$("#notNickCheck").css("display", "none");
	if (nickName == "") {
		$("#notNickName").css("display", "block");
		return false;
	}
	var nickNameRegExp = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,12}$/;

	if (!nickNameRegExp.test(nickName)) {
		$("#notValiNickName").css("display", "block");
		return false;
	}

	if (nickCheck != "1") {
		$("#notNickCheck").css("display", "block");
		return false;
	}
	return true;
}

function checkName(name) {
	$("#notName").css("display", "none");
	$("#notValiName").css("display", "none");

	if (name == "" || name.trim() == "") {
		$("#notName").css("display", "block");
		return false;
	}
	var nameRegExp = /^[가-힣]{2,4}$/;
	if (!nameRegExp.test(name)) {
		$("#notValiName").css("display", "block");
		return false;
	}
	return true;
}

function checkAddress(zipcode, address1) {
	$("#notAddress").css("display", "none");
	if (zipcode == "" || zipcode.trim() == "") {
		$("#notAddress").css("display", "block");
		return false;
	} else if (address1 == "" || address1.trim() == "") {
		$("#notAddress").css("display", "block");
		return false;
	}
	return true;
}

function kakaoPost() {
	new daum.Postcode({
		oncomplete: function(data) {
			document.querySelector("#zipcode").value = data.zonecode;
			document.querySelector("#address1").value = data.address
			document.getElementById("address2").focus();
		}
	}).open();

}

function zip() {

}

