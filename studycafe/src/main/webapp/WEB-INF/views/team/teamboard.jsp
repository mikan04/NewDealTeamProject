<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀 등록 게시판</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="/css/teamboard.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>

	<div class="main-wrap">
		<div class="index-ingredient">
			<div class="index main-regteam">
				<div class="team-regist-div">
					<button class="team-regist" onclick="location.href = '${contextPath}/team/teamregispage'">팀 등록</button>
				</div>
				<label>
					<a href="#">팀등록 게시판</a>
				</label>
				<c:forEach items="${teamBoardList.content }" var="list">
					<div>
						<a href="${contextPath }/teamboard/${list.teamBoardNum}">${list.teamBoardTitle }</a>
						<span>
							<font size="2"><fmt:formatDate value="${list.createDate }" pattern="yyyy-MM-dd" /></font>
						</span>
						<br>
						<span>
							<font size="2">댓글갯수</font>
						</span>
					</div>
				</c:forEach>
			</div>
		</div>
		<div>
			<!-- 페이징 -->
			<p class="pagination" align="center">

				<!-- first -->
				<c:if test="${page.first == false }">
					<span>
						<a class="page-link" href="?page=0">처음</a>
					</span>
				</c:if>

				<!-- prev -->
				<c:if test="${page.previous == true }">
					<span>
						<a class="page-link" href="?page=${page.number-page.totalPagesInEachScreen}">이전</a>
					</span>
				</c:if>

				<!-- paging -->
				<c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">
					<span class="page-item">
						<a class="page-link" href="?page=${i-1}">${i}</a>
					</span>
				</c:forEach>

				<!-- next -->
				<c:if test="${page.next == true }">
					<span>
						<a class="page-link" href="?page=${page.number + page.totalPagesInEachScreen }">다음</a>
					</span>
				</c:if>

				<!-- last -->
				<c:if test="${page.last == false }">
					<span>
						<a class="page-link" href="?page=${page.totalPages-1 }">마지막</a>
					</span>
				</c:if>

			</p>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>
	<script type="text/javascript">
		
	</script>
</body>
</html>