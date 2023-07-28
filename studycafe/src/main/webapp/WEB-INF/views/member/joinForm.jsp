<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>타이틀입력</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" type="text/css" href="/css/joinform.css">
<!-- index.css 복사해다가 jsp파일이랑 이름 똑같이 바꾸고 임포트해주세요. index.css에 따로 추가하거나 바꾸지 말아주세요-->

<style type="text/css">

	body{
	font-family: Dotum,'돋움',Helvetica,sans-serif;
    font-size: 15px;
	}
	
	input{
	width: 300px;
	border-color: hsla(220,9%,46%,.3);
	border-width: 1px;
	border-radius: 0.375rem;
	margin-top: 5px;
	}
	
	select{
	width: 300px;
	border-color: hsla(220,9%,46%,.3);
	border-width: 1px;
	border-radius: 0.375rem;
	margin-top: 5px;
	}
	
	.join_row{
	/* display: flex; */
    align-items: center;
    position: relative;
    box-sizing: border-box;
    max-width: 100%;
    min-height: 50px;
   	padding-top: 10px;
	}
	
 	/* 왜 다 없어짐? */
	/* .join_row id{
	position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    border-radius: inherit;
    border: 1px solid #dfdfdf;
    pointer-events: none;
    content: '';
	}  */
	
	
	
	.index-ingredient{
	margin-bottom: 15px;
	
	}
	
	.joininner{
	width: 400px;
	margin: auto;
	
	border-radius: 15px;
	padding: 10px
	} 
	
 	.join_content{
	
	
	}
	
	.btn_row{
	padding-top: 30px;
	text-align:center;
	}
	
	
	.join_btn{
	width: 400px;
    padding: 14px 0;
    border-radius: 6px;
    border: 1px solid rgba(0,144,249,.5);
    background: rgba(0,144,249,.5);
    font-size: 18px;
    font-weight: 700;
    line-height: 22px;
    color: #fff;
	}
	


</style>
<script type="text/javascript">
	document.addEventListener("keyup", function() {
		// 아이디 중복체크 버튼
		if ($("#username").val().trim() != "") {
			$("#idCheckBtn").attr("disabled", false);
		} else {
			$("#idCheckBtn").attr("disabled", true);
		}

		// 닉네임 중복체크 버튼
		if ($("#nickName").val().trim() != "") {
			$("#nickCheckBtn").attr("disabled", false);
		} else {
			$("#nickCheckBtn").attr("disabled", true);
		}
		// 이메일 본인인증 버튼
		if ($("#email1").val().trim() != "") {
			$("#emailCheckBtn").attr("disabled", false);
		} else {
			$("#emailCheckBtn").attr("disabled", true);
		}

	});
	// 아이디 중복 체크
	function idCheck() {
		var username = $("#username").val();
		$.ajax({
			type : "post",
			url : "/idCheck",
			data : {
				"username" : username
			},
			dataType : "json",
			success : function(e) {
				if (e == true) {
					$("#useUsername").css("display", "none");
					$("#notUseUsername").css("display", "block");
				} else {
					$("#notIdCheck").css("display","none");
					$("#useUsername").css("display", "block");
					$("#notUseUsername").css("display", "none");
					$("#idCheck").val("1");
				}
			},
			error : function() {
				alert("알수없는에러");
			}
		});
	}
	// 닉네임 중복 체크
	function nickCheck() {
		var nickName = $("#nickName").val();
		$.ajax({
			type : "post",
			url : "/nickCheck",
			data : {
				"nickName" : nickName
			},
			dataType : "json",
			success : function(e) {
				if (e == true) {
					$("#useNick").css("display", "none");
					$("#notUseNick").css("display", "block");
				} else {
					$("#notNickCheck").css("display", "none");
					$("#useNick").css("display", "block");
					$("#notUseNick").css("display", "none");
					$("#nickCheck").val("1");
				}
			},
			error : function() {
				alert("알수없는에러");
			}
		});
	}
	// 이메일 인증번호 전송
	function sendEmail() {
		var email = $("#email1").val() + $("#email2").val();
		alert(email + "로 이메일이 발송되었습니다");
		$("#notEmailAuth").css("display","none");
		$("#emailCheckBtn").css("display", "none");
		$("#emailCheck").css("display", "block");
		$("#emailReCheckBtn").css("display", "block");
		$.ajax({
			type : "POST",
			url : "/send",
			data : {
				"toEmail" : email
			},
			success : function(data) {
				if (data != null) {
					$("#emailReCheckBtn").on("click", function() {
						var emailCheck = $("#emailCheck").val();
						if (emailCheck == data) {
							alert("인증에 성공하였습니다.");
							$("#emailCheck").css("display", "none");
							$("#emailReCheckBtn").css("display", "none");
							$("#emailAuth").val("1");
						} else {
							alert("인증에 실패하였습니다.");
						}
					});
				}
			},
			error : function(xhr, status, error) {
				alert("Failed to send message: " + error); 
			}
		});
	}
	// 회원가입
	function join() {
		
		var username = $("#username").val();
		var password = $("#password").val();
		var email = $("#email1").val() + $("#email2").val();
		var nickName = $("#nickName").val();
		var name = $("#name").val();
		var zipcode = $("#zipcode").val();
		var address1 = $("#address1").val();
		var address2 = $("#address2").val();

		var memberEntity = { username,password,email,nickName,name };

		var memberAddressEntity = { zipcode,address1,address2 };

		var data = {
			memberEntity : memberEntity,
			memberAddressEntity : memberAddressEntity
		};
		console.log(data);
		$.ajax({
			type : "post",
			url : "/joinPro",
			contentType : 'application/json',
			data : JSON.stringify(data),
			dataType : "json",
			success : function(e) {
				if (e == true) {
					alert("회원가입이 완료되었습니다.");
					location.href = "/";
				}
			},
			error : function() {
				alert("회원가입 실패");
			},
		});
	}
	
	// 유효성 검사
	function checkAll() {
		
		var username = $("#username").val();
		var idCheck = $("#idCheck").val();
		var password = $("#password").val();
		var rePassword = $("#rePassword").val();		
		var email = $("#email1").val() + $("#email2").val();
		var nickName = $("#nickName").val();
		var nickCheck = $("#nickCheck").val();
		var name = $("#name").val();
		var emailAuth = $("#emailAuth").val();
		var zipcode = $("#zipcode").val();
		var address1 = $("#address1").val();
		var address2 = $("#address2").val();
		
        if (!checkUsername(username,idCheck)) {
            return false;
        } else if (!checkPassword(username, password,rePassword)) {
            return false;
        } else if (!checkNickName(nickName,nickCheck)) {
            return false;
        } else if (!checkName(name)){
        	return false;	
        }else if (!checkMail(email)) {
            return false;
        } else if (!checkEmailAuth(emailAuth)) {
            return false;
        } else if (!checkAddress(zipcode,address1)) {
            return false;
        }
        
        return join();
    }
	function checkUsername(username,idCheck){
		$("#notUsername").css("display","none");
		$("#notvaliUsername").css("display","none");
		$("#notIdCheck").css("display","none");
		if(username == ""){
			$("#notUsername").css("display","block");
			return false;
		} 
		var usernameRegExp =  /^[a-zA-z0-9]{6,12}$/;
		
		if(!usernameRegExp.test(username)){
			$("#notvaliUsername").css("display","block");
			return false;
		}
		if(idCheck != "1"){
			$("#notIdCheck").css("display","block");
			return false;
		}
		return true;
	}
	
	function checkPassword(username, password,rePassword){
		$("#notPwd").css("display","none");
		$("#notValiPwd").css("display","none");
		$("#notEqualPwd").css("display","none");
		$("#equalUsername").css("display","none");
		if(password == ""){
			$("#notPwd").css("display","block");
			return false;
		}
		 var passwordRegExp =  /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
		 
		 if(!passwordRegExp.test(password)){
			 $("#notValiPwd").css("display","block");
			 return false;
		 }
		 if(password != rePassword || rePassword == ""){
			 $("#notEqualPwd").css("display","block");
			 return false;
		 }
		 if(username == password){
			 $("#equalUsername").css("display","block");
			 return false;
		 }
		 
		 return true;
	}
	
	function checkNickName(nickName,nickCheck){
		$("#notNickName").css("display","none");
		$("#notValiNickName").css("display","none");
		$("#notNickCheck").css("display","none");
		if(nickName == ""){
			$("#notNickName").css("display","block");
			return false;
		}
		var nickNameRegExp = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,6}$/;
		
		if(!nickNameRegExp.test(nickName)){
			$("#notValiNickName").css("display","block");
			return false;
		}
		
		if(nickCheck != "1"){
			$("#notNickCheck").css("display","block");
			return false;
		}
		return true;
	}
	
	 function checkName(name) {
		$("#notName").css("display","none");
		$("#notValiName").css("display","none");

	 	if(name == ""){
	 		$("#notName").css("display","block");
	 		return false;
	 	}
		var nameRegExp = /^[가-힣]{2,4}$/;
		if (!nameRegExp.test(name)) {
				$("#notValiName").css("display","block");
	            return false;
	        }
	        return true; 
	    }
	
	function checkMail(email){
		 $("#notEmail").css("display","none");
		 
		 var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
		 
		 if(!emailRegExp.test(email) || email == ""){
			 $("#notEmail").css("display","block");

			 return false;
		 }
		 
		 return true;
	}
	
	 function checkEmailAuth(emailAuth) {
		 $("#notEmailAuth").css("display","none");
		 if(emailAuth != "1"){
			 $("#notEmailAuth").css("display","block");
			 return false;
		 }
		 return true;
	 }
	 function checkAddress(zipcode,address1) {
		 $("#notAddress").css("display","none");
		 if(zipcode == ""){
			 $("#notAddress").css("display","block");
			 return false;
		 } else if (address1 == ""){
			 $("#notAddress").css("display","block");
			 return false;
		 }
		 return true;
	 }
	 
	function kakaoPost() {
		new daum.Postcode({
			oncomplete : function(data) {
				document.querySelector("#zipcode").value = data.zonecode;
				document.querySelector("#address1").value = data.address
				document.getElementById("address2").focus();
			}
		}).open();

	}

</script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>

	<div class="main-wrap">
		<div class="index-ingredient">

			<div class="joininner">

				<div id="content">

					<h2 class="blind"></h2>
					<div class="join_content">

						<div class="row_group">
							<div class="join_row id">
								<label id="join_title">아이디</label>
								<div>
									<span class="ps_box int_id">
									<input type="text" id="username" name="username" maxlength="12" placeholder="영문소문자,숫자조합 (6~12자)" />
									</span>							
									<button type="button" id="idCheckBtn" name="idCheckBtn" onclick="idCheck()" disabled>중복체크</button>
								</div>
								<div>
									<input type="text" style="display: none;" value="0" id="idCheck" />
									<span id="useUsername" style="display: none; color: blue;">사용 가능한 아이디 입니다.</span>
									<span id="notUseUsername" style="display: none; color: red;">이미 존재하는 아이디 입니다.</span>
									<span id="notvaliUsername" style="display: none; color: red;">영문, 숫자 조합으로 6자이상 12자 이하의 영문만 입력해주세요.</span>
									<span id="notUsername" style="display: none; color: red;">아이디를 입력해주세요.</span>
									<span id="notIdCheck" style="display: none; color: red;">아이디를 중복체크를 해주세요.</span>
								</div>
							</div>

							<div class="join_row password">
								<label id="join_title">비밀번호</label>
								<div>
									<input type="password" id="password" name="password" maxlength="15" placeholder="영문,숫자,특수문자 조합 (8~15자)" />
								</div>
								<div>
									<span id="notValiPwd" style="display: none; color: red;">8~15자의 영문,숫자,특수문자 조합해서 설정해주세요.</span>
									<span id="notPwd" style="display: none; color: red;">비밀번호를 입력해주세요.</span>
									<span id="equalUsername" style="display: none; color: red;">아이디와 비밀번호를 다르게 입력해주세요.</span>
								</div>
							</div>

							<div class="join_row">
								<label id="join_title"><!-- 비밀번호 확인 --></label>
								<input type="password" id="rePassword" name="rePassword" maxlength="12" placeholder="비밀번호 재확인" />
								<span id="notRePwd" style="display: none; color: red;">비밀번호를 입력해주세요.</span>
								<span id="equalPwd" style="display: none; color: red;">비밀번호가 일치합니다.</span>
								<span id="notEqualPwd" style="display: none; color: red;">비밀번호가 일치하지 않습니다.</span>
							</div>
						</div>

						<div class="join_row">
							<label id="join_title">닉네임</label>
							<div>
								<input type="text" id=nickName name="nickName" title="닉네임" maxlength="6" placeholder="영문,숫자,한글(2~6자)" />
								<button type="button" id="nickCheckBtn" name="nickCheckBtn" onclick="nickCheck()" disabled>중복체크</button>
							</div>
								<div>
								<input type="text" style="display: none;" value="0" id="nickCheck" />
								<span id="useNick" style="display: none; color: blue;">사용 가능한 닉네임 입니다.</span>
								<span id="notUseNick" style="display: none; color: red;">이미 존재하는 닉네임 입니다.</span>
								<span id="notValiNickName" style="display: none; color: red;">2~6자로 입력해주세요.</span>
								<span id="notNickName" style="display: none; color: red;">닉네임을 입력해주세요.</span>
								<span id="notNickCheck" style="display: none; color: red;">닉네임 중복체크를 해주세요.</span>
							</div>	
						</div>

						<div class="join_row">
							<label id="join_title">이름</label>
							<div>
								<input type="text" id="name" name="name" placeholder="한글 (2~4자)" />
							</div>
							<div>
								<span id="notName" style="display: none; color: red;">이름을 입력해주세요.</span>
								<span id="notValiName" style="display: none; color: red;">2~4자 이내의 한글로 입력해주세요.</span>
							</div>
						</div>

						<div class="join_row">
							<label id="join_title">이메일</label>
							<div>
								<input type="text" class="form-control" name="email1" id="email1" placeholder="이메일">
								<select class="form-control" name="email2" id="email2">
									<option>@naver.com</option>
									<option>@daum.net</option>
									<option>@gmail.com</option>
									<option>@hanmail.com</option>
									<option>@yahoo.co.kr</option>
								</select>
								<button type="button" id="emailCheckBtn" onclick="sendEmail()" disabled>본인인증</button>
							</div>
							<div>
								<span id="notEmail" style="display: none; color: red;">이메일 주소를 올바르게 입력해 주세요.</span>
								<span id="notEmailAuth" style="display: none; color: red;">본인인증이 필요합니다.</span>
								<div class="mail-check-box">
									<input id="emailCheck" placeholder="인증번호 8자리를 입력해주세요!" maxlength="8" style="display: none;">
									<button type="button" id="emailReCheckBtn" style="display: none">본인인증</button>
									<input id="emailAuth" style="display: none;" />
								</div>
							</div>	

						</div>

						<div class="join_row">

							<div>
								<label id="join_title"> 주소 </label>
								<a href="javascript:kakaoPost();" class="btn_verify btn_primary gray zipcodebtn" id="btnSend" role="button">
									<span class="">주소검색</span>
								</a>
							</div>

							<input type="text" id="zipcode" name="zipcode" placeholder="우편번호" readonly>
						</div>
						<input type="text" id="address1" name="address1" placeholder="주소" readonly>
						<input type="text" id="address2" name="address2" placeholder="상세주소">
						<span id="notAddress" style="display: none; color: red;">주소를 입력해주세요.</span>
					</div>

				</div>
			</div>
			<div class="btn_row">
					<button class="join_btn" type="button" onclick="checkAll()">가입하기</button>
			</div>
		</div>
		
	</div>



	<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>
</body>
</html>