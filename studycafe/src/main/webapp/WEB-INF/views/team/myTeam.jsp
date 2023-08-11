<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!-- 로그인 한 회원 정보 사용 -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.memberEntity" var="member" />
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${team.teamName}</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="/css/myteam.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<div class="main-wrap">
		<div class="team-name-div">
			<h1>${team.teamName}</h1>
			<input type="hidden" id="teamNumber" value="${team.teamNumber }">
		</div>
		<div class="team-member-div">
			<div class="member-info">
				<div class="team-name">
					<b>아이디</b>
				</div>
				<div>
					<b>이름</b>
				</div>
				<div class="team-name">
					<b>닉네임</b>
				</div>
				<c:if test="${team.teamHead eq member.username }">
					<div>
						<b>관리</b>
					</div>
				</c:if>
			</div>

			<c:forEach items="${teamMember}" var="teamMember">

				<div class="member-info">
					<div class="team-name" title="${teamMember.username}">${teamMember.username}</div>
					<div>${teamMember.name}</div>
					<div class="team-name" title="${teamMember.nickName}">${teamMember.nickName}</div>

					<c:if test="${team.teamHead eq member.username}">
						<div>
							<button onclick="getOutTeam('${teamMember.username}');">내보내기</button>
						</div>
					</c:if>
				</div>
			</c:forEach>

			<div class="member-info">
				<c:if test="${team.teamHead eq member.username}">
					<div>
						<button type="button" data-toggle="modal" data-target="#teamDeleteModal">팀 삭제</button>
					</div>
				</c:if>
			</div>

			<div class="modal" id="teamDeleteModal" tabindex="-1" role="dialog" aria-labelledby="teamDeleteModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">정말 삭제 하시겠습니까?</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p>팀을 삭제하면 되돌릴 수 없습니다.</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-danger" onclick="deleteTeam(${team.teamNumber});">삭제</button>
							<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>

			<div class="team-info">
				<div>
					<label>
						<b>현재 포인트</b>
					</label>
					<span>${team.point } 점</span>
				</div>
				<div>
					<label>
						<b>인증 횟수</b>
					</label>
					<span>${team.approveCount } 회</span>
				</div>
			</div>
		</div>
	</div>
	<input id="teamHead" type="hidden" value="${team.teamHead }">
	<!-- <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script> -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="/js/myTeam.js"></script>
</body>
</html>