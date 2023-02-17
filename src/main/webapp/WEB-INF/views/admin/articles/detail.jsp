<%@ page import="com.bbs.command.AdminCommands" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-16
  Time: 오전 1:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/templates/javascript/page-util.js"></script>
</head>
<body>
<c:choose>
    <c:when test="${article.boardId == 1}">
        <table>
            <tr>
                <td>제목</td>
                <td>${article.title}</td>
            </tr>
            <tr>
                <td>내용</td>
                <td>${article.content}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        nothing
    </c:otherwise>
</c:choose>

<button onclick="location.href=`<%=AdminCommands.NOTICE_EDIT.getPath()%>?articleId=${article.id}`">수정</button>
<button onclick="location.href=`<%=AdminCommands.NOTICE_DETAIL.getPath()%>`">삭제</button>
<button>목록</button>
</body>
<script>

</script>
</html>