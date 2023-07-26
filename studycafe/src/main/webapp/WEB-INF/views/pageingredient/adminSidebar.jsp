<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>사이드바</title>
    <link rel="stylesheet" href="/css/admin.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
      integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  </head>
  <body>
    <header
      class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow"
    >
      <a class="navbar-brand col-md-2 me-0 px-3" href="/">스터디 카페</a>
      <button
        class="navbar-toggler d-md-none collapsed"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#sidebarMenu"
        aria-controls="sidebarMenu"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="w-100 h-0"></div>
      <div class="navbar-nav">
        <div class="nav-item text-nowrap">
          <a class="nav-link px-3" href="#">로그아웃</a>
        </div>
      </div>
    </header>
    <nav
      id="sidebarMenu"
      class="col-md-2 d-md-block bg-light sidebar collapse navbar-collapse"
    >
      <div class="position-sticky pt-3">
        <img class="d-none d-md-block avatar-img mb-2" src="/img/manager.png" />

        <div class="sidebar-heading mb-2">
          <h6 class="px-3 mt-4 mb-1 text-muted">관리자</h6>
        </div>
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" data-path="home" href="/admin/home">
              <div
                class="d-flex justify-content-center justify-content-lg-between"
              >
                <div class="mx-2">
                  <i class="fa-solid fa-house"></i>
                </div>
                <div class="d-none d-lg-block nav-link-label">
                  <span>대쉬보드</span>
                </div>
              </div>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-path="team" href="/admin/team">
              <div
                class="d-flex justify-content-center justify-content-lg-between"
              >
                <div class="mx-2">
                  <i class="fa-solid fa-people-group"></i>
                </div>
                <div class="d-none d-lg-block nav-link-label">
                  <span>팀 관리</span>
                </div>
              </div>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-path="study" href="/admin/study">
              <div
                class="d-flex justify-content-center justify-content-lg-between"
              >
                <div class="mx-2">
                  <i class="fa-solid fa-diagram-project"></i>
                </div>
                <div class="d-none d-lg-block nav-link-label">
                  <span> 스터디 관리</span>
                </div>
              </div>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-path="user" href="/admin/user">
              <div
                class="d-flex justify-content-center justify-content-lg-between"
              >
                <div class="mx-2">
                  <i class="fa-regular fa-user"></i>
                </div>
                <div class="d-none d-lg-block nav-link-label">
                  <span> 유저 관리</span>
                </div>
              </div>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-path="ranking" href="/admin/ranking">
              <div
                class="d-flex justify-content-center justify-content-lg-between"
              >
                <div class="mx-2">
                  <i class="fa-solid fa-ranking-star"></i>
                </div>
                <div class="d-none d-lg-block nav-link-label">
                  <span> 순위</span>
                </div>
              </div>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-path="chart" href="/admin/chart">
              <div
                class="d-flex justify-content-center justify-content-lg-between"
              >
                <div class="mx-2">
                  <i class="fa-solid fa-chart-pie"></i>
                </div>
                <div class="d-none d-lg-block nav-link-label">
                  <span> 차트</span>
                </div>
              </div>
            </a>
          </li>
        </ul>
      </div>
    </nav>
  </body>
</html>
