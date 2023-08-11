/**
 * 
 */

// Enter 키 방지
function preventSubmit(event) {
    if (event.key === "Enter") {
        searchPlaces();
        return false;
    }
}

// 유효성 검사
function regis_check() {
	var studytitle = $('#studyTitle');
	var studyContent = myEditor.getData();
	
	if (studytitle.val() == "") {
		alert("제목을 입력해주세요.");
		studytitle.focus();
		return false;
	}
	
	if (!studyContent.trim()) {
        alert('내용을 입력해주세요.');
        myEditor.focus();
        return;
    }

	document.modify_form.submit();
}

// ck에디터
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
		console.log('Editor was initialized', editor);
		myEditor = editor;
	})
	.catch(error => {
		console.error(error);
	});

// 이미지 미리보기 아직안씀
$("#file").change(function() {
	if (this.files && this.files[0]) {
		var reader = new FileReader;
		reader.onload = function(data) {
			$(".select_img img").attr("src", data.target.result).width(350).height(350);
		}
		reader.readAsDataURL(this.files[0]);
	}
});