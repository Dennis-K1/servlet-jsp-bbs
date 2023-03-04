<%@ page import="com.bbs.properties.JspComponents" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-03
  Time: 오후 5:14
  To change this template use File | Settings | File Templates.
--%>

<%-- 인덱스 페이지 레이아웃 백업--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>레이아웃</title>

  <script type="text/javascript"
          src="<%=request.getContextPath()%>/templates/javascript/page-util.js"></script>
  <link rel="stylesheet" type="text/css"
        href="<%=request.getContextPath()%>/templates/css/table.css">
  <!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
        crossorigin="anonymous">
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
          crossorigin="anonymous"></script>

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
    <div class="col py-3" style="background-color: #f5f5f5">
      Content area...
    </div>
  </div>
</div>
</body>
</html>