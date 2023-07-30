<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DSSEAD 회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" type="text/css" href="/css/modifymember.css">
<script src="/js/joinform.js"></script>
</head>
<body>

	<div class="main-wrap">
		<div class="index-ingredient">

			<div class="joininner">

				<div id="content">

					<a href="/"><img src="/img/logo.png" class="logo"></a>

					<div class="join_top">회원정보 수정</div>

					<h2 class="blind"></h2>
					<div class="join_content">

						<div class="row_group">
							<div class="join_row id">
								<label id="join_title">아이디</label>
								<div>
									<span class="ps_box int_id">
										<input type="text" id="username" name="username" maxlength="12" value="${member.username }" readonly="readonly"/>
									</span>
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
								<label id="join_title">
									비밀번호 확인
								</label>
								<input type="password" id="rePassword" name="rePassword" maxlength="12" placeholder="비밀번호 재확인" />
								<span id="notRePwd" style="display: none; color: red;">비밀번호를 입력해주세요.</span>
								<span id="equalPwd" style="display: none; color: red;">비밀번호가 일치합니다.</span>
								<span id="notEqualPwd" style="display: none; color: red;">비밀번호가 일치하지 않습니다.</span>
							</div>
						</div>

						<div class="join_row">
							<label id="join_title">닉네임</label>
							<div>
								<input type="text" id=nickName name="nickName" title="닉네임" maxlength="6" value="${member.nickName }" />
								<button type="button" id="nickCheckBtn" name="nickCheckBtn" onclick="nickCheck()" disabled>중복체크</button>
							</div>
							<div>
								<input type="text" style="display: none;" value="0" id="nickCheck" />
								<span id="useNick" style="display: none; color: blue;">사용 가능한 닉네임 입니다.</span>
								<span id="notUseNick" style="display: none; color: red;">이미 존재하는 닉네임 입니다.</span>
								<span id="notValiNickName" style="display: none; color: red;">2~12자로 입력해주세요.</span>
								<span id="notNickName" style="display: none; color: red;">닉네임을 입력해주세요.</span>
								<span id="notNickCheck" style="display: none; color: red;">닉네임 중복체크를 해주세요.</span>
							</div>
						</div>

						<div class="join_row">
							<label id="join_title">이름</label>
							<div>
								<input type="text" id="name" name="name" value="${member.name }" readonly="readonly"/>
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
							</div>

							<input type="text" id="zipcode" name="zipcode" value="${memberAddress.zipcode }" readonly onclick="javascript:kakaoPost();">
						</div>
						<input type="text" id="address1" name="address1" value="${memberAddress.address1 }" readonly onclick="javascript:kakaoPost();">
						<input type="text" id="address2" name="address2" value="${memberAddress.address2 }">
						<span id="notAddress" style="display: none; color: red;">주소를 입력해주세요.</span>
					</div>

				</div>
			</div>
			<div class="btn_row">
				<button class="join_btn" type="button" onclick="checkAll()">가입하기</button>
			</div>
		</div>
	</div>
</body>
</html>