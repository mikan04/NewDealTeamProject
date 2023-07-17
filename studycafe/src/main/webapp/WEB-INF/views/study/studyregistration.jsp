<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>키워드로 장소검색하고 목록으로 표출하기</title>
<link rel="stylesheet" type="text/css" href="/css/studyregistration.css">
</head>
<body>
	<div class="map_wrap" align="center">
		<div id="map"
			style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>

		<div id="menu_wrap" class="bg_white">
			<div class="option">
				<div>
					<form onsubmit="searchPlaces(); return false;">
						<input type="text" value="강남" id="keyword" size="15"
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

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=387f3bec7e47fcbd6795a87cb3c0cac2&libraries=services"></script>

	<script src="/js/studyregistration.js"></script>
</body>
</html>