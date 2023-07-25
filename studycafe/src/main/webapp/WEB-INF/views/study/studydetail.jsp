<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/studydetail.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>
	
	<div class="main-wrap">
	    <table class="">
	        <caption>상세보기</caption>
	        <colgroup>
	            <col width="15%">
	            <col width="35%">
	            <col width="15%">
	            <col width="*">
	        </colgroup>
	         
	        <tbody>
	            <tr>
	                <th>제목</th>
	                <td>${studyEntity.studyTitle}</td>
	                <th>조회수</th>
	                <td>0</td>
	            </tr>
	            <tr>
	                <th>작성자</th>
	                <td>${studyEntity.studyWriter}</td>
	                <th>작성시간</th>
	                <td>${studyEntity.dateTime}</td>
	            </tr>
	            <tr>
	                <th>내용</th>
	                <td colspan="3">${studyEntity.studyContent}</td>
	            </tr>
	        </tbody>
	    </table>
   
		<div id="map" style="width:100%; height:500px;"></div>
		<input type="hidden" id="lat" value="${studyEntity.latitude}">
		<input type="hidden" id="lng" value="${studyEntity.longitude}">
	</div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=59e92bd7943f48f31ff73b322a4e5603&libraries=services,clusterer,drawing"></script>
	<script src="/js/studydetail.js"></script>
	<jsp:include page="/WEB-INF/views/pageingredient/footer.jsp"></jsp:include>
</body>
</html>