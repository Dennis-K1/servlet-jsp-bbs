<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-10
  Time: 오전 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align ="center">
    <hr width="70%">
    <h1>유저 목록</h1>
    <hr width="70%">

    <table border="1" style="width: 70%;">
        <tr>
            <th width="7%">번호</th>
            <th width="16%">아이디</th>
            <th width="20%">등록일</th>
            <th width="5%">삭제여부</th>
            <th width="5%">삭제일</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                    <%--다른 경로일 경우(다른 폴더)
                    <a href="/Misiion-Web/jsp/user/detail.jsp">
                     --%>
<%--                <td><a href="detail.jsp?no=${user.no }">${user.account }</a></td>--%>
                <td>${user.account}</td>
                <td>${user.dateRegistered }</td>
                <td>${user.userDeleted }</td>
                <td>${user.dateDeleted }</td>

            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>
