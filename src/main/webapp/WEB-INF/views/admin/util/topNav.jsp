<%@ page import="com.bbs.command.AdminCommands" %>
<%@ page import="com.bbs.properties.SessionKeys" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-25
  Time: 오후 8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


<nav class="navbar navbar-dark" style="background-color: #0c3d9f">
  <div class="container-fluid">
    <a href="<%=AdminCommands.INDEX.getPath()%>"><span class="navbar-brand mb-0 h1">HOME</span></a>
    <div class="d-flex justify-content-end" id="profile">
            <span>
                <%=session.getAttribute(SessionKeys.LOGIN_ADMIN)%>
            </span>
      <a href="<%=AdminCommands.LOGOUT.getPath()%>"><span class="nav-item-text">로그아웃</span></a>
    </div>
  </div>
</nav>