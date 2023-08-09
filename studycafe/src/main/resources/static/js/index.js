/**
 * 인덱스 페이지 js
 */

$(function() {
	$.ajax({
		type: 'POST',
		url: '/team/rankingchart',
		dataType: 'json',

		success: function(result) {

			createChart(result);

		},
		error: function(xhr, status) {
			alert("랭킹을 불러오는중 에러가 발생하였습니다. 이거 안불러와지면 큰일나는데...\n관리자에게 문의를 꼭 부탁드립니다.");
		}
	});
})

function createChart(result) {
	
	let teamArray = getTeamRankingArray(result);

	const ctx = document.getElementById('ranking-chart');

	Chart.defaults.font.size = 16;
	let rankingChart = new Chart(ctx, {
		type: 'bar',
		data: {
			labels: teamArray.teamName, // 여기에 팀 이름 들어감
			datasets: [
				{
					label: '인증횟수', // approve_count
					data: teamArray.approveCount,
					borderColor: getRandomColor(),
					backgroundColor: getRandomColor(),
				},
				{
					label: '포인트', // point
					data: teamArray.teamPoint,
					borderColor: getRandomColor(),
					backgroundColor: getRandomColor(),
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

	return rankingChart;
}

function getTeamRankingArray(result) {
	
	let teamName = [];
	let approveCount = [];
	let teamPoint = [];
	
	for (let i = 0; i < result.length; i++) {
		teamName.push(result[i].teamName);
		approveCount.push(result[i].approveCount);
		teamPoint.push(result[i].point);
	}
	
	let rankingResult = {
		"teamName" : teamName,
		"approveCount" : approveCount,
		"teamPoint" : teamPoint
	}
	
	return rankingResult;
}

// 눈요기
function getRandomColor() {
	return "#" + Math.floor(Math.random() * 16777215).toString(16);
}
