const MONTHS = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

const CHART_COLORS = {
  red: "rgb(255, 99, 132)",
  orange: "rgb(255, 159, 64)",
  yellow: "rgb(255, 205, 86)",
  green: "rgb(75, 192, 192)",
  blue: "rgb(54, 162, 235)",
  purple: "rgb(153, 102, 255)",
  grey: "rgb(201, 203, 207)",
};

function transparentize(color, opacity) {
  var alpha = opacity === undefined ? 0.5 : 1 - opacity;
  return Color(color).alpha(alpha).rgbString();
}

function createChart(el, type, data, options) {
  return new Chart(el, {
    type,
    data,
    options,
  });
}

function renderDashboard() {
  // 대쉬보드 차트 설정
  const chrt1 = document.getElementById("donught-1").getContext("2d");
  const chart_options = {
    responsive: true,
  };
  const chrt1_data = {
    labels: ["팀1", "팀2", "팀3", "팀4", "팀5", "팀6"],
    datasets: [
      {
        label: "팀 점수 랭킹",
        data: [20, 40, 13, 35, 20, 38],
        backgroundColor: [
          "yellow",
          "aqua",
          "pink",
          "lightgreen",
          "gold",
          "lightblue",
        ],
        hoverOffset: 5,
      },
    ],
  };

  const chrt2 = document.getElementById("donught-2").getContext("2d");
  const chrt2_data = {
    labels: ["팀1", "팀2", "팀3", "팀4", "팀5", "팀6"],
    datasets: [
      {
        label: "인증 점수 랭킹",
        data: [10, 20, 40, 23, 70, 44],
        backgroundColor: [
          "yellow",
          "aqua",
          "pink",
          "lightgreen",
          "gold",
          "lightblue",
        ],
        hoverOffset: 5,
      },
    ],
  };

  const chrt3 = document.getElementById("donught-3").getContext("2d");
  const chrt3_data = {
    labels: ["새로운 트래픽", "기존 트래픽"],
    datasets: [
      {
        label: "트래픽",
        data: [50, 100],
        backgroundColor: ["pink", "#F8F8FF"],
        hoverOffset: 5,
      },
    ],
  };

  const chrt4 = document.getElementById("donught-4").getContext("2d");
  const chrt4_data = {
    labels: ["새로운 유저", "기존 유저"],
    datasets: [
      {
        label: "유저",
        data: [22, 200],
        backgroundColor: ["lightgreen", "#F8F8FF"],
        hoverOffset: 5,
      },
    ],
  };

  createChart(chrt1, "doughnut", chrt1_data, chart_options);
  createChart(chrt2, "doughnut", chrt2_data, chart_options);
  createChart(chrt3, "pie", chrt3_data, chart_options);
  createChart(chrt4, "pie", chrt4_data, chart_options);

  const line1 = document.getElementById("line-1").getContext("2d");
  const line2 = document.getElementById("line-2").getContext("2d");

  const line_options = (text) => {
    return {
      responsive: true,
      interaction: {
        mode: "index",
        intersect: false,
      },
      plugins: {
        title: {
          display: true,
          text: text,
        },
      },
      scales: {
        y: {
          type: "linear",
          display: true,
          position: "left",
        },
        y1: {
          type: "linear",
          display: true,
          position: "right",

          grid: {
            drawOnChartArea: false,
          },
        },
      },
    };
  };

  const line_labels = MONTHS.slice(2, 10);
  const line1_data = {
    labels: line_labels,
    datasets: [
      {
        label: "등록 신청",
        data: [105, 203, 55, 68, 77, 105, 155],
        fill: false,
        borderColor: CHART_COLORS.green,
        // backgroundColor: rgba(75, 192, 192, 0.5),
        yAxisID: "y",
      },
      {
        label: "등록 완료",
        data: [42, 167, 50, 53, 55, 88, 102],
        fill: false,
        borderColor: CHART_COLORS.purple,
        // backgroundColor: rgba(153, 102, 255, 0.5),
        yAxisID: "y",
      },
    ],
  };

  const line2_data = {
    labels: line_labels,
    datasets: [
      {
        label: "예약 신청",
        data: [23, 54, 76, 25, 88, 42, 75],
        fill: false,
        borderColor: CHART_COLORS.green,
        // backgroundColor: rgba(75, 192, 192, 0.5),
        yAxisID: "y",
      },
      {
        label: "예약 완료",
        data: [20, 50, 60, 22, 66, 40, 59],
        fill: false,
        borderColor: CHART_COLORS.purple,
        // backgroundColor: rgba(153, 102, 255, 0.5),
        yAxisID: "y",
      },
    ],
  };

  createChart(line1, "line", line1_data, line_options("팀 등록 현황"));
  createChart(line2, "line", line2_data, line_options("예약 현황"));
}

// 메인 실행 함수
document.addEventListener("DOMContentLoaded", function () {
  // 사이드 바 링크 활성화 비활성화 설정
  let links = document.querySelectorAll(".nav-link");
  let path = window.location.pathname.split("/")[2];

  for (let link of links) {
    if (link.dataset.path === path) {
      link.classList.add("active");
    } else {
      link.classList.remove("active");
    }
  }

  switch (path) {
    case "home":
      renderDashboard();
      break;

    default:
      break;
  }
});
