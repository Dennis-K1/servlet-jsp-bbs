<%@ page import="com.bbs.properties.AdminCommands" %>
<%@ page import="java.util.Iterator" %><%--
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
<jsp:include page="sideMenu.jsp"></jsp:include>

<% Iterator pathIterator = AdminCommands.getMap().keySet().iterator();
    String path = null;
    while (pathIterator.hasNext()) {
        path = pathIterator.next().toString();
    %>
<a href="<%=path%>"><%=path%></a><br>
<%}%>
</body>
</html>
