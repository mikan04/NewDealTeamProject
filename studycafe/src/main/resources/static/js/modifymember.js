document.addEventListener("keyup", function() {
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

// 정보 수정 완료시
function updateMember() {
	let username = $("#username").val();
	let nickName = $("#nickName").val();

	let zipcode = $("#zipcode").val();
	let address1 = $("#address1").val();
	let address2 = $("#address2").val();

	let memberDto = {
		'username': username,
		'nickName': nickName,
		'zipcode': zipcode,
		'address1': address1,
		'address2': address2

	};

	$.ajax({
		type: "PATCH",
		url: "/member/updateinfo",
		contentType: 'application/json',
		data: JSON.stringify(memberDto),
		dataType: "json",

		success: function(result) {

			alert("회원정보 수정이 완료되었습니다.");

			if (result) {
				location.href = "/logout";
			}
		},
		error: function() {

			alert("알수없는에러");

		}
	});
}
