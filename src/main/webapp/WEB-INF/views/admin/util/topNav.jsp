<%@ page import="com.bbs.command.AdminCommands" %>
<%@ page import="com.bbs.properties.SessionKeys" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-25
  Time: 오후 8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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