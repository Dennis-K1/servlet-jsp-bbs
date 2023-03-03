<%@ page import="com.bbs.command.AdminCommands" %>
<%@ page import="com.bbs.properties.JspComponents" %>
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
    <c:choose>
        <c:when test="${pageParameters.boardId == 1}">
            <title>
                공지사항 관리
            </title>
        </c:when>
        <c:when test="${pageParameters.boardId == 2}">
            <title>
                자유게시판 관리
            </title>
        </c:when>
        <c:when test="${pageParameters.boardId == 3}">
            <title>
                1:1문의 관리
            </title>
        </c:when>
    </c:choose>
</head>

<body>
<%--  탑 네브 바    --%>
<jsp:include page="<%=JspComponents.TOP_NAV.getPath()%>"/>
<%-- 센터 --%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <%--        사이드바     --%>
        <jsp:include page="<%=JspComponents.SIDE_MENU.getPath()%>"/>
        <%--      메인 콘텐츠   --%>
        <div class="col" style="background-color: #f5f5f5">
            <c:choose>
                <%--      공지사항   --%>
                <c:when test="${pageParameters.boardId == 1}">
                    <jsp:include page="<%=JspComponents.ADMIN_NOTICE_MANAGEMENT.getPath()%>"/>
                </c:when>
                <%--      자유게시판   --%>
                <c:when test="${pageParameters.boardId == 2}">
                    <jsp:include page="<%=JspComponents.ADMIN_COMMUNITY_MANAGEMENT.getPath()%>"/>
                </c:when>
                <%--      1:1문의   --%>
                <c:when test="${pageParameters.boardId == 3}">
                    <jsp:include page="<%=JspComponents.ADMIN_INQUIRY_MANAGEMENT.getPath()%>"/>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
<script>
  const deleteArticleById = (articleId) => {
    const parameterObject = {'articleId': articleId}
    const method = 'post'
    const action = '<%=AdminCommands.ARTICLE_DELETE.getPath()%>'
    sendFormWithParameter(parameterObject, method, action)
  }
</script>

</html>
