/**
 *  팀 등록 글 작성 js
 */
// ck에디터
var myEditor;
ClassicEditor
	.create(document.querySelector('#content'), {
		ckfinder: {
			uploadUrl: '/ck/teamregisimgupload' // 내가 지정한 업로드 url (post로 요청감)
		},
		removePlugins: ['Heading'],
		language: "ko"
	})
	.then(editor => {
		console.log('Editor was initialized', editor);
		myEditor = editor;
	})
	.catch(error => {
		console.error(error);
		alert("2mb 이하의 파일만 업로드 가능합니다.");
	});

// 이미지 미리보기
$("#file").change(function() {
	if (this.files && this.files[0]) {
		var reader = new FileReader;
		reader.onload = function(data) {
			$(".select_img img").attr("src", data.target.result);
		}
		reader.readAsDataURL(this.files[0]);
	}
});

function validPost(){
	let errorMsg = document.querySelectorAll(".error-box > input");
	
	if(errorMsg != null || errorMsg != ""){
		for(let i = 0; i < errorMsg.length; i++){
			console.log(errorMsg[i].value);
			alert(errorMsg[i].value);
		}
	}
}