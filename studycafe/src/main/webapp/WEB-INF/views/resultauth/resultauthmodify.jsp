<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>인증 등록</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/36.0.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>

<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
rel="stylesheet"
integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
crossorigin="anonymous"
/>
<link rel="stylesheet" type="text/css" href="/css/studyregistration.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>

	<div class="main-wrap">
		<div class="index-ingredient">
			<form action="/auth/modifypro" name="modify_form" method="post" enctype="multipart/form-data">
				<p>
					<label for="resultAuthTitle">제목</label>
					<input type="text" id="resultAuthTitle" name="resultAuthTitle" value="${resultAuthEntity.resultAuthTitle}" placeholder="제목 입력">
				</p>

				<p>
					<label for="resultAuthWriter">작성자</label>
					<input type="text" id="resultAuthWriter" name="resultAuthWriter" value="${resultAuthEntity.resultAuthWriter}" readonly="readonly">
				</p>

				<div>
					<label for="resultAuthContent">내용</label>
					<textarea id="resultAuthContent" name="resultAuthContent" style="outline: none;">${resultAuthEntity.resultAuthContent}</textarea>
				</div>
				
				<!-- 이미지 첨부시 미리보기 -->
				<p>
					<label for="file">첨부파일</label>
					<input type="file" id="file" name="file" placeholder="파일등록">
					
				</p>
				
				<!-- 이미지 임시출력 -->
				<div class="select_img">
					<img src="" />
				</div>
				<!-- //이미지 첨부시 미리보기 -->
	
				
				<!-- hidden 데이터 -->
				<c:choose><c:when test="${not empty resultAuthEntity.commentDate}">
					<input type="hidden" id="commentDate" name="commentDate" value="${resultAuthEntity.commentDate}">
				</c:when></c:choose>
				<input type="hidden" id="resultAuthScore" name="resultAuthScore" value="${resultAuthEntity.resultAuthScore}">
				<input type="hidden" id="resultAuthComment" name="resultAuthComment" value="${resultAuthEntity.resultAuthComment}">
				<input type="hidden" id="resultAuthNum" name="resultAuthNum" value="${resultAuthEntity.resultAuthNum}">
				<button class="btn btn-dark" id="registerBtn" type="button" onclick="regis_check();">수정</button>
			</form>
		</div>
	</div>

	<script src="/js/authmodify.js"></script>
</body>
</html>