<%@ page import="com.bbs.command.AdminCommands" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/templates/javascript/page-util.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/css/item-list.css">
</head>
<body>
<div align ="center">
    <hr width="70%">
    <h1>유저 목록</h1>
    <hr width="70%">

    <table border="1" style="width: 70%;">
        <tr>
            <th width="7%">번호</th>
            <th width="15%">아이디</th>
            <th width="15%">등록일</th>
            <th width="3%">삭제여부</th>
            <th width="5%">삭제일</th>
            <th width="3%">유저 삭제</th>
            <th width="3%">유저 복구</th>
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
                <c:choose>
                    <c:when test="${user.userDeleted == 0}">
                        <td><button onclick="deleteUserById(${user.id})">X</button></td>
                    </c:when>
                    <c:otherwise>
                        <td></td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${user.userDeleted == 0}">
                        <td></td>
                    </c:when>
                    <c:otherwise>
                        <td><button onclick="recoverUserById(${user.id})">X</button></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
    <div id="pageParameters">
        <c:if test="${pageParameters.pageNumber <= fn:length(pageParameters.displayedPageNumbers)}">
            <button onclick="toPageOf(${pageParameters.startPage})" class="pageButton">&lt;&lt;</button>
            <button onclick="toPageOf(${pageParameters.pageNumber - 1})" class="pageButton">&lt;</button>
        <c:forEach items="${pageParameters.displayedPageNumbers}" var="page">
            <c:choose>
                <c:when test="${page == pageParameters.pageNumber}"><button class="pageButtonClicked">${page}</button></c:when>
                <c:otherwise><button onclick="toPageOf(${page})" class="pageButton">${page}</button></c:otherwise>
            </c:choose>
        </c:forEach>
            <button onclick="toPageOf(${pageParameters.pageNumber + 1})" class="pageButton">&gt;</button>
            <button onclick="toPageOf(${pageParameters.endPage})" class="pageButton">&gt;&gt;</button>
        </c:if>
    </div>

    <ul>
        <c:forEach items="${requestScope}" var="p">
            <li>${p.key} -> ${p.value}</li>
        </c:forEach>
    </ul>
</div>
</body>
<script>
    const toPageOf = (pageNumber) => {
      const parameterObject = {'pageNumber':pageNumber}
      linkTo("<%=AdminCommands.USER_MANAGEMENT.getPath()%>", parameterObject)
    }

    const recoverUserById = (userId) => {
      const parameterObject = {'userId': userId}
      const method = 'post'
      const action = '<%=AdminCommands.USER_RECOVERY.getPath()%>'
      sendFormWithParameter(parameterObject, method, action)
    }
    const deleteUserById = (userId) => {
      const parameterObject = {'userId': userId}
      const method = 'post'
      const action = '<%=AdminCommands.USER_DELETE.getPath()%>'
      sendFormWithParameter(parameterObject, method, action)
    }
</script>

</html>