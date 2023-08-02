<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!-- 로그인 한 회원 정보 사용 -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.member" var="member" />
</sec:authorize>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${csPost.csTitle } 수정</title>
<link rel="stylesheet" type="text/css" href="/css/detailview.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/36.0.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>

	<div class="main-wrap">
		<div class="index-ingredient">
			<form method="post" enctype="multipart/form-data" action="/cs/modifyview/${csPost.idx }">
			
				<!-- patchmapping를 사용하기 위한 히든태그 -->
				<input type="hidden" name="_method" value="PATCH">
			
				<p>
					<label for="team-title">제목</label>
					<input type="text" id="team-title" name="csTitle" value="${csPost.csTitle }">
					<input type="hidden" name="idx" value="${csPost.idx}">
				</p>

				<p>
					<label for="team-head">작성자</label>
					<input type="text" id="team-head" name="csWriter" value="${csPost.csWriter }" readonly="readonly">
				</p>

				<p>
					<label for="team-head">작성날짜</label>
					<input type="text" id="team-head" name=createDate value=${csPost.createDate } disabled="disabled">
				</p>

				<div>
					<label for="content">내용</label>
					<textarea id="content" name="csContent">
						${csPost.csContent }
					</textarea>
				</div>
				
				<p>
					<label for="file">첨부파일</label>
					<input type="file" id="file" name="file" placeholder="파일등록">
				</p>
				
				<div class="select_img">
					<img src="${csPost.filePath }" />
				</div>

				<c:if test="${member.nickName.equals(csPost.csWriter) }">
					<p class="modify-delete-box">
						<button id="modify-btn">수정하기</button>
					</p>
				</c:if>
			</form>

		</div>
	</div>

	<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>
	<script src="/js/modifyteamboard.js"></script>
</body>
</html>