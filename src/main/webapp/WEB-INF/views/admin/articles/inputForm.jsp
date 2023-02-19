<%@ page import="com.bbs.command.AdminCommands" %>
<%@ page import="com.bbs.properties.JSPUtilRoutes" %>
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
    <jsp:include page="<%=JSPUtilRoutes.ERROR_MESSAGE.getPath()%>"/>
</head>
<body>
<c:choose>
    <c:when test="${boardId == 1}">
        <form action="<%=AdminCommands.NOTICE_INPUT.getPath()%>" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>
                        <h1>게시글 등록 페이지</h1>
                    </td>
                </tr>
                <tr>
                    <td><label for="title">제목 : </label></td>
                    <td><input type="text" name="title" id="title" autocomplete="off"></td>
                </tr>
                <tr>
                    <td><label for="content">내용 : </label></td>
                    <td><textarea name="content" id="content" autocomplete="off"></textarea></td>
                </tr>
                <tr>
                    <td><label for="file">내용 : </label></td>
                    <td><input type="file" name="file" id="file"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submit">등록</button>
                    </td>
                </tr>
            </table>
        </form>
    </c:when>
    <c:otherwise>
        nothing
    </c:otherwise>
</c:choose>

</body>
</html>
