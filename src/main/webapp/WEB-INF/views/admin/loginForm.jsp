<%@ page import="com.bbs.properties.JSPUtilRoutes" %>
<%@ page import="com.bbs.command.AdminCommands" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-03
  Time: 오후 4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="redirectURL" scope="request" value="${param.redirectURL}"/>
<%
    request.setAttribute("redirectURL", request.getParameter("redirectURL"));
%>
<html>
<head>
    <title>어드민 로그인</title>
<jsp:include page="<%=JSPUtilRoutes.ERROR_MESSAGE.getPath()%>"/>
</head>
<body>
<form name="loginForm" action="<%=AdminCommands.LOGIN.getPath()%>?redirectURL=${param.redirectURL}" method="post">
    <table>
        <tr>
            <td>
                <h1>어드민 로그인 페이지</h1>
                ${param.redirectURL}
            </td>
        </tr>
        <tr>
            <td><label for="account">아이디 : </label></td>
            <td><input type="text" name="account" id="account" autocomplete="off"></td>
        </tr>
        <tr>
            <td><label for="password">비밀번호 : </label></td>
            <td><input type="password" name="password" id="password" autocomplete="off"></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">로그인</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
