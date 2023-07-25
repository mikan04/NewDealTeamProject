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

<!-- 제이쿼리 최신버전 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<!-- index.css 복사해다가 jsp파일이랑 이름 똑같이 바꾸고 임포트해주세요. index.css에 따로 추가하거나 바꾸지 말아주세요-->
<link rel="stylesheet" type="text/css" href="/css/admin.css">

</head>
<body>
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>

	<div class="main-wrap">
		<div class="index-ingredient">

			<!-- 여기에서부터 태그 만들어서 작업해주세요 -->

		</div>
	</div>

	<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>
</body>
</html>