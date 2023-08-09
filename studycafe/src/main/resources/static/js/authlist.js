/**
 * 
 */

// 게시글 리스트 불러오기
function loadPosts(keyword, page) {
	var paramData = {
			"keyword": keyword,
			"page": page
		};
		
	$.ajax({
		url: "/authlist"
		, contentType : "charset=UTF-8" 
		, type : 'GET'
		, data : paramData 
		, success: function(data){
            var postList = $('#postList');
            
            postList.empty(); // #postList 비우기
            
            // 게시글 내용이 없을 경우
            if (!data.content.length) {
				if(keyword) {
					postList.append(
						'<tr>' +
						'<td colspan="4">검색된 게시글이 없습니다.</td>' +
						'</tr>'
					);
				} else {
					postList.append(
						'<tr>' +
						'<td colspan="4">게시글이 없습니다.</td>' +
						'</tr>'
					);
				}
        	} else {
	            $.each(data.content, function (index, post) {
	            	var day = new Date(post.createDate);
	            	
	                postList.append(
                		'<tr>' +
                		'<td>' + post.resultAuthNum + '</td>' +
                		'<th>' +
                		'<a href="/auth/detail/' + post.resultAuthNum + '">' + post.resultAuthTitle + '</a>' +
                		'</th>' +
                		'<td>' + post.resultAuthWriter + '</td>' +
                		' <td>' + day.getFullYear() + '.' + (day.getMonth() + 1) + '.' + day.getDate() + '</td>' +
                		'</tr>'
	                );
	            });
            }
            currentPage = data.number;
            renderPagination(data.totalPages);
		}
		, error: function(error){
			console.log("에러 : " + error);
		}
	});
}

// 페이징 처리
function renderPagination(totalPages) {
    var pagination = $('#pagination');
    
    pagination.empty(); // #pagination 비워주기
    
    for (var i = 0; i < totalPages; i++) {
        var pageNum = i + 1;
        if (i === currentPage) {
            pagination.append('<span class="pagination-link current-page">' + pageNum + '</span>');
        } else {
            pagination.append('<span class="pagination-link" data-page="' + i + '">' + pageNum + '</span>');
        }
    }
}