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
</head>
<body>
<c:choose>
<c:when test="${article.boardId == 1}">
<form name="articleForm" action="<%=AdminCommands.NOTICE_INPUT.getPath()%>" method="post">
    <table>
        <tr>
            <td>
                <h1>게시글 등록 페이지</h1>
            </td>
        </tr>
        <tr>
            <td><label for="title">제목 : </label></td>
            <td><input type="text" name="title" id="title" autocomplete="off" value="${article.title}"></td>
        </tr>
        <tr>
            <td><label for="content">내용 : </label></td>
            <td><textarea name="content" id="content" autocomplete="off">${article.content}</textarea></td>
        </tr>
    </table>
</form>
</c:when>
<c:otherwise>
nothing
</c:otherwise>
</c:choose>

<button>수정하기</button>
<button>뒤로가기</button>
</body>
</html>