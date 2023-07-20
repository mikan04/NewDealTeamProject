<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>스터디 모집 등록</title>
	<link rel="stylesheet" type="text/css" href="/css/studyregistration.css">
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>
</head>
<body>
 	<div class="main_wrap" align="center">
		<form class="studyregistrationForm" id="studyregistrationForm" method="post" enctype="multipart/form-data" action="/studyregistrationpro">
			<div>
				<label for="tudytitle">제목</label>
				<input type="text" id="studytitle" name="studyTitle" value="Java 팀원 구합니다!!"></div>
			<div>
				<textarea id="studycontent" name="studyContent" rows="4" cols="50" >팀원 얼릉 구합니다 빨리!!</textarea></div>
			<div>
				<label for="files">파일첨부</label>
				<input type="file" id="files" name="files"></div>
			<div>
				<input type="text" id="address_name" name="address_name">
				<label for="address_name">지번 주소</label>
				<span></span></div>
			<br><br>
			<input type="hidden" id="studywriter" name="studyWriter" value="jeongsu">
			<input type="hidden" id="latitude" name="latitude">
			<input type="hidden" id="longitude" name="longitude">
			<button type="submit">등록</button>
		</form>
		
		<br><br>
  		<div class="map_wrap" align="center">
			<div id="map" style="width: 500px; height: 500px; position: relative; overflow: hidden;"></div>
			<div id="menu_wrap" class="bg_white">
				<div class="option">
					<div>
						<form onsubmit="searchPlaces(); return false;">
							<input type="text" value="" id="keyword" size="15"
								placeholder="검색어를 입력해주세요.">
							<button type="submit">검색하기</button>
						</form>
					</div>
				</div>
				<hr>
				<ul id="placesList"></ul>
				<div id="pagination"></div>
			</div>
			<p>
				<em>지도를 클릭해주세요!</em>
			</p>
			<div id="clickLatlng"></div>
			<div id="clickLatlng-marker"></div>
		</div>
	</div>
	
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=59e92bd7943f48f31ff73b322a4e5603&libraries=services,clusterer,drawing"></script>

	<script src="/js/studyregistration.js"></script>
</body>
</html>