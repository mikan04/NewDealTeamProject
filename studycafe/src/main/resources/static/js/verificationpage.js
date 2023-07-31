/**
 * 정보수정 검증
 */
 
 function verification(){
	 
	 let password = document.querySelector("#password").value;
	 
	 $.ajax({
			type : 'POST',
			url : '/member/verification',
			data : {
				"password" : password
			},
			dataType : 'json',
			
			success : function(result) {
				
				if(result === true){
					
					alert("비밀번호 확인이 완료되었습니다. 정보수정 페이지로 이동합니다.");
					
					location.href = "/member/modifyinfo";
					
				} else {
					
					alert("비밀번호가 일치하지 않습니다.");
					
					return false;
					
				}
				
			},
			
			error : function(error) {
				
				alert("알 수 없는 에러가 발생하였습니다.");
				
			}
		});
	 
 }