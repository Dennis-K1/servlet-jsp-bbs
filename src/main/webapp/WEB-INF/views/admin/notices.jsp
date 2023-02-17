<%@ page import="com.bbs.properties.AdminCommands" %>
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
<div align="center">
    <hr width="70%">
    <h1>공지사항 관리</h1>
    <hr width="70%">

    <table border="1" style="width: 70%;">
        <tr>
            <th width="7%">번호</th>
            <th width="15%">작성자</th>
            <th width="15%">제목</th>
            <th width="15%">조회수</th>
            <th width="15%">등록일</th>
            <th width="3%">이미지</th>
            <th width="3%">삭제여부</th>
            <th width="5%">삭제일</th>
            <th width="3%">게시글 삭제</th>
            <th width="3%">게시글 복구</th>
        </tr>
        <c:forEach items="${noticeList}" var="notice">
            <tr>
                <td>${notice.id}</td>
                <td>${notice.account}</td>
                <td>
                    <a href="<%=AdminCommands.NOTICE_DETAIL.getPath()%>?articleId=${notice.id}">${notice.title}</a>
                </td>
                <td>${notice.views}</td>
                <td>${notice.dateRegistered }</td>
                <td>${notice.fileAttached }</td>
                <td>${notice.articleDeleted }</td>
                <td>${notice.dateDeleted }</td>
                <c:choose>
                    <c:when test="${notice.articleDeleted == 0}">
                        <td>
                            <button onclick="deleteArticleById(${notice.id})">X</button>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td></td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${notice.articleDeleted == 0}">
                        <td></td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <button onclick="recoverArticleById(${notice.id})">X</button>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
    <div id="pageParameters">
        <c:if test="${pageParameters.pageNumber <= fn:length(pageParameters.displayedPageNumbers)}">
            <button onclick="toPageOf(${pageParameters.startPage})" class="pageButton">&lt;&lt;
            </button>
            <c:choose>
                <c:when test="${pageParameters.pageNumber == pageParameters.startPage}">
                    <button class="pageButton">&lt;</button>
                </c:when>
                <c:otherwise>
                    <button onclick="toPageOf(${pageParameters.pageNumber - 1})" class="pageButton">
                        &lt;
                    </button>
                </c:otherwise>
            </c:choose>
            <c:forEach items="${pageParameters.displayedPageNumbers}" var="page">
                <c:choose>
                    <c:when test="${page == pageParameters.pageNumber}">
                        <button class="pageButtonClicked">${page}</button>
                    </c:when>
                    <c:otherwise>
                        <button onclick="toPageOf(${page})" class="pageButton">${page}</button>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${pageParameters.pageNumber == pageParameters.endPage}">
                    <button class="pageButton">&gt;</button>
                </c:when>
                <c:otherwise>
                    <button onclick="toPageOf(${pageParameters.pageNumber + 1})" class="pageButton">
                        &gt;
                    </button>
                </c:otherwise>
            </c:choose>
            <button onclick="toPageOf(${pageParameters.endPage})" class="pageButton">&gt;&gt;
            </button>
        </c:if>
    </div>
    <div id="searchBar">
        <c:choose>
            <c:when test="${pageParameters.searchKeyword != null}">
                <input type="text" id="searchKeyword" value="${pageParameters.searchKeyword}">
            </c:when>
            <c:otherwise>
                <input type="text" id="searchKeyword" placeholder="키워드 입력">
            </c:otherwise>
        </c:choose>
        <button onclick="searchKeyword()">검색</button>
    </div>
</div>
<button onclick="location.href=`<%=AdminCommands.NOTICE_INPUT.getPath()%>`">글 등록</button>
</body>
<script>
  const searchKeyword = () => {
    let searchKeyword = document.getElementById('searchKeyword').value;
    let parameterObject = {};
    if (searchKeyword.length > 0) {
      parameterObject.searchKeyword = searchKeyword
    }
    linkTo("<%=AdminCommands.NOTICE_MANAGEMENT.getPath()%>", parameterObject)
  }
  const toPageOf = (pageNumber) => {
    let parameterObject = {'pageNumber':pageNumber}
    let searchKeyword = `${pageParameters.searchKeyword}`
    if (searchKeyword.length > 0) {
      parameterObject.searchKeyword = searchKeyword
    }
    linkTo("<%=AdminCommands.NOTICE_MANAGEMENT.getPath()%>", parameterObject)
  }

  const recoverArticleById = (articleId) => {
    const parameterObject = {'articleId':articleId}
    const method = 'post'
    const action = '<%=AdminCommands.ARTICLE_RECOVERY.getPath()%>'
    sendFormWithParameter(parameterObject, method, action)
  }
  const deleteArticleById = (articleId) => {
    const parameterObject = {'articleId':articleId}
    const method = 'post'
    const action = '<%=AdminCommands.ARTICLE_DELETE.getPath()%>'
    sendFormWithParameter(parameterObject, method, action)
  }
</script>

</html>
