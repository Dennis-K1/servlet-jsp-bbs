<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.bbs.properties.JspComponents" %>
<%@ page import="com.bbs.domain.AdminDashboard" %>
<%@ page import="com.bbs.command.AdminCommands" %>
<%@ page import="com.bbs.util.CommandUtil" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-03
  Time: 오후 5:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%-- 관리자 페이지 홈화면 / 대시보드--%>
<html>
<head>
    <title>대시보드</title>
</head>
<body>
<%--  탑 네브 바    --%>
<jsp:include page="<%=JspComponents.TOP_NAV.getPath()%>"/>

<%-- 센터 --%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <%--        사이드 메뉴      --%>
        <jsp:include page="<%=JspComponents.SIDE_MENU.getPath()%>"/>
        <%--         메인 콘텐츠      --%>
        <div class="col" style="background-color: #f5f5f5">
            <div class="fs-3 fw-bold mt-4 text-secondary">
                통계
            </div>
            <div class="card bg-white p-4 mt-3">
                <div class="row row-cols-1 row-cols-md-2 g-4">
                    <div class="col">
                        <div class="card border-primary">
                            <div class="card-header bg-primary text-center">
                                <medium class="text-light">게시판별 게시글 등록 비율</medium>
                            </div>
                            <div class="card-body">
                                <canvas id="articlesRatioChart"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="row row-cols-1 row-cols-md-1 g-4">
                            <div class="col">
                                <div class="card border-primary">
                                    <div class="card-header bg-primary text-center">
                                        <medium class="text-light">총계</medium>
                                    </div>
                                    <div class="card-body">
                                        <p>총 활성 유저 수 : <span
                                                class="text-primary fw-bold">${adminDashboard.numberOfActiveUsers}</span>
                                        </p>
                                        <p>미답변 문의수 : <span
                                                class="text-primary fw-bold">${adminDashboard.numberOfPendingInquiries}</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="card border-primary">
                                    <div class="card-header bg-primary text-center">
                                        <medium class="text-light">조회수 TOP5</medium>
                                    </div>
                                    <div class="card-body">
                                        <div class="d-flex fw-bold text-primary text-center">
                                            <span style="width: 55%" class="text-overflow">제목</span>
                                            <span style="width: 15%">조회수</span>
                                            <span style="width: 30%"
                                                  class="text-overflow">게시판명</span>
                                        </div>
                                        <c:forEach items="${adminDashboard.top5ViewsArticles}"
                                                   var="article">
                                            <div class="d-flex text-center">
                                                <span style="width: 55%" class="text-overflow">
                                                    <c:set scope="request" var="boardId" value="${article.boardId}"></c:set>
                                                    <a href="<%=CommandUtil.getPathByBoardId((Long)request.getAttribute("boardId"))%>/detail?articleId=${article.id}">
                                                            ${article.title}
                                                    </a>
                                                </span>
                                                <span style="width: 15%; font-size: 14px" class="pt-1">${article.views}</span>
                                                <span style="width: 30%; font-size: 12px" class="pt-1"
                                                      class="text-overflow">${article.boardName}</span>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="fs-3 fw-bold mt-4 text-secondary">
                최신 글 목록
            </div>
            <div class="card bg-white p-4 mt-3">
                <div class="row row-cols-4 row-cols-md-2 align-items-stretch d-flex g-4">
                    <c:forEach begin="1" end="4" varStatus="loop">
                        <div class="col">
                            <div class="card border-primary h-100">
                                <div class="card-header bg-primary text-center">
                                    <c:choose>
                                        <c:when test="${loop.index == 1}">
                                            <medium class="text-light">공지사항</medium>
                                        </c:when>
                                        <c:when test="${loop.index == 2}">
                                            <medium class="text-light">자유게시판</medium>
                                        </c:when>
                                        <c:when test="${loop.index == 3}">
                                            <medium class="text-light">1:1문의</medium>
                                        </c:when>
                                        <c:when test="${loop.index == 4}">
                                            <medium class="text-light">갤러리</medium>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <div class="card-body">
                                    <div class="d-flex fw-bold text-primary">
                                        <span style="width: 60%" class="text-overflow">제목</span>
                                        <span style="width: 40%" class="text-overflow">등록일</span>
                                    </div>
                                    <c:forEach
                                            items="${adminDashboard.top3RecentArticlesByEachBoard}"
                                            var="article">
                                        <c:if test="${article.boardId == loop.index}">
                                            <div class="d-flex">
                                                <span style="width: 60%"
                                                      class="text-overflow">
                                                    <c:set scope="request" var="boardId" value="${article.boardId}"></c:set>
                                                    <a href="<%=CommandUtil.getPathByBoardId((Long)request.getAttribute("boardId"))%>/detail?articleId=${article.id}">
                                                        ${article.title}
                                                    </a>
                                                </span>
                                                <span style="width: 40%; font-size: 12px"
                                                      class="text-overflow pt-1">${article.dateRegistered}</span>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.0/dist/chart.min.js"></script>
<script>
  const canvas = document.getElementById('articlesRatioChart');

  const data = {
    labels: ['공지사항', '자유게시판', '1:1문의', '갤러리'],
    datasets: [{
      label: 'Article Counts by Category',
      data: [${adminDashboard.numberOfActiveNoticeArticles},
        ${adminDashboard.numberOfActiveCommunityArticles},
        ${adminDashboard.numberOfActiveInquiryArticles},
        ${adminDashboard.numberOfActiveGalleryArticles}],
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 206, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)'
      ],
      borderColor: [
        'rgba(255, 99, 132, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)'
      ],
      borderWidth: 1
    }]
  };
  const articlesRatioChart = new Chart(canvas, {
    type: 'pie',
    data: data,
    options: {
      plugins: {
        legend: {
          position: 'bottom'
        }
      }
    }
  });
</script>
<style>
  .text-overflow {
    display: inline-block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
</style>
</html>
