<%@ page import="com.bbs.properties.JspComponents" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-16
  Time: 오전 1:23
  To change this template use File | Settings | File Templates.
--%>


<%-- 각 게시판 수정 페이지 공통 관리 파일--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:choose>
        <c:when test="${article.boardId == 1}">
            <title>공지사항 수정</title>
        </c:when>
    </c:choose>
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
        <div class="col py-3" style="background-color: #f5f5f5">
            <c:choose>
                <%--  공지사항  --%>
                <c:when test="${article.boardId == 1}">
                    <jsp:include page="<%=JspComponents.ADMIN_NOTICE_EDIT_FORM.getPath()%>"/>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>

</html>