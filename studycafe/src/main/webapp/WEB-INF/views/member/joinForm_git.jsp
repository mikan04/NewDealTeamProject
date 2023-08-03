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
<link rel="stylesheet" type="text/css" href="/css/joinform.css">
<script src="/js/joinform_git.js"></script>
</head>
<body>
	<div class="main-wrap">
		<div class="index-ingredient">

			<div class="joininner">

				<div id="content">

					<img src="/img/logo.png" class="logo" onclick="location.href='/'">

					<div class="join_top">회원가입에 필요한 기본정보를 입력해주세요.</div>

					<h2 class="blind"></h2>
					<div class="join_content">

						<div class="row_group">
							<div class="join_row id">
								<input id="username" name="username" maxlength="12" value="git_${id}" readonly="readonly" />
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
									<!-- 비밀번호 확인 -->
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
								<input type="text" id=nickName name="nickName" title="닉네임" maxlength="6" placeholder="영문,숫자,한글(2~12자)" />
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
								<input type="text" id="name" name="name" placeholder="한글 (2~4자)" />
							</div>
							<div>
								<span id="notName" style="display: none; color: red;">이름을 입력해주세요.</span>
								<span id="notValiName" style="display: none; color: red;">2~4자 이내의 한글로 입력해주세요.</span>
							</div>
						</div>

						<div class="join_row">

							<div>
								<label id="join_title"> 주소 </label>
								<!-- <a href="javascript:kakaoPost();" class="btn_verify btn_primary gray zipcodebtn" id="btnSend" role="button">
									<span class="">주소검색</span>
								</a> -->
							</div>

							<input type="text" id="zipcode" name="zipcode" placeholder="우편번호" readonly onclick="javascript:kakaoPost();">
						</div>
						<input type="text" id="address1" name="address1" placeholder="주소" readonly onclick="javascript:kakaoPost();">
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
</body>
</html>