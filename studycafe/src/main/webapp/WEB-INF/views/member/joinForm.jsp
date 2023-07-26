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
					$("#useId").css("display", "none");
					$("#notUseId").css("display", "block");
				} else {
					$("#useId").css("display", "block");
					$("#notUseId").css("display", "none");
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
					$("#useNick").css("display", "block");
					$("#notUseNick").css("display", "none");
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
		$.ajax({
			type : "POST",
			url : "/send",
			data : {
				"toEmail" : email
			},
			success : function(data) {
				if (data != null) {
					alert(email + "로 이메일이 발송되었습니다" + data);
					$("#emailCheckBtn").css("display", "none");
					$("#emailCheck").css("display", "block");
					$("#emailReCheckBtn").css("display", "block");
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
				alert("Failed to send message: " + error); // Display the error message
			}
		});
	}
	function join() {
		var memberEntity = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email1").val() + $("#email2").val(),
			nickName : $("#nickName").val(),
			name : $("#name").val(),
			emailAuth : $("#emailAuth").val()
		};

		var memberAddressEntity = {
			zipcode : $("#zipcode").val(),
			address1 : $("#address1").val(),
			address2 : $("#address2").val()
		};

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
				if(e != null){
					alert("회원가입이 완료되었습니다.");
					location.href="/";
				}
			},
			error : function() {
				alert("회원가입 실패");
			},
		});
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
							<div class="join_id">
								<label id="join_title">아이디</label>
								<span class="ps_box int_id">
									<input type="text" id="username" name="username" maxlength="20" />
								</span>
								<button type="button" id="idCheckBtn" name="idCheckBtn" onclick="idCheck()" disabled>중복체크</button>
								<span id="useId" style="display: none; color: red;">사용 가능한 아이디 입니다.</span>
								<span id="notUseId" style="display: none; color: red;">이미 존재하는 아이디 입니다.</span>
								<span id="valiId">4 ~ 8자의 영문</span>
							</div>

							<div class="join_row">
								<label id="join_title">비밀번호</label>
								<span class="ps_box int_pass" id="pswd1Img">
									<input type="password" id="password" name="password" maxlength="20" />
									<span class="lbl">
										<span id="pswd1Span" class="step_txt"></span>
									</span>
								</span>
								<span class="error_next_box" id="pswd1Msg" style="display: none" aria-live="assertive">5~12자의 영문 소문자, 숫자와
									특수기호(_)만 사용 가능합니다.</span>
							</div>

							<div class="join_row">
								<label id="join_title">비밀번호 재확인</label>

								<span class="ps_box int_pass_check" id="pswd2Img">
									<input type="password" id="password2" name="password2" class="int" title="비밀번호 재확인 입력" aria-describedby="pswd2Blind"
										maxlength="20" />
								</span>
								<span class="error_next_box" id="pswd2Msg" style="display: none" aria-live="assertive"></span>
							</div>
						</div>

						<div class="join_row">

							<label id="join_title">닉네임</label>

							<span class="ps_box box_right_space">
								<input type="text" id=nickName name="nickName" title="닉네임" class="int" maxlength="40" />
							</span>
							<button type="button" id="nickCheckBtn" name="nickCheckBtn" onclick="nickCheck()" disabled>중복체크</button>
							<span id="useNick" style="display: none; color: red;">사용 가능한 닉네임 입니다.</span>
							<span id="notUseNick" style="display: none; color: red;">이미 존재하는 닉네임 입니다.</span>
							<span id="valiId">2글자 이상 6자리 이하</span>
						</div>

						<div class="row_group">
							<div class="join_row">

								<label id="join_title">이름</label>

								<span class="ps_box box_right_space">
									<input type="text" id="name" name="name" />
								</span>
								<span class="error_next_box" id="nameMsg" style="display: none" aria-live="assertive"></span>
							</div>
							<span class="error_next_box" id="genderMsg" style="display: none" aria-live="assertive"></span>

							<div>
								<label id="join_title">이메일</label>
								<div class="input-group">
									<input type="text" class="form-control" name="email1" id="email1" placeholder="이메일">
									<select class="form-control" name="email2" id="email2">
										<option>@naver.com</option>
										<option>@daum.net</option>
										<option>@gmail.com</option>
										<option>@hanmail.com</option>
										<option>@yahoo.co.kr</option>
										<option>직접입력</option>
									</select>
									<button type="button" class="btn btn-primary" id="emailCheckBtn" onclick="sendEmail()">본인인증</button>
									<div class="mail-check-box">
										<input id="emailCheck" placeholder="인증번호 8자리를 입력해주세요!" maxlength="8" style="display: none;">
										<button type="button" id="emailReCheckBtn" style="display: none">본인인증</button>
										<input id="emailAuth" style="display: none;" />
									</div>

								</div>
							</div>






							<div class="join_row">


								<label id="join_title"> 주소 </label>

								<div class="int_adress_area">
									<span class="ps_box int_address">
										<input type="text" id="zipcode" name="zipcode" placeholder="선택입력" aria-label="선택입력" class="int" maxlength="5">
									</span>
									<a href="javascript:kakaoPost();" class="btn_verify btn_primary gray zipcodebtn" id="btnSend" role="button">
										<span class="">우편번호</span>
									</a>
								</div>

								<span class="ps_box join_address">
									<input type="text" id="address1" name="address1" placeholder="선택입력" aria-label="선택입력" class="int" maxlength="100">
								</span>
								<span class="ps_box join_address">
									<input type="text" id="address2" name="address2" placeholder="선택입력" aria-label="선택입력" class="int" maxlength="100">
								</span>
							</div>
							<span class="error_next_box" id="emailMsg" style="display: none" aria-live="assertive"></span>
						</div>




						<div class="btn_area">
							<button type="button" onclick="join()">가입하기</button>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>

	<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
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
</body>
</html>