<%@ page import="com.bbs.properties.JspComponents" %>
<%@ page import="com.bbs.command.AdminCommands" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-25
  Time: 오후 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--  탑 네브 바    --%>
<jsp:include page="<%=JspComponents.TOP_NAV.getPath()%>"></jsp:include>

<%-- 센터 --%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <%--        사이드 메뉴      --%>
        <jsp:include page="<%=JspComponents.SIDE_MENU.getPath()%>"></jsp:include>
        <%--         메인 콘텐츠      --%>
        <div class="col" style="background-color: #f5f5f5">
            <div class="fs-3 fw-bold mt-4 text-secondary">
                회원 정보
            </div>
            <div class="card bg-white p-4 mt-3">
                <table class="mt-3 table table-borderless">
                    <tr>
                        <th class="w-25 font-weight-bold text-primary">번호</th>
                        <td>${user.id}</td>
                    </tr>
                    <tr>
                        <th class="w-25 font-weight-bold text-primary">아이디</th>
                        <td>${user.account}</td>
                    </tr>
                    <tr>
                        <th class="w-25 font-weight-bold text-primary">게시글 수</th>
                        <td>${user.countArticle}</td>
                    </tr>
                    <tr>
                        <th class="w-25 font-weight-bold text-primary">방문 횟수</th>
                        <td>${user.countVisit}</td>
                    </tr>
                    <tr>
                        <th class="w-25 font-weight-bold text-primary">마지막 접속일</th>
                        <td>${user.dateLastLogin}</td>
                    </tr>
                    <tr>
                        <th class="w-25 font-weight-bold text-primary">등록일</th>
                        <td>${user.dateRegistered}</td>
                    </tr>
                    <tr>
                        <th class="w-25 font-weight-bold text-primary">삭제 여부</th>
                        <td>${user.userDeleted}</td>
                    </tr>
                    <tr>
                        <th class="w-25 font-weight-bold text-primary">삭제일</th>
                        <td>${user.dateDeleted}</td>
                    </tr>
                </table>
            </div>

            <div class="fs-3 fw-bold mt-4 text-secondary">
                작성 게시글 정보
            </div>
            <div class="card bg-white p-4 mt-3">
                <table class="mt-3 table text-center table-borderless">
                    <c:choose>
                        <c:when test="${fn:length(articleList) == 0}">
                            <tr>
                                <td>등록한 게시글 없음</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                                <tr class="font-weight-bold text-primary">
                                    <th scope="col">번호</th>
                                    <th scope="col">제목</th>
                                    <th scope="col">조회수</th>
                                    <th scope="col">등록일</th>
                                </tr>
                                <c:forEach items="${articleList}" var="article">
                                    <tr scope="row">
                                        <td>${article.id}</td>
                                        <c:choose>
                                            <c:when test="${article.boardId == 2}">
                                                <td><a href="<%=AdminCommands.COMMUNITY_DETAIL.getPath()%>?articleId=${article.id}">${article.title}</a></td>
                                            </c:when>
                                            <c:when test="${article.boardId == 3}">
                                                <td><a href="<%=AdminCommands.INQUIRY_DETAIL.getPath()%>?articleId=${article.id}">${article.title}</a></td>
                                            </c:when>
                                        </c:choose>
                                        <td>${article.views}</td>
                                        <td>${article.dateRegistered }</td>
                                    </tr>
                                </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>

        </div>
    </div>
</div>
</html>
