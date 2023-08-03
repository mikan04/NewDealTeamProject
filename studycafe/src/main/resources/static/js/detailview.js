/**
 *  팀 등록 글 조회
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
		const toolbarElement = editor.ui.view.toolbar.element;

		toolbarElement.style.display = 'none';

		editor.enableReadOnlyMode('#content');
	})
	.catch(error => {
		console.error(error);
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

// 게시글 삭제
function removePost(idx, identifier) {

	let askRemove = confirm("게시글을 정말 삭제하시겠습니까?");

	if (askRemove == true) {
		
		if (identifier === "tb") {
			$.ajax({
				url: identifying(identifier).tb,
				type: "DELETE",
				data: {
					"idx": idx
				},
				dataType: "json",

				success: function(result) {

					if (result == true) {
						alert("삭제가 완료되었습니다.");

						location.href = identifying(identifier).tbHref;
					}
				},
				error: function(error) {

					alert("에러가 발생하였습니다.");
				}
			})
			
		} else if(identifier === "cs"){
			
			$.ajax({
				url: identifying(identifier).cs,
				type: "DELETE",
				data: {
					"idx": idx
				},
				dataType: "json",

				success: function(result) {

					if (result == true) {
						alert("삭제가 완료되었습니다.");

						location.href = identifying(identifier).csHref;
					}
				},
				error: function(error) {

					alert("에러가 발생하였습니다.");
				}
			})
		}
		
	}

}

function identifying(identifier) {

	let tbArr = {
		tb: "/team/removepost",
		tbHref: "/team/teamboards?page=0"
	};

	let csArr = {
		cs: "/cs/removepost",
		csHref: "/cs/csboard?page=0"
	};

	if (identifier === "tb") {
		return tbArr;

	} else if (identifier === "cs") {
		return csArr;
	}
}