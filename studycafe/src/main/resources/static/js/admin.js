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

const DEFAULT_CHART_OPTIONS = {
  responsive: true,
};

const DEFAULT_TABLE_OPTIONS = {
  minHeight: 300,
  layout: "fitColumns",
  responsiveLayout: "hide",
  history: true,
  pagination: "local",
  paginationSize: 10,
  paginationCounter: "rows",
  movableColumns: true,
};

// function transparentize(color, opacity) {
//   var alpha = opacity === undefined ? 0.5 : 1 - opacity;
//   return Color(color).alpha(alpha).rgbString();
// }

function createChart(el, type, data, options) {
  return new Chart(el, {
    type,
    data,
    options,
  });
}

function createHTTPRequest(url) {
  let httpRequest = new XMLHttpRequest();
  let result;
  if (!httpRequest) {
    console.error("ajax 요청을 만드는데 실패하였습니다.");
    return false;
  }

  httpRequest.onreadystatechange = function () {
    try {
      if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (this.readyState == 4 && httpRequest.status === 200) {
          result = httpRequest.responseText;
        } else {
          alert("httpRequest 요청에 문제가 있습니다.");
        }
      }
    } catch (e) {
      alert(`Caught Exception: ${e.description}`);
    }
  };

  httpRequest.open("GET", url, false);
  httpRequest.send();
  return result;
}

function renderDashboard() {
  // 대쉬보드 차트 설정
  const chrt1 = document.getElementById("home-donught-1").getContext("2d");

  const dashboard_data = createHTTPRequest("/admin/api/dashboard");

  const chrt1_data = {
    labels: ["팀1", "팀2", "팀3", "팀4", "팀5", "팀6"],
    datasets: [
      {
        label: "팀 점수 순위",
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

  const chrt2 = document.getElementById("home-donught-2").getContext("2d");
  const chrt2_data = {
    labels: ["팀1", "팀2", "팀3", "팀4", "팀5", "팀6"],
    datasets: [
      {
        label: "인증 횟수 순위",
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

  const chrt3 = document.getElementById("home-donught-3").getContext("2d");
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

  const chrt4 = document.getElementById("home-donught-4").getContext("2d");
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

  createChart(chrt1, "doughnut", chrt1_data, DEFAULT_CHART_OPTIONS);
  createChart(chrt2, "doughnut", chrt2_data, DEFAULT_CHART_OPTIONS);
  createChart(chrt3, "pie", chrt3_data, DEFAULT_CHART_OPTIONS);
  createChart(chrt4, "pie", chrt4_data, DEFAULT_CHART_OPTIONS);

  const line1 = document.getElementById("home-line-1").getContext("2d");
  const line2 = document.getElementById("home-line-2").getContext("2d");

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
        borderColor: CHART_COLORS.red,
        // backgroundColor: rgba(75, 192, 192, 0.5),
        yAxisID: "y",
      },
      {
        label: "예약 완료",
        data: [20, 50, 60, 22, 66, 40, 59],
        fill: false,
        borderColor: CHART_COLORS.yellow,
        // backgroundColor: rgba(153, 102, 255, 0.5),
        yAxisID: "y",
      },
    ],
  };

  createChart(line1, "line", line1_data, line_options("팀 등록 현황"));
  createChart(line2, "line", line2_data, line_options("예약 현황"));
}

function renderTeam() {
  // 팀 차트 설정

  const chrt1 = document.getElementById("team-donught-1").getContext("2d");
  const chrt1_data = {
    labels: ["팀1", "팀2", "팀3", "팀4", "팀5", "팀6"],
    datasets: [
      {
        label: "팀 점수 랭킹",
        data: [20, 40, 13, 32, 45, 22],
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

  const chrt2 = document.getElementById("team-donught-2").getContext("2d");
  const chrt2_data = {
    labels: ["팀1", "팀2", "팀3", "팀4", "팀5", "팀6"],
    datasets: [
      {
        label: "인증 횟수 랭킹",
        data: [25, 44, 15, 23, 70, 32],
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

  const chrt3 = document.getElementById("team-donught-3").getContext("2d");
  const chrt3_data = {
    labels: ["새로운 팀", "기존 팀"],
    datasets: [
      {
        label: "팀",
        data: [23, 47],
        backgroundColor: ["DarkMagenta", "#F8F8FF"],
        hoverOffset: 5,
      },
    ],
  };

  createChart(chrt1, "doughnut", chrt1_data, DEFAULT_CHART_OPTIONS);
  createChart(chrt2, "doughnut", chrt2_data, DEFAULT_CHART_OPTIONS);
  createChart(chrt3, "pie", chrt3_data, DEFAULT_CHART_OPTIONS);

  const t1 = document.getElementById("team-table-1");
  const t2 = document.getElementById("team-table-2");

  const table_data_1 = [
    {
      id: 1,
      name: "팀1",
      head: "12",
      point: 12,
      count: 1,
      submit_date: "07/30/2019",
      approve_date: "07/30/2019",
    },
    {
      id: 2,
      name: "팀2",
      head: "1",
      point: 3,
      count: 1,
      submit_date: "07/30/2019",
      approve_date: "07/30/2019",
    },
    {
      id: 3,
      name: "팀3",
      head: "42",
      point: 0,
      count: 0,
      submit_date: "07/30/2019",
      approve_date: "07/30/2019",
    },
    {
      id: 4,
      name: "팀4",
      head: "125",
      point: 4,
      count: 1,
      submit_date: "07/30/2019",
      approve_date: "07/30/2019",
    },
    {
      id: 5,
      name: "팀5",
      head: "16",
      point: 21,
      count: 5,
      submit_date: "07/30/2019",
      approve_date: "07/30/2019",
    },
    {
      id: 6,
      name: "팀6",
      head: "12",
      point: 43,
      count: 10,
      submit_date: "07/30/2019",
      approve_date: "07/30/2019",
    },
    {
      id: 7,
      name: "팀7",
      head: "1",
      point: 66,
      count: 22,
      submit_date: "07/30/2019",
      approve_date: "07/30/2019",
    },
    {
      id: 8,
      name: "팀8",
      head: "42",
      point: 10,
      count: 1,
      submit_date: "07/30/2019",
      approve_date: "07/30/2019",
    },
    {
      id: 9,
      name: "팀9",
      head: "125",
      point: 23,
      count: 4,
      submit_date: "07/30/2019",
      approve_date: "07/30/2019",
    },
    {
      id: 10,
      name: "팀10",
      head: "16",
      point: 45,
      count: 5,
      submit_date: "07/30/2019",
      approve_date: "07/30/2019",
    },
  ];

  const table1 = new Tabulator("#team-table-1", {
    index: "id",
    data: table_data_1,
    columns: [
      { title: "ID", field: "id" },
      { title: "이름", field: "name" },
      { title: "헤드", field: "head" },
      { title: "포인트", field: "point" },
      { title: "인증횟수", field: "count" },
      { title: "신청날짜", field: "submit_date" },
      { title: "승인날짜", field: "approve_date" },
    ],
    ...DEFAULT_TABLE_OPTIONS,
  });

  const table_data_2 = [
    { name: "팀1", count: 4, type: "IT", submit_date: "07/30/2019" },
    { name: "팀2", count: 3, type: "공무원", submit_date: "07/30/2019" },
    { name: "팀3", count: 5, type: "경찰", submit_date: "07/30/2019" },
    { name: "팀4", count: 4, type: "IT", submit_date: "07/30/2019" },
    { name: "팀5", count: 5, type: "IT", submit_date: "07/30/2019" },
    { name: "팀6", count: 8, type: "토익", submit_date: "07/30/2019" },
    { name: "팀7", count: 3, type: "JIT", submit_date: "07/30/2019" },
    { name: "팀8", count: 5, type: "변호사", submit_date: "07/30/2019" },
    { name: "팀9", count: 5, type: "농수산업", submit_date: "07/30/2019" },
    { name: "팀11", count: 4, type: "마케팅", submit_date: "07/30/2019" },
    { name: "팀12", count: 4, type: "공무원", submit_date: "07/30/2019" },
    { name: "팀13", count: 4, type: "IT", submit_date: "07/30/2019" },
    { name: "팀14", count: 4, type: "제조업", submit_date: "07/30/2019" },
    { name: "팀15", count: 4, type: "웹디자인", submit_date: "07/30/2019" },
    { name: "팀16", count: 4, type: "웹개발", submit_date: "07/30/2019" },
    { name: "팀17", count: 4, type: "로봇개발", submit_date: "07/30/2019" },
    { name: "팀18", count: 4, type: "게임개발", submit_date: "07/30/2019" },
    { name: "팀19", count: 4, type: "간호사", submit_date: "07/30/2019" },
  ];

  const table2 = new Tabulator("#team-table-2", {
    index: "id",
    data: table_data_1,
    columns: [
      { title: "이름", field: "name" },
      { title: "팀인원", field: "count" },
      { title: "스터디분야", field: "type" },
      { title: "신청날짜", field: "submit_date" },
    ],
    ...DEFAULT_TABLE_OPTIONS,
  });
}

function renderUser() {
  // 유저 차트 설정

  const chrt1 = document.getElementById("user-donught-1").getContext("2d");
  const chrt1_data = {
    labels: ["유저1", "유저2", "유저3", "유저4", "유저5", "유저6"],
    datasets: [
      {
        label: "유저 점수 랭킹",
        data: [354, 200, 138, 205, 304, 322],
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

  const chrt2 = document.getElementById("user-donught-2").getContext("2d");
  const chrt2_data = {
    labels: ["유저1", "유저2", "유저3", "유저4", "유저5", "유저6"],
    datasets: [
      {
        label: "유저 활동 랭킹",
        data: [11, 22, 16, 54, 20, 32],
        backgroundColor: [
          "DarkViolet",
          "aqua",
          "DeepPink",
          "lightgreen",
          "Plum",
          "LavenderBlush",
        ],
        hoverOffset: 5,
      },
    ],
  };

  const chrt3 = document.getElementById("user-donught-3").getContext("2d");
  const chrt3_data = {
    labels: ["새로운 유저", "기존 유저"],
    datasets: [
      {
        label: "유저",
        data: [53, 412],
        backgroundColor: ["DarkCyan", "#F8F8FF"],
        hoverOffset: 5,
      },
    ],
  };

  createChart(chrt1, "doughnut", chrt1_data, DEFAULT_CHART_OPTIONS);
  createChart(chrt2, "doughnut", chrt2_data, DEFAULT_CHART_OPTIONS);
  createChart(chrt3, "pie", chrt3_data, DEFAULT_CHART_OPTIONS);

  const t1 = document.getElementById("team-table-1");
  const t2 = document.getElementById("team-table-2");

  const table_data_1 = [
    { id: 1, name: "유저1", email: "12", auth: true, point: 33, team_num: 1 },
    { id: 2, name: "유저2", email: "1", auth: true, point: 0, team_num: 2 },
    { id: 3, name: "유저3", email: "42", auth: false, point: 0, team_num: 3 },
    { id: 4, name: "유저4", email: "125", auth: true, point: 23, team_num: 2 },
    { id: 5, name: "유저5", email: "16", auth: false, point: 21, team_num: 1 },
    { id: 6, name: "유저6", email: "12", auth: true, point: 43, count: 4 },
    { id: 7, name: "유저7", email: "1", auth: true, point: 66, team_num: 4 },
    { id: 8, name: "유저8", email: "42", auth: false, point: 10, team_num: 4 },
    { id: 9, name: "유저9", email: "125", auth: true, point: 23, team_num: 5 },
    { id: 11, name: "유저10", email: "16", auth: true, point: 32, team_num: 5 },
    {
      id: 12,
      name: "유저11",
      email: "16",
      auth: false,
      point: 16,
      team_num: 7,
    },
    {
      id: 13,
      name: "유저12",
      email: "16",
      auth: false,
      point: 23,
      team_num: 10,
    },
    { id: 14, name: "유저13", email: "16", auth: true, point: 50, team_num: 7 },
    {
      id: 15,
      name: "유저14",
      email: "16",
      auth: false,
      point: 45,
      team_num: 6,
    },
    { id: 16, name: "유저15", email: "16", auth: true, point: 21, team_num: 6 },
    {
      id: 17,
      name: "유저16",
      email: "16",
      auth: true,
      point: 33,
      team_num: 15,
    },
    {
      id: 18,
      name: "유저17",
      email: "16",
      auth: false,
      point: 47,
      team_num: 34,
    },
  ];

  const table1 = new Tabulator("#user-table-1", {
    index: "id",
    data: table_data_1,
    columns: [
      { title: "ID", field: "id" },
      { title: "이름", field: "name" },
      { title: "이메일", field: "email" },
      { title: "인증여부", field: "auth" },
      { title: "팀번호", field: "team_num" },
      { title: "점수", field: "point" },
    ],
    ...DEFAULT_TABLE_OPTIONS,
  });
}

function renderStudy() {
  // 스터디 차트 설정

  const chrt1 = document.getElementById("study-pie-1").getContext("2d");
  const chrt1_data = {
    labels: ["스터디 신청", "스터디 진행", "스터디 종료"],
    datasets: [
      {
        label: "스터디",
        data: [53, 234, 164],
        backgroundColor: ["lightpink", "lightblue", "yellow"],
        hoverOffset: 5,
      },
    ],
  };

  const chrt2 = document.getElementById("study-pie-2").getContext("2d");
  const chrt2_data = {
    labels: ["스터디1", "스터디2", "스터디3", "스터디4", "스터디5", "스터디6"],
    datasets: [
      {
        label: "스터디 점수 랭킹",
        data: [11, 22, 16, 54, 20, 32],
        backgroundColor: [
          "DarkViolet",
          "aqua",
          "DeepPink",
          "lightgreen",
          "Plum",
          "LavenderBlush",
        ],
        hoverOffset: 5,
      },
    ],
  };

  createChart(chrt1, "doughnut", chrt1_data, DEFAULT_CHART_OPTIONS);
  createChart(chrt2, "doughnut", chrt2_data, DEFAULT_CHART_OPTIONS);

  const table_data_1 = [
    {
      id: 1,
      name: "유저1",
      title: "팀원구합니다!",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 2,
      name: "유저2",
      title: "같이 공부하실분들!",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 3,
      name: "유저3",
      title: "9급 공무원 시험 준비",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 4,
      name: "유저4",
      title: "7급 공뭔 준비 팀",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 5,
      name: "유저5",
      title: "프론트앤드 개발자 취업 준비 스터디 모집합니다",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 6,
      name: "유저6",
      title: "백앤드 개발자 취업 준비 스터디 모집합니다",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 7,
      name: "유저7",
      title: "자바개발자",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 8,
      name: "유저8",
      title: "정처기 공부카페",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 9,
      name: "유저9",
      title: "ㅇㅇㅇㅇㅇ",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 11,
      name: "유저10",
      title: "ㅇㅇㅇㅇㅇ",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 12,
      name: "유저11",
      title: "ㅇㅇㅇㅇㅇ",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 13,
      name: "유저12",
      title: "경찰공뭔시험",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 14,
      name: "유저13",
      title: "ㅇㅇㅇㅇㅇ",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 15,
      name: "유저14",
      title: "ㅇㅇㅇㅇㅇ",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 16,
      name: "유저15",
      title: "ㅋㅋㅋㅋ",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 17,
      name: "유저16",
      title: "비트코인",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
    {
      id: 18,
      name: "유저17",
      title: "변호사시험 그룹모집합니다",
      x: 33,
      y: 20,
      date: "07/30/2019",
    },
  ];

  const table1 = new Tabulator("#study-table-1", {
    index: "id",
    data: table_data_1,
    columns: [
      { title: "ID", field: "id" },
      { title: "작성자", field: "name" },
      { title: "제목", field: "title" },
      { title: "위도", field: "x" },
      { title: "경도", field: "y" },
      { title: "등록일자", field: "date" },
    ],
    ...DEFAULT_TABLE_OPTIONS,
  });
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
    case "team":
      renderTeam();
      break;
    case "user":
      renderUser();
      break;
    case "study":
      renderStudy();
      break;

    default:
      break;
  }
});
