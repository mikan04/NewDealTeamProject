$(document).ready(function() {
	var username = sessionStorage.getItem("username");
	console.log(username);
	$("#username").html(username);
});
function searchPwd() {
	sessionStorage.removeItem("username");
	window.location.href = "/searchPwd1";
}