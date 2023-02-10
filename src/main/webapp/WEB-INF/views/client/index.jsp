<%@ page import="com.bbs.command.ClientCommands" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-03
  Time: 오후 3:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/admin">/admin</a><br>
<% Iterator pathIterator = ClientCommands.getMap().keySet().iterator();
    String path = null;
    while (pathIterator.hasNext()) {
        path = pathIterator.next().toString();
%>
<a href="<%=path%>"><%=path%></a><br>
<%}%>
</body>
</html>
