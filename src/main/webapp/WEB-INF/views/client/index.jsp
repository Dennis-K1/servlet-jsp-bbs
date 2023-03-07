<%@ page import="com.bbs.command.ClientCommands" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.bbs.command.AdminCommands" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-03
  Time: 오후 3:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
response.sendRedirect(AdminCommands.INDEX.getPath());
%>