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


<%-- 각 게시판 상세 페이지 공통 관리 파일--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:choose>
        <c:when test="${article.boardId == 1}">
            <title>
                공지사항 정보
            </title>
        </c:when>
        <c:when test="${article.boardId == 2}">
            <title>
                자유게시판 정보
            </title>
        </c:when>
        <c:when test="${article.boardId == 3}">
            <title>
                1:1문의 정보
            </title>
        </c:when>
        <c:when test="${article.boardId == 4}">
            <title>
                갤러리 정보
            </title>
        </c:when>
    </c:choose>
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
        <div class="col" style="background-color: #f5f5f5">
            <c:choose>
                <%--         공지사항      --%>
                <c:when test="${article.boardId == 1}">
                    <jsp:include page="<%=JspComponents.ADMIN_NOTICE_DETAIL.getPath()%>"/>
                </c:when>
                <%--         자유게시판     --%>
                <c:when test="${article.boardId == 2}">
                    <jsp:include page="<%=JspComponents.ADMIN_COMMUNITY_DETAIL.getPath()%>"/>
                </c:when>
                <%--         1:1문의     --%>
                <c:when test="${article.boardId == 3}">
                    <jsp:include page="<%=JspComponents.ADMIN_INQUIRY_DETAIL.getPath()%>"/>
                </c:when>
                <%--         갤러리     --%>
                <c:when test="${article.boardId == 4}">
                    <jsp:include page="<%=JspComponents.ADMIN_GALLERY_DETAIL.getPath()%>"/>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
<style>
  [aria-expanded="false"] > .expanded,
  [aria-expanded="true"] > .collapsed {
    display: none;
  }
</style>
<script>
  const deleteArticleById = (articleId) => {
    const parameterObject = {'articleId': articleId}
    const method = 'post'
    const action = '<%=AdminCommands.ARTICLE_DELETE.getPath()%>'
    sendFormWithParameter(parameterObject, method, action)
  }

  const replyArticle = (articleId) => {
    let content = document.getElementById('reply').value
    if (validateReply(content) === false) {
      return;
    }
    const parameterObject = {'articleId': articleId, 'content': content}
    const method = 'post'
    const action = '<%=AdminCommands.REPLY_INPUT.getPath()%>'
    sendFormWithParameter(parameterObject, method, action)
  }

  const deleteReply = (replyId, articleId) => {
    const parameterObject = {'replyId': replyId, 'articleId': articleId}
    const method = 'post'
    const action = '<%=AdminCommands.REPLY_DELETE.getPath()%>'
    sendFormWithParameter(parameterObject, method, action)
  }

  const recoverReply = (replyId, articleId) => {
    const parameterObject = {'replyId': replyId, 'articleId': articleId}
    const method = 'post'
    const action = '<%=AdminCommands.REPLY_RECOVERY.getPath()%>'
    sendFormWithParameter(parameterObject, method, action)
  }

  const nestedReply = (replyId, articleId) => {
    let content = document.getElementById(`nestedReply\${replyId}`).value
    if (validateReply(content) === false) {
      return;
    }
    const parameterObject = {'replyId': replyId, 'articleId': articleId, 'content': content}
    const method = 'post'
    const action = '<%=AdminCommands.REPLY_INPUT.getPath()%>'
    sendFormWithParameter(parameterObject, method, action)
  }

  function validateReply(content) {
    if (content === ""){
      alert("내용을 1글자 이상 100글자 미만으로 입력해주세요.")
      return false;
    }
    if (content.length < 1 || content.length > 100){
      alert("내용을 1글자 이상 100글자 미만으로 입력해주세요.")
      return false;
    }
  }
</script>
</html>
