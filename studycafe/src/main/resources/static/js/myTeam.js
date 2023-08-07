function getOutTeam(data) {

	let teamNumber = $("#teamNumber").val();
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
			location.reload();
		}
	})
}