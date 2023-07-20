<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>스터디 게시판</title>
	<link rel="stylesheet" type="text/css" href="/css/studylist.css">
	<jsp:include page="/WEB-INF/views/pageingredient/header.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <style>
        body {
            text-align: center;
        }
        #pagination {
            margin-top: 20px;
        }
        .pagination-link {
            display: inline-block;
            padding: 5px 10px;
            border: 1px solid #ddd;
            margin-right: 5px;
            cursor: pointer;
        }
        .current-page {
            background-color: #007bff;
            color: #fff;
        }
    </style>
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
	                <form>
	                    <div class="search-wrap">
	                        <label for="search" class="blind">스터디 게시판 제목 검색</label>
	                        <input id="search" type="search" name="search" placeholder="검색어를 입력해주세요." value="">
	                        <button type="button" id="searchBtn" class="btn btn-dark">검색</button>
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
	                <tbody id="postList">
	                
	                </tbody>
	            </table>
	            
			    <div id="pagination">
			
			    </div>
	        </div>
	    </div>
	</section>
	<script>
	
		function loadPosts(page) {
			var paramData = {
					"keyword": $('#search').val(),
					"page": page
				};
			$.ajax({
		
				url: "${pageContext.request.contextPath}/studyAjax"
				, contentType : "charset=UTF-8" 
				, type : 'GET'
				, data : paramData 
				, success: function(data){
	                var postList = $('#postList');
	                postList.empty();
	                $.each(data.content, function (index, post) {
	                    postList.append(
	                    		'<tr>' +
	                    		'<td>' + post.studyNum + '</td>' +
	                    		'<th>' +
	                    		'<a href="#!">' + post.studyTitle + '</a>' +
	                    		'</th>' +
	                    		'<td>' + post.studyWriter + '</td>' +
	                    		' <td>' + post.dateTime + '</td>' +
	                    		'</tr>'
	                    	);
	                });
	                currentPage = data.number;
	                renderPagination(data.totalPages);
				}
				, error: function(error){
					console.log("에러 : " + error);
				}
			});
		}
		
        function renderPagination(totalPages) {
            var pagination = $('#pagination');
            pagination.empty();
            for (var i = 0; i < totalPages; i++) {
                var pageNum = i + 1;
                if (i === currentPage) {
                    pagination.append('<span class="pagination-link current-page">' + pageNum + '</span>');
                } else {
                    pagination.append('<span class="pagination-link" data-page="' + i + '">' + pageNum + '</span>');
                }
            }
        }
		
        $(document).ready(function () {
            loadPosts(0);

            $('#searchBtn').on('click', function () {
                loadPosts(0); // 검색어가 바뀌면 첫 페이지부터 검색 결과를 보여줍니다.
            });

            $(document).on('click', '.pagination-link', function () {
                loadPosts($(this).data('page'));
            });
        });
	</script>
</body>
</html>