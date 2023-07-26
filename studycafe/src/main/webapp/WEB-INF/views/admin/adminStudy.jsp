<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>관리자 스터디 관리</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
      crossorigin="anonymous"
    />

    <link
      href="https://unpkg.com/tabulator-tables@5.5.0/dist/css/tabulator.min.css"
      rel="stylesheet"
    />
    <script
      type="text/javascript"
      src="https://unpkg.com/tabulator-tables@5.5.0/dist/js/tabulator.min.js"
    ></script>
    <link rel="stylesheet" type="text/css" href="/css/admin.css" />
  </head>
  <body>
    <div class="main-wrap">
      <div class="index-ingredient">
        <div class="container-fluid">
          <div class="row">
            <jsp:include
              page="/WEB-INF/views/pageingredient/adminSidebar.jsp"
            ></jsp:include>
            <div class="main-content col-xs col-md col-lg">
              <div class="container">
                <div class="row">
                  <div class="col">
                    <div class="container">
                      <div class="row">
                        <div class="col-xs-12 col-sm-6 mt-4 chart-data">
                          <canvas id="study-pie-1" aria-label="chart"></canvas>
                          <div class="pie-legend text-center">스터디 현황</div>
                        </div>
                        <div class="col-xs-12 col-sm-6 mt-4 chart-data">
                          <canvas id="study-pie-2" aria-label="chart"></canvas>
                          <div class="donught-legend text-center">
                            스터디 점수 랭킹
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row my-5">
                  <div class="col">
                    <div class="admin-table">
                      <label class="study-label-1" for="study-table-1"
                        >전체 스터디</label
                      >
                      <div id="study-table-1" aria-label="table"></div>
                    </div>
                  </div>
                </div>
                <!-- <div class="row">
                  <div class="col">
                    <div class="team-register-graph">
                      <label class="team-label-1" for="team-table-1">전체 팀</label>
                      <div id="team-table-2" aria-label="table"></div>
                    </div>
                  </div>
                </div> -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
      crossorigin="anonymous"
    ></script>
    <script src="/js/admin.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  </body>
</html>
