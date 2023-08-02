<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:parseDate var="date" value="${detail.qnaDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
<fmt:formatDate  var="dateTime" value="${date}" type="DATE" pattern="yyyy-MM-dd HH:mm"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/qnadetail.css">
<!-- 폰트어썸 아이콘 https://fontawesome.com/ -->
<script src="https://kit.fontawesome.com/b780cabc8c.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/36.0.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
</head>
<script>
$(document).ready(function() {
    alert($("#qnaNum").val());
});
</script>
<body>
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>
	
	<div class="main-wrap">
		<div class="index-ingredient">
			<form class="main-form" method="post" enctype="multipart/form-data">
			<input type="hidden" id="qnaNum" value="${datail.qnaNum}">
				<p>
					<label for="studyTitle">제목</label>
					<input type="text" id="studyTitle" name="qnaTitle" value="${datail.qnaTitle}" readonly="readonly"></p>
				<p>
					<label for="studyWriter">작성자</label>
					<input type="text" id="studyWriter" name="qnaWriter" value="${datail.qnaWriter}" readonly="readonly"></p>
				<p>
					<label for="dateTime">작성날짜</label>
					<input type="text" id="dateTime" name="dateTime" value="${datail.qnaDate}" readonly="readonly" pattern="yyyy-MM-dd"></p>
				<div>
					<label for="studyContent">내용</label>
					<textarea class="texta" id="studyContent" name="qnaContent" readonly="readonly">${datail.qnaContent}</textarea>
				</div>
				
				<button class="btn btn-dark" id="listBtn" type="button" onclick="location.href='/qna/'">목록</button>
				<button class="btn btn-dark" id="modifyBtn" type="button" onclick="location.href='/qnaModify/${datail.qnaNum}'">수정</button>
				<button class="btn btn-dark" id="deleteBtn" type="button" onclick="studyDelete(${datail.qnaNum})">삭제</button>
			</form>
			<div class="content-wrap">
				<p><label>댓글</label></p>
				<div>
					<ul class="reply-list">
						<!-- <li>
							<div class="thumb">
								<img src="/img/user.png" width="48" height="48" class="">
							</div>
							<div class="reply-content">
								<ul class="info">
									<li class="nickname">jeongsu</li>
									<li class="date">2023.07.21</li>
								</ul>
									<p class="text">같이 공부해도 될까요??</p>
									<ul class="control">
										<li class=""><a href="#" class="link_reply"><i class="fa fa-comment"></i>답변달기</a>
										<li class=""><a href="#" class="link_reply"><i class="fa-solid fa-pencil"></i>수정</a>
										<li class=""><a href="#" class="link_reply"><i class="fa-solid fa-trash-can"></i>삭제</a>
								</ul>
							</div>
						</li>
						<li>
							<div class="thumb_re">
								<img src="/img/user_writer.png" width="48" height="48" class="">
							</div>
							<div class="reply-content reply-re_content">
								<ul class="info">
									<li class="nickname">jeongsu</li>
									<li class="date">2023.07.21</li>
								</ul>
									<p class="text">그럼요!!</p>
									<ul class="control">
										<li class=""><a href="#" class="link_reply"><i class="fa-solid fa-pencil"></i>수정</a>
										<li class=""><a href="#" class="link_reply"><i class="fa-solid fa-trash-can"></i>삭제</a>
								</ul>
							</div>
						</li> -->
					</ul>
					<form>
						<textarea class="comment" id="comment" rows="5" placeholder="코멘트 달기"></textarea>
						<button class="btn btn-dark" id="commentBtn" type="button" onclick="studyReplyInsert();">작성</button>
					</form>
				</div>
			</div>
		</div>
	</div>
 	
	<script src="/js/qnadetail.js"></script>
	<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>
</body>
</html>