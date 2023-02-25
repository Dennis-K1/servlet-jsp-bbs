<%@ page import="com.bbs.command.AdminCommands" %>
<%@ page import="com.bbs.properties.JspComponents" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-16
  Time: 오전 1:23
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Title</title>
</head>
<body>
<%--  탑 네브 바    --%>
<jsp:include page="<%=JspComponents.TOP_NAV.getPath()%>"></jsp:include>

<%-- 센터 --%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <%--        사이드 메뉴      --%>
        <jsp:include page="<%=JspComponents.SIDE_MENU.getPath()%>"></jsp:include>
        <%--         메인 콘텐츠      --%>
        <div class="col py-3" style="background-color: #f5f5f5">
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
                    <img src="data:image/jpg;base64,${article.image}" width="240" height="300"/>
                </c:when>
                <c:otherwise>
                    nothing
                </c:otherwise>
            </c:choose>
            <button onclick="location.href=`<%=AdminCommands.NOTICE_EDIT_FORM.getPath()%>?articleId=${article.id}`">수정</button>
            <button onclick="deleteArticleById(${article.id})">삭제</button>
            <button>목록</button>
        </div>
    </div>
</div>
</body>
<script>
  const deleteArticleById = (articleId) => {
    const parameterObject = {'articleId':articleId}
    const method = 'post'
    const action = '<%=AdminCommands.ARTICLE_DELETE.getPath()%>'
    sendFormWithParameter(parameterObject, method, action)}
</script>
</html>
