window.resizeTo(1000, 700);

function getOutTeam(data) {

	let teamNumber = $("#teamNumber").val();

	let teamHead = $("#teamHead").val();

	if (teamHead === data) {
		alert("팀장은 내보낼 수 없습니다.");

		return false;
	}

	console.log(teamNumber);

	$.ajax({
		type: "post",
		url: "/team/getoutteam",
		data: {
			"username": data,
			"teamNumber": teamNumber
		},
		dataType: "json",
		success: function(data) {

			alert("내보내기 성공");
			location.reload(true);

		}
	})
}

function deleteTeam(teamNumber) {

	$.ajax({
		type: "DELETE",
		url: "/team/delete",
		data: {
			"teamNumber": teamNumber
		},
		dataType: "json",

		success: function(data) {

			if (data) {
				alert("팀을 해체하였습니다.");

				opener.location.href = "/logout";
				
				window.close();
				
			} else {
				alert("알 수 없는 에러가 발생하였습니다.");
				
				return false;
			}

		},
		error : function(error){
			alert("관리자에게 문의해주세요. 팀 삭제 도중 알 수 없는 에러가 발생하였습니다.");
		}
	})

}