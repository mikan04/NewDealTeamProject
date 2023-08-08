<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- 로그인 한 회원 정보 사용 -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.memberEntity" var="member" />
</sec:authorize>

<fmt:formatDate  var="dateTime" value="${resultAuthEntity.createDate}" type="DATE" pattern="yyyy-MM-dd HH:mm"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${resultAuthEntity.resultAuthTitle}</title>
<link rel="stylesheet" type="text/css" href="/css/authdetail.css">
<!-- 폰트어썸 아이콘 https://fontawesome.com/ -->
<script src="https://kit.fontawesome.com/b780cabc8c.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/36.0.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>
	
	<div class="main-wrap">
		<div class="index-ingredient">
			<form class="main-form" method="post" enctype="multipart/form-data">
				<input type="hidden" id="resultAuthNum" name="resultAuthNum" value="${resultAuthEntity.resultAuthNum}">
				<p>
					<label for="resultAuthTitle">제목</label>
					<input type="text" id="resultAuthTitle" name="resultAuthTitle" value="${resultAuthEntity.resultAuthTitle}" readonly="readonly"></p>
				<p>
					<label for="resultAuthWriter">작성자</label>
					<input type="text" id="resultAuthWriter" name="resultAuthWriter" value="${resultAuthEntity.resultAuthWriter}" readonly="readonly"></p>
				<p>
					<label for="dateTime">작성날짜</label>
					<input type="text" id="dateTime" name="dateTime" value="${dateTime}" readonly="readonly"></p>
				<div>
					<label for="resultAuthContent">내용</label>
					<textarea id="resultAuthContent" name="resultAuthContent" readonly="readonly">${resultAuthEntity.resultAuthContent}</textarea></div>
				<div class="buttons">
					<button class="btn btn-dark" id="listBtn" type="button" onclick="location.href='/auth'">목록</button>
	
					<c:set var="nickName" value="${member.nickName}" />
					<c:set var="writer" value="${resultAuthEntity.resultAuthWriter}" />
					
					<c:choose>
						<c:when test="${nickName eq writer}">
							<button class="btn btn-dark" id="modifyBtn" type="button" onclick="location.href='/auth/modify/${resultAuthEntity.resultAuthNum}'">수정</button>
							<button class="btn btn-dark" id="deleteBtn" type="button" onclick="resultAuthDelete()">삭제</button>
						</c:when>
					</c:choose>
				</div>
			</form>
			<div class="content-wrap">
				<p><label>댓글</label></p>
				<div>
					<ul class="reply-list" id="reply-list"></ul>
					<c:choose>
						<c:when test="${member.role eq 'ROLE_ADMIN' and resultAuthEntity.resultAuthScore eq 0}">
						<div class="flex items-center">점수</div>
						<div class="star-rating mx-auto">
						  <input type="radio" id="5-stars" name="rating" value="5" v-model="ratings"/>
						  <label for="5-stars" class="star">★</label>
						  <input type="radio" id="4-stars" name="rating" value="4" v-model="ratings"/>
						  <label for="4-stars" class="star">★</label>
						  <input type="radio" id="3-stars" name="rating" value="3" v-model="ratings"/>
						  <label for="3-stars" class="star">★</label>
						  <input type="radio" id="2-stars" name="rating" value="2" v-model="ratings"/>
						  <label for="2-stars" class="star">★</label>
						  <input type="radio" id="1-star" name="rating" value="1" v-model="ratings" />
						  <label for="1-star" class="star">★</label>
						</div>
						<div>
							<textarea class="comment" id="comment" rows="5" placeholder="코멘트 달기"></textarea>
							<button class="btn btn-dark" id="commentBtn" type="button" onclick="resultAuthScore();">작성</button>
						</div>
						</c:when>
					</c:choose>				
</div>
			</div>
		</div>
	</div>
 	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=59e92bd7943f48f31ff73b322a4e5603&libraries=services,clusterer,drawing"></script>
	<script src="/js/authdetail.js"></script>
	<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>
	<script>
		$(document).ready(function () {
			resultAuthScoreList();
		});
	</script>
</body>
</html>