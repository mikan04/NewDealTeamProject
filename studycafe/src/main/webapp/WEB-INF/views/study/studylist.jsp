<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>스터디 게시판</title>
	<link rel="stylesheet" type="text/css" href="/css/studylist.css">
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>
</head>
<body>
	<section class="notice">
	  <div class="page-title">
	        <div class="container">
	            <h3>스터디 모집 게시판</h3>
	        </div>
	    </div>
	
	    <!-- board seach area -->
	    <div id="board-search">
	        <div class="container">
	            <div class="search-window">
	                <form action="">
	                    <div class="search-wrap">
	                        <label for="search" class="blind">공지사항 내용 검색</label>
	                        <input id="search" type="search" name="" placeholder="검색어를 입력해주세요." value="">
	                        <button type="submit" class="btn btn-dark">검색</button>
	                    </div>
	                </form>
	            </div>
	        </div>
	    </div>
	   
	  <!-- board list area -->
	    <div id="board-list">
	        <div class="container">
	            <table class="board-table">
	                <thead>
	                <tr>
	                    <th scope="col" class="th-num">번호</th>
	                    <th scope="col" class="th-title">제목</th>
	                    <th scope="col" class="th-write">작성자</th>
	                    <th scope="col" class="th-date">등록일</th>
	                </tr>
	                </thead>
	                <tbody>
		                <tr>
		                    <td>3</td>
		                    <th>
		                      <a href="#!">[공지사항] 개인정보 처리방침 변경안내처리방침</a>
		                      <p>테스트</p>
		                    </th>
		                    <td> jeongsu</td>
		                    <td>2017.07.13</td>
		                </tr>
	                </tbody>
	            </table>
	        </div>
	    </div>
	</section>
</body>
</html>