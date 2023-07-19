<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀 등록 게시판</title>
<link rel="stylesheet" href="/css/teamboard.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>
	<div class="main-wrap">
		<div class="index-ingredient">
			<div class="index main-regteam">
				<button class="team-regist" onclick="location.href = '${contextPath}/team/teamregis'">팀 등록</button>
				<label>
					<a href="#">팀등록 게시판</a>
				</label>
				<div>
					<a href="#">할아버지리어카드롭킥 팀 등록 신청합니다</a>
					<span><font size="2">yyyy-mm-dd</font></span>
					<br>
					<span><font size="2">댓글갯수</font></span>
				</div>
				<div>
					<a href="#">임산부석의지배자들 팀 등록 신청해요</a>
					<span><font size="2">yyyy-mm-dd</font></span>
					<br>
					<span><font size="2">댓글갯수</font></span>
				</div>
				<div>
					<a href="#">팀등록 게시글 10개까지...</a>
					<span><font size="2">yyyy-mm-dd</font></span>
					<br>
					<span><font size="2">댓글갯수</font></span>
				</div>
				<div>
					<a href="#">여기 아래로 페이징</a>
					<span><font size="2">yyyy-mm-dd</font></span>
					<br>
					<span><font size="2">댓글갯수</font></span>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>
</body>
</html>