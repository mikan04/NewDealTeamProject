/**
 *  아이디 비번찾기 헤더 js
 */
window.onload = function() {
	
	let searchId = document.querySelector(".search-id");
	let searchPwd = document.querySelector(".search-pwd");
	
	if (window.location.href.includes("findAccount")) {
		searchId.style.background="white";
		searchId.style.border="none";
	}
}
