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
    <title>관리자 메인 대쉬보드</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
      crossorigin="anonymous"
    />
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

            <div class="main-content col col-xs col-sm col-md col-lg">
              <div class="container">
                <div class="row">
                  <div class="col">
                    <div class="container">
                      <div class="row">
                        <div class="col-xs-12 col-sm-6 col-md-3 mt-4">
                          <canvas id="home-donught-1" aria-label="chart"></canvas>
                          <div class="donught-legend text-center">
                            팀 점수 랭킹
                          </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-3 mt-4">
                          <canvas id="home-donught-2" aria-label="chart"></canvas>
                          <div class="donught-legend text-center">
                            인증 점수 랭킹
                          </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-3 mt-4">
                          <canvas id="home-donught-3" aria-label="chart"></canvas>
                          <div class="donught-legend text-center">
                            트래픽 증가
                          </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-3 mt-4">
                          <canvas id="home-donught-4" aria-label="chart"></canvas>
                          <div class="donught-legend text-center">
                            유저 증가
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col">
                    <div class="team-register-graph">
                      <canvas id="home-line-1" aria-label="chart"></canvas>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col">
                    <div class="team-register-graph">
                      <canvas id="home-line-2" aria-label="chart"></canvas>
                    </div>
                  </div>
                </div>
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
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="/js/admin.js"></script>
  </body>
</html>
