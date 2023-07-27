/**
 * Study Detail Kakao Map
 */

/** 
 * 게시글 삭제 Ajax
 * */
function studyDelete(id) {
	$.ajax({
		url: "/studydelete"
		, contentType : "application/json; charset=utf-8"
		, type : 'POST'
		, data : JSON.stringify({"id": id})
		, success: function(data) {
			if (data.status === 'ok' ) {
				alert(id + "번 게시글을 삭제했습니다.");
				location.href = "/study";
			} else {
				alert("관리자에게 문의해주세요.");
			}
		},
		error: function(errer) {
			console.log("에러 : " + error);
		}
	});
}

/** 
 * 카카오 맵 api
 * */

var lat = document.getElementById('lat').value;
var lng = document.getElementById('lng').value;

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 마커가 표시될 위치입니다 

var markerPosition  = new kakao.maps.LatLng(lat, lng);

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

searchDetailAddrFromCoords(marker.getPosition(), function(result, status) {
	
	if (status === kakao.maps.services.Status.OK) {
		document.getElementById('address_name').value = result[0].address.address_name; // 지번주소
	}
	
	road_address = !!result[0].road_address ?
		'<p><label for="road_address_name">도로명 주소</label>' + 
		'<input type="text" id="road_address_name" value="'+ result[0].road_address.address_name + '" readonly="readonly"><br></p>' : '';

	var resultDiv = document.getElementById('road_address');
	
	resultDiv.innerHTML = road_address;
});

function searchDetailAddrFromCoords(coords, callback) {
	// 좌표로 법정동 상세 주소 정보를 요청합니다
	geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

/** 
 * CK 에디터 
 * */

var myEditor;

ClassicEditor
	.create(document.querySelector('#studyContent'), {
		ckfinder: {
			uploadUrl: '/ck/teamregisimgupload' // 내가 지정한 업로드 url (post로 요청감)
		},
		removePlugins: ['Heading'],
		language: "ko"
	})
	.then(editor => {
		const toolbarElement = editor.ui.view.toolbar.element;
		
		toolbarElement.style.display = 'none';
		
		editor.enableReadOnlyMode('#studyContent');
	})
	.catch(error => {
		console.error(error);
	});

// 이미지 미리보기
$("#file").change(function() {
	if (this.files && this.files[0]) {
		var reader = new FileReader;
		reader.onload = function(data) {
			$(".select_img img").attr("src", data.target.result).width(350).height(350);
		}
		reader.readAsDataURL(this.files[0]);
	}
});