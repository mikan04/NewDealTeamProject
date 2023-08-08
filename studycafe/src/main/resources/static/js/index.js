/**
 * 인덱스 페이지 js
 */

$(function() {
	$.ajax({
		type: 'POST',
		url: '/team/rankingchart',
		dataType: 'json',

		success: function(result) {

		},
		error: function(xhr, status) {
			alert('[' + status + ']\n\n' + xhr.responseText);
			hasError = true;
		}
	});

	const ctx = document.getElementById('ranking-chart');
	Chart.defaults.font.size = 16;
	new Chart(ctx, {
		type: 'bar',
		data: {
			labels: ['1팀', '2팀', '3팀', '4팀', '5팀'], // 여기에 팀 이름 들어감
			datasets: [
				{
					label: '인증횟수', // approve_count
					data: [1, 2, 3, 4, 5],
					borderColor: '#36A2EB',
					backgroundColor: '#9BD0F5',
				},
				{
					label: '포인트', // point
					data: [2, 3, 4, 5, 6],
					borderColor: '#FF6384',
					backgroundColor: '#FFB1C1',
				}
			]
		},
		options: {
			responsive: false,
			indexAxis: 'y',
			scales: {
				y: {
					beginAtZero: true
				}
			}
		}
	});
})
