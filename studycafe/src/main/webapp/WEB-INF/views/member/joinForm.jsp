<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script language="JavaScript"
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최초접속페이지</title>
<style>
.gray {
	background-color: #8e8e8e;
}

.subMenu {
	height: 190px;
	background-color: #343434;
}


.h_logo {
	display: block;
	margin: 0 auto;
	width: 240px;
	height: 114px;
	background-image: url(../resources/img/LOGO2.png);
	background-repeat: no-repeat;
	background-position: 0 0;
	background-size: 240px auto;
	color: transparent;
	font-size: 0;
}

.joininner {
	max-width: 460px;
	margin: 0 auto;
}


.int_adress_area {
	position: relative;
	margin-top: 10px;
	padding: 0 125px 0 0;
}

.zipcodebtn {
	position: absolute;
	top: 0;
	right: 0;
	width: 115px;
	height: 51px;
	padding: 18px 0 16px;
	font-weight: 700;
	text-align: center;
	box-sizing: border-box;
	text-decoration: none;
}

.join_address {
	margin-top: 10px;
}
</style>
</head>
<body>
	<div class="subContent">
		<div>
			<div class="joininner">
				<form action="/joinPro" method="post" id="join_form" class="row g-3" onsubmit="">
					<div id="content">
						<!-- tg-text=title -->
						<h2 class="blind"></h2>
						<div class="join_content">
							<!-- 아이디, 비밀번호 입력 -->
							<div class="row_group">
								<div class="join_row">
									<h3 class="join_title">
										<label for="id">ID</label>
									</h3>
									<span class="ps_box int_id">
									<input type="text" id="username" name="username" class="int" title="EMAIL" maxlength="20">
									</span>
									<span class="error_next_box" id="emailMsg" style="display: none" aria-live="assertive"></span>

								</div>
								
								<div>
									<h3 class="join_title"> <label for="id">이메일</label></h3>
									<span>
										<input type="email" id="email" name="email">
									</span>
								</div>

								<div class="join_row">
									<h3 class="join_title">
										<label for="pswd1">비밀번호</label>
									</h3>
									<span class="ps_box int_pass" id="pswd1Img"> 
									<input type="password" id="password" name="password" class="int" title="비밀번호 입력" aria-describedby="pswd1Msg" maxlength="20">
										<span class="lbl">
										<span id="pswd1Span" class="step_txt"></span>
									</span>
									</span> 
										<span class="error_next_box" id="pswd1Msg"
										style="display: none" aria-live="assertive">5~12자의 영문
										소문자, 숫자와 특수기호(_)만 사용 가능합니다.</span>

									<h3 class="join_title">
										<label for="pswd2">비밀번호 재확인</label>
									</h3>
									<span class="ps_box int_pass_check" id="pswd2Img"> 
										<input type="password" id="password2" name="password2" class="int" title="비밀번호 재확인 입력" 
										aria-describedby="pswd2Blind"
										maxlength="20"> 
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
									<span class="ps_box box_right_space"> <input type="text" id="name" name="name" title="이름" class="int" maxlength="40">
									</span> 
									<span class="error_next_box" id="nameMsg" style="display: none" aria-live="assertive"></span>
								</div>
								<span class="error_next_box" id="genderMsg" style="display: none" aria-live="assertive"></span>
								
								<div class="join_row">
									<h3 class="join_title">
										<label for="nickname">닉네임</label>
									</h3>
									<span class="ps_box box_right_space"> <input type="text" id="nickName" name="nickName" title="닉네임" class="int" maxlength="40">
									</span> 
									<span class="error_next_box" id="nameMsg" style="display: none" aria-live="assertive"></span>
								</div>

				
								<div class="join_row join_email">
									<h3 class="join_title">
										<label for="email">주소<span class="terms_choice">(선택)</span></label>
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





					<!--                     
                    
					<div class="col-md-4" style="width: 100%;">
						<div class="join_Title">
							<label for="name" class="form-label">이름</label>
						</div>
						<input type="text" name="memberName" id="name" class="form-control" maxlength="10" />
						<div class="idmsg"></div>

					</div>
					<div class="col-md-4" style="width: 100%;">
						<div class="join_Title">
							<label for="id" class="form-label">아이디</label>
						</div>
						<input type="text" name="test" id="id" class="form-control"
							maxlength="10" required>
						<div class="valid-feedback">ㅇㅋ</div>
						<div class="invalid-feedback">아이디를 적으세요</div>

					</div>
					<div id="join_email">
						<div class="join_Title">이메일</div>
						<span class="insertbox"> <input type="text"
							name="memberEmail" id="email" class="form-control" maxlength="40">
						</span>
					</div>
					<div id="join_password1">
						<div class="join_Title">비밀번호</div>
						<span class="insertbox"> <input type="password"
							name="memberPassword" id="pswd1" class="form-control"
							maxlength="20">
						</span>
					</div>
					<div id="join_password2">
						<div class="join_Title">비밀번호 확인</div>
						<span class="insertbox"> <input type="password"
							id="pswd2" class="form-control" maxlength="20">
						</span>
					</div>
					<div id="join_phone">
						<div class="join_Title">연락처</div>
						<span class="insertbox"> <input type="text"
							name="memberMobile" id="phone" class="form-control"
							maxlength="11"> <input type="button" value="인증">
						</span>
					</div>
					<div id="join_adress">
						<div class="join_Title">주소</div>
						<span class="insertbox"> <input type="text"
							name="memberZipcode" id="memberZipcode" class="form-control"
							maxlength="5"> <input type="button" value="우편번호 찾기"
							onclick="kakaoPost()">
						</span> <span class="insertbox"> <input type="text"
							name="memberAddress" id="memberAddress" class="form-control"
							maxlength="20">
						</span> <span class="insertbox"> <input type="text"
							name="memberDetailAddress" id="memberDetailAddress"
							class="form-control" maxlength="33">
						</span>

					</div>
					<div class="btnCon">
						<button class="submitbtn reset" type="reset">
							<span>다시작성</span>
						</button>
						<button class="btn btn-primary" type="submit">
							<span>회원가입</span>
						</button>
					</div> -->
				</form>
			</div>
		</div>
	</div>


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