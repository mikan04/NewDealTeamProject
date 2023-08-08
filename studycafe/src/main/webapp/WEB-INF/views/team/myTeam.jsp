<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div>
			<label id="teamName">${team.teamName}</label>
			<input type="text" id="teamNumber" value="${team.teamNumber }">
	</div>
	<div>
	
		<table>
			<thead>
			</thead>
				<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>닉네임</th>
				<th>팀 내보내기</th>	
				</tr>
	<c:forEach items="${member}" var="member">
			<tbody>
				<tr>
				<td>${member.username}</td>
				<td>${member.name}</td>
				<td>${member.nickName}</td>
				<td><input type="button" value="내보내기" onclick="getOutTeam('${member.username}');"/></td>
				</tr> 
			</tbody>
	</c:forEach>
		</table>
		
	</div>
<script src="/js/myTeam.js"></script>
</body>
</html>