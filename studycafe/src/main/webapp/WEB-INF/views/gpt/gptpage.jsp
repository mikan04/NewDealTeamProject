<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>무엇이든 물어보세요!</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<!-- jquery cdn 항상 최신버전 유지 찾음 -->
<link rel="stylesheet" type="text/css" href="${contextPath }/css/gptpage.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>

	<div class="gpt-wrap">
		<div class="gpt-title" align="center">
			<h1>Chat with ChatGPT</h1>
		</div>
		<div id="conversation"></div>
		
		<form id="userInputForm">
			<textarea cols="30" rows="3" id="userInput"></textarea>
			<button type="submit" id="btn-send">Send</button>
		</form>
		
	</div>
	<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>

	<script src="${contextPath }/js/gptpage.js"></script>
</body>
</html>