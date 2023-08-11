/**
 * Study Detail Kakao Map
 */

/** 
 * 게시글 삭제 Ajax
 * */
function studyDelete() {

	var jsonData = {
			id: $('#studyNum').val()
		};
	
	$.ajax({
		url: "/studydelete"
		, contentType : "application/json; charset=utf-8"
		, type : 'POST'
		, dataType : 'json'
		, data : JSON.stringify(jsonData)
		, success: function(data) {
			if (data.status === 'ok' ) {
				alert("게시글을 삭제했습니다.");
				location.href = "/study";
			} else {
				alert("접근 경로가 잘 못 되었습니다.");
			}
		},
		error: function(errer) {
			console.log("에러 : " + error);
		}
	});
}

/** 
 * 댓글 입력, 삭제, 수정 Ajax
 * */

var comment_counts = {}; // 대댓글 클릭 카운트
var modify_counts = {}; // 댓글 수정 클릭 카운트

function studyReplyInsert(index, ref) {
	var jsonData = {};

	if (ref != null) { // 대댓글
		jsonData = {
			studyEntity: {
				studyNum: $('#studyNum').val()
			},
			studyReplyWriter: $('#nickName').val(),
			studyReplyContent: $('#re_comment_' + index).val(),
			studyReplyDepth: 1,
			studyReplyRef: ref
		};
	} else { // 일반 댓글
		jsonData = {
			studyEntity: {
				studyNum: $('#studyNum').val()
			},
			studyReplyWriter: $('#nickName').val(),
			studyReplyContent: $('#comment').val(),
			studyReplyDepth: 0
		};
	}
	
	$.ajax({
		url: "/studyReplyInsert"
		, contentType: "application/json; charset=utf-8"
		, type : "POST"
		, data : JSON.stringify(jsonData)
		, success: function(data) {
			if (data.status === "ok") {
				modify_counts[index]++;
				comment_counts[index]++;
				$('#comment').val('');
				studyReplyList();	
			} else {
				alert("접근 경로가 잘 못 되었습니다.");
			}
		},
		error: function(errer) {
			console.log("에러 : " + errer);
		}
	});
}

function studyReplyDelete(id) {
	
	var jsonData = {
		studyReplyNum: id
	};
	
	$.ajax({
		url: "/studyReplyDelete"
		, contentType: "application/json; charset=utf-8"
		, type : "POST"
		, data : JSON.stringify(jsonData)
		, success: function(data) {
			if (data.status === "ok") {
				alert("댓을을 삭제했습니다.");
				studyReplyList();
			} else {
				alert("접근 경로가 잘 못 되었습니다.");
			}
		},
		error: function(errer) {
			console.log("에러 : " + errer);
		}
	});
}

function studyModify(index) {
	var jsonData = {
		studyEntity: {
			studyNum: $('#studyNum').val()
		},
		studyReplyNum: $('#replyNum_' + index).val(),
		studyReplyContent: $('#modify_comment_' + index).val(),
		studyReplyWriter: $('#studyReplyWriter_' + index).val(),
		studyReplyRef: $('#studyReplyRef_' + index).val(),
		studyReplyDepth: $('#studyReplyDepth_' + index).val()
	};
	
	$.ajax({
		url: "/studyReplyModify"
		, contentType: "application/json; charset=utf-8"
		, type : "POST"
		, data : JSON.stringify(jsonData)
		, success: function(data) {
			if (data.status === "ok") {
				modify_counts[index]++;
				comment_counts[index]++;
				studyReplyList();	
			} else {
				alert("접근 경로가 잘 못 되었습니다.");
			}
		},
		error: function(errer) {
			console.log("에러 : " + errer);
		}
	});
}

function studyReplyList() {
	var jsonData = {
		studyEntity: {
			studyNum: $('#studyNum').val()
		}
	};
	
	$.ajax({
		url: "/studyReplyList"
		, contentType: "application/json; charset=utf-8"
		, type : "POST"
		, data : JSON.stringify(jsonData)
		, success: function(data) {
			var postList = $('#reply-list');
			
			postList.empty(); // #reply-list 비우기
			
			// 댓글이 있을 경우
			if (data.length) {
				$.each (data, function (index, post) {
					var day = new Date(post.studyReplyDate);

					if (post.studyEntity.studyWriter === post.studyReplyWriter) {
						img = "/img/user_writer.png"; // 글쓴이 이미지
					} else {
						img = "/img/user.png"; // 일반 이미지
					}
					
					// 댓글 깊이 (대댓글 일 경우)
					if (post.studyReplyDepth === 1) {
						imgClass = "thumb_re" // 이미지 클래스
						contentClass = "reply-content reply-re_content"; // 댓글 클래스
					} else {
						imgClass = "thumb"
						contentClass = "reply-content";
					}
					htmls = '';
					htmls = '' +
						'<li>' +
						'<div class="' + imgClass +'">' +
						'<img src="' + img + '" width="48" height="48" class="">' +
						'</div>' +
						'<div class="' + contentClass + '">' +
						'<ul class="info">' +
						'<li class="nickname" id="nickname_' + index + '">' + post.studyReplyWriter + '</li>' +
						'<li class="date">&nbsp;&nbsp;' + dateFormat(day) + '</li>' +
						'</ul>' +
						'<p class="text" id="reply_content_' + index + '">' + post.studyReplyContent + '</p>' +
						'<ul class="control">'
					if ($('#nickName').val() != null) 
					htmls += !!post.studyReplyDepth ? '' : '<li><a href="#" onclick="re_comment_open(' + index + ', ' + post.studyReplyRef + '); return false;" class="link_reply"><i class="fa fa-comment"></i>답변달기</a>';
					if ($('#nickName').val() === post.studyReplyWriter)
					htmls += '<li><a href="#" onclick="comment_modify_open(' + index + ', ' + post.studyReplyRef + '); return false;" class="link_reply"><i class="fa-solid fa-pencil"></i>수정</a>' +
						'<li><a href="#" onclick="studyReplyDelete(' + post.studyReplyNum + '); return false;" class="link_reply"><i class="fa-solid fa-trash-can"></i>삭제</a>'
					htmls += '</ul>' +
						'<input type="hidden" id="replyNum_' + index + '" value="' + post.studyReplyNum + '">' +
						'<input type="hidden" id="studyReplyWriter_' + index + '" value="' + post.studyReplyWriter + '">' +
						'<input type="hidden" id="studyReplyRef_' + index + '" value="' + post.studyReplyRef + '">' +
						'<input type="hidden" id="studyReplyDepth_' + index + '" value="' + post.studyReplyDepth + '">' +
						'<div id="re_comment_open_' + index + '"></div>' +
						'<div id="comment_modify_open_' + index + '"></div>' +
						'</div>' +
						'</li>'
					postList.append(htmls);
				});
			}
		},
		error: function(errer) {
			console.log("에러 : " + errer);
		}
	});
}

// 대댓글 창
function re_comment_open(index, ref) {
	if (!comment_counts[index]) {
		comment_counts[index] = 0;
	}

	var comment = $('#re_comment_open_' + index);
	var modify = $('#comment_modify_open_' + index);

	if (comment_counts[index] % 2 === 0) {
		if (modify_counts[index] % 2 !== 0) {
			modify_counts[index] = 0;
			modify.empty(); // 댓글 수정 창 초기화
		}
		
		comment.append(
			'<textarea class="comment" id="re_comment_' + index + '" rows="5" placeholder="코멘트 달기"></textarea>' +
			'<button class="btn btn-dark" id="re_commentBtn_' + index + '" type="button" onclick="studyReplyInsert(' + index + ', ' + ref + ');">작성</button>'
    	);
	} else {
		comment.empty();
	}
	
	comment_counts[index]++;
}

// 댓글 수정 창
function comment_modify_open(index, ref) {
	if (!modify_counts[index]) {
		modify_counts[index] = 0;
	}

	var comment = $('#re_comment_open_' + index);
	var modify = $('#comment_modify_open_' + index);
	
	if (modify_counts[index] % 2 === 0) {
		if (comment_counts[index] % 2 !== 0) {
			comment_counts[index] = 0;
			comment.empty(); // 대댓글 창 초기화
		}
				
		modify.append(
			'<textarea class="comment" id="modify_comment_' + index + '" rows="5" placeholder="' + $("#reply_content_" + index).text() + '"></textarea>' +
			'<button class="btn btn-dark" id="modify_commentBtn_' + index + '" type="button" onclick="studyModify(' + index + ')">수정</button>'
    	);
	} else {
		modify.empty();
	}
	
	modify_counts[index]++;
}

function dateFormat(date) {
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;
    hour = hour >= 10 ? hour : '0' + hour;
    minute = minute >= 10 ? minute : '0' + minute;
    second = second >= 10 ? second : '0' + second;

    return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute;
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