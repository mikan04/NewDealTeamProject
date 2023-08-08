/**
 * resultAuth Detail Kakao Map
 */

/** 
 * 게시글 삭제 Ajax
 * */
function resultAuthDelete() {

	var jsonData = {
			id: $('#resultAuthNum').val()
		};
	
	$.ajax({
		url: "/auth/delete"
		, contentType : "application/json; charset=utf-8"
		, type : 'POST'
		, dataType : 'json'
		, data : JSON.stringify(jsonData)
		, success: function(data) {
			if (data.status === 'ok' ) {
				alert("게시글을 삭제했습니다.");
				location.href = "/auth";
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
 * 댓글 입력 Ajax
 * */

function resultAuthScore() {
	var jsonData = {
			id: $('#resultAuthNum').val(),
			score: $('input[name=rating]:checked').val(),
			comment: $('#comment').val()
		};
	
	$.ajax({
		url: "/auth/scoreinsert"
		, contentType : "application/json; charset=utf-8"
		, type : 'POST'
		, dataType : 'json'
		, data : JSON.stringify(jsonData)
		, success: function(data) {
			if (data.status === 'ok' ) {
				alert("코멘트를 작성했습니다.");
				location.href = "/auth";
			} else {
				alert("접근 경로가 잘 못 되었습니다.");
			}
		},
		error: function(errer) {
			console.log("에러 : " + error);
		}
	});
}

function resultAuthScoreList() {
	
	var jsonData = {
			id: $('#resultAuthNum').val()
		};
	
	$.ajax({
		url: "/auth/score"
		, contentType: "application/json; charset=utf-8"
		, type : "POST"
		, data : JSON.stringify(jsonData)
		, success: function(data) {
			var postList = $('#reply-list');
		
			postList.empty();

			if (data.entity.resultAuthScore != 0) {

				var currentDate = new Date(data.entity.commentDate);
				var score = data.entity.resultAuthScore;
				var str = "";
				
				for (i=0; i<5; i++) {
					if (i < score) {str += "★ ";} else {str += "☆ ";}
				}
	
				htmls = '' +
						'<li>' +
						'<div class="thumb">' +
						'<img src="/img/user_writer.png" width="48" height="48" class="">' +
						'</div>' +
						'<div class="reply-content">' +
						'<ul class="info">' +
						'<li class="nickname" id="nickname">관리자</li>' +
						'<li class="date">&nbsp;&nbsp;' + dateFormat(currentDate) + '</li>' +
						'</ul>' +
						'<p>' +
						'<label class="star">' + str + '</label>' +
						'</p>' +
						'<p class="text" id="reply_content">' + data.entity.resultAuthComment + '</p>' +
						'</div>' +
						'</li>';
						
				postList.append(htmls);
			}
		},
		error: function(errer) {
			console.log("에러 : " + errer);
		}
	});
}

/** 
 * CK 에디터 
 * */

var myEditor;

ClassicEditor
	.create(document.querySelector('#resultAuthContent'), {
		ckfinder: {
			uploadUrl: '/ck/teamregisimgupload' // 내가 지정한 업로드 url (post로 요청감)
		},
		removePlugins: ['Heading'],
		language: "ko"
	})
	.then(editor => {
		const toolbarElement = editor.ui.view.toolbar.element;
		
		toolbarElement.style.display = 'none';
		
		editor.enableReadOnlyMode('#resultAuthContent');
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

        return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
}