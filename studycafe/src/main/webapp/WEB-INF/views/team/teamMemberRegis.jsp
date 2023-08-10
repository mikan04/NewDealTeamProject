<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authentication property="principal.memberEntity" var="member" />

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀 멤버 추가</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/teamMemberRegis.css">

</head>
<body>
	<div class="main-wrap">
		<div class="index-ingredient">

			<div class="search-id">
				<label for="userName"> 아이디 검색 </label>
				<input type="text" id="userName" name="userName" placeholder="유저 검색" />
				<button type="button" id="userNameCheck" name="userNameCheck" onclick="searchMember()" disabled>검색</button>
			</div>


			<table id="user-table">
				<tbody>
					<tr>
						<th>아이디</th>
						<th>닉네임</th>
						<th>팀</th>
						<th>추가</th>
					</tr>
				</tbody>
			</table>

			<div id="userSelectDiv">
				<label for="userName">선택된 아이디</label>
				<input type="text" id="userSelect" name="userSelect" placeholder="유저 선택" readonly="readonly" />
				<button type="button" id="addUserBtn" name="addUserBtn" onclick="addUser()">팀원 추가</button>
			</div>

		</div>
	</div>
	<script src="/js/teamMemberRegis.js"></script>
</body>
</html>