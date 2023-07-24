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
<link rel="stylesheet" type="text/css" href="/css/joinform.css">
<!-- index.css 복사해다가 jsp파일이랑 이름 똑같이 바꾸고 임포트해주세요. index.css에 따로 추가하거나 바꾸지 말아주세요-->

<style type="text/css">
</style>
<script type="text/javascript">
	
	
	document.addEventListener("keyup" ,function(){
		if($("#username").val().trim()!=""){
			$("#idCheckBtn").attr("disabled",false);
		} else {
			$("#idCheckBtn").attr("disabled",true);
		}
	})
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
				if (e === true) {
					$("#use").css("display","none");
					$("#notUse").css("display","block");
				}
			},
			error : function() {
				$("#use").css("display","block");
				$("#notUse").css("display","none");
			}
		});
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>

	<div class="main-wrap">
		<div class="index-ingredient">

			<div class="joininner">
				<form action="/joinPro" method="post" id="join_form" class="row g-3">
					<div id="content">

						<h2 class="blind"></h2>
						<div class="join_content">

							<div class="row_group">
								<div class="join_id">
									<h3 class="join_title">
										<label for="id">ID</label>
									</h3>
									<span class="ps_box int_id">
										<input type="text" id="username" name="username" maxlength="20">
									</span>
									<button type="button" id="idCheckBtn" name="idCheckBtn" onclick="idCheck()" disabled>중복체크</button>
									<span id="use" style="display: none; color: red;">사용 가능한 아이디 입니다.</span>
									<span id="notUse" style="display: none; color: red;" >이미 존재하는 아이디 입니다.</span>

								</div>

								<div>
									<h3 class="join_title">
										<label for="id">이메일</label>
									</h3>

									<div class="input-group">
										<input type="text" class="form-control" name="userEmail1" id="userEmail1" placeholder="이메일">
										<select class="form-control" name="userEmail2" id="userEmail2">
											<option>@naver.com</option>
											<option>@daum.net</option>
											<option>@gmail.com</option>
											<option>@hanmail.com</option>
											<option>@yahoo.co.kr</option>
											<option>직접입력</option>
										</select>
										<button type="button" class="btn btn-primary" id="mail-Check-Btn">본인인증</button>
										<div class="mail-check-box">
											<input class="form-control mail-check-input" placeholder="인증번호 6자리를 입력해주세요!" maxlength="6" style="display: none;">
										</div>
										<span id="mail-check-warn"></span>
									</div>
								</div>

								<div class="join_row">
									<h3 class="join_title">
										<label for="pswd1">비밀번호</label>
									</h3>
									<span class="ps_box int_pass" id="pswd1Img">
										<input type="password" id="password" name="password" class="int" title="비밀번호 입력" aria-describedby="pswd1Msg"
											maxlength="20">
										<span class="lbl">
											<span id="pswd1Span" class="step_txt"></span>
										</span>
									</span>
									<span class="error_next_box" id="pswd1Msg" style="display: none" aria-live="assertive">5~12자의 영문 소문자, 숫자와
										특수기호(_)만 사용 가능합니다.</span>

									<h3 class="join_title">
										<label for="pswd2">비밀번호 재확인</label>
									</h3>
									<span class="ps_box int_pass_check" id="pswd2Img">
										<input type="password" id="password2" name="password2" class="int" title="비밀번호 재확인 입력"
											aria-describedby="pswd2Blind" maxlength="20">
										<span id="pswd2Blind" class="wa_blind">설정하려는 비밀번호가 맞는지 확인하기 위해 다시 입력 해주세요.</span>
									</span>
									<span class="error_next_box" id="pswd2Msg" style="display: none" aria-live="assertive"></span>
								</div>
							</div>
							<!-- // 아이디, 비밀번호 입력 -->

							<div class="row_group">
								<div class="join_row">
									<h3 class="join_title">
										<label for="name">이름</label>
									</h3>
									<span class="ps_box box_right_space">
										<input type="text" id="name" name="name" title="이름" class="int" maxlength="40">
									</span>
									<span class="error_next_box" id="nameMsg" style="display: none" aria-live="assertive"></span>
								</div>
								<span class="error_next_box" id="genderMsg" style="display: none" aria-live="assertive"></span>

								<div class="join_row">
									<h3 class="join_title">
										<label for="nickname">닉네임</label>
									</h3>
									<span class="ps_box box_right_space">
										<input type="text" id=nickName name="nickName" title="닉네임" class="int" maxlength="40">
									</span>
									<span class="error_next_box" id="nameMsg" style="display: none" aria-live="assertive"></span>
								</div>


								<div class="join_row join_email">

									<h3 class="join_title">
										<label for="email">
											주소
											<span class="terms_choice">(선택)</span>
										</label>
									</h3>
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
								<!-- <button type="button" id="btnJoin" class="btn_type btn_primary">
									<span>가입하기</span>
								</button> -->
								<button type="submit">가입하기</button>
							</div>
						</div>
					</div>

				</form>
			</div>

		</div>
	</div>

	<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>
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