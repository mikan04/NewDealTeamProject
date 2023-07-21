/**
 * 
 */
function loadPosts(keyword, page) {
	var paramData = {
			"keyword": keyword,
			"page": page
		};
	$.ajax({

		url: "/studyAjax"
		, contentType : "charset=UTF-8" 
		, type : 'GET'
		, data : paramData 
		, success: function(data){
            var postList = $('#postList');
            postList.empty();
            $.each(data.content, function (index, post) {
            	var day = new Date(post.dateTime);
            	
                postList.append(
                		'<tr>' +
                		'<td>' + post.studyNum + '</td>' +
                		'<th>' +
                		'<a href="/studydetail/' + post.studyNum + '">' + post.studyTitle + '</a>' +
                		'</th>' +
                		'<td>' + post.studyWriter + '</td>' +
                		' <td>' + day.getFullYear() + '.' + (day.getMonth() + 1) + '.' + day.getDate() + '</td>' +
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