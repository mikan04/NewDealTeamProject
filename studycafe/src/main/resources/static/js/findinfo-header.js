/**
 *  아이디 비번찾기 헤더 js
 */
window.onload = function() {
	
	let searchId = document.querySelector(".search-id");
	let searchPwd = document.querySelector(".search-pwd");
	
	let locationId = document.querySelector("#location-id");
	let locationPwd = document.querySelector("#location-pwd");
	
	if (window.location.href.includes("findAccount")) {
		searchPwd.style.background="white";
		searchPwd.style.border="none";
		searchId.style.borderBottom="none";
		
		locationId.style.color="#6e81df";
		searchId.addEventListener("mouseover",function(){
			searchId.style.pointerEvents="none";
		})
		
	} else if(window.location.href.includes("searchPwd1")){
		searchId.style.background="white";
		searchId.style.border="none";
		searchPwd.style.borderBottom="none";
		
		locationPwd.style.color="#6e81df";
		searchPwd.addEventListener("mouseover",function(){
			searchPwd.style.pointerEvents="none";
		})
	}
}
