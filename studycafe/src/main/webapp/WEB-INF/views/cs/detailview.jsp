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
<title>${csPost.csTitle }</title>
<link rel="stylesheet" type="text/css" href="/css/detailview.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/36.0.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>

	<div class="main-wrap">
		<div class="index-ingredient">
			<form method="post" enctype="multipart/form-data">
				<p>
					<label for="team-title">제목</label>
					<input type="text" id="team-title" name="csTitle" value="${csPost.csTitle }" readonly="readonly">
				</p>

				<p>
					<label for="team-head">작성자(팀장)</label>
					<input type="text" id="team-head" name="csWriter" value="${csPost.csWriter }" readonly="readonly">
				</p>

				<p>
					<label for="team-head">작성날짜</label>
					<input type="text" id="team-head" name="createDate" value=${csPost.createDate } readonly="readonly">
				</p>

				<div>
					<label for="content">내용</label>
					<textarea id="content" name="csContent" readonly="readonly">
						${csPost.csContent }
					</textarea>
				</div>
				
				<p>
					<label for="file">첨부파일</label>
					
					<c:choose>
						<c:when test="${csPost.fileKey eq 'none.png' }">
							<span>첨부파일 없음</span>
						</c:when>
						<c:otherwise>
							<img src="${csPost.filePath }"/>
						</c:otherwise>
					</c:choose>
				</p>

				<c:if test="${member.nickName.equals(csPost.csWriter) }">
					<p class="modify-delete-box">
						<a type="submit" id="modify-btn" href="${contextPath }/cs/modifyview/${csPost.idx}">수정</a>
						<a type="submit" id="delete-btn" href="javascript:removePost('${csPost.idx}','cs')">삭제</a>
					</p>
				</c:if>
			</form>

		</div>
	</div>

	<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>
	<script src="/js/detailview.js"></script>
</body>
</html>