<%@ page import="com.bbs.properties.JspComponents" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-03
  Time: 오후 5:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>어드민 인덱스</title>
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
                관리자 페이지 통계
            </div>
            <div class="card bg-white p-4 mt-3">
                통계
            </div>
        </div>
    </div>
</div>
</body>
</html>
