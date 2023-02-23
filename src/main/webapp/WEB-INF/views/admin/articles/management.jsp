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
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/templates/css/item-list.css">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
          crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
            crossorigin="anonymous"></script>
</head>

<body>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-primary">
            <jsp:include page="../sideMenu.jsp"></jsp:include>
        </div>
        <div class="col py-3">
            <div class="col py-3">
                <table border="1" style="width: 100%;">
                    <tr>
                        <th width="7%">번호</th>
                        <th width="15%">작성자</th>
                        <th width="15%">제목</th>
                        <th width="15%">조회수</th>
                        <th width="15%">등록일</th>
                        <th width="3%">이미지</th>
                        <th width="3%">게시글 삭제</th>
                    </tr>
                    <c:forEach items="${articleList}" var="article">
                        <tr>
                            <td>${article.id}</td>
                            <td>${article.account}</td>
                            <td>
                                <a href="<%=AdminCommands.NOTICE_DETAIL.getPath()%>?articleId=${article.id}">${article.title}</a>
                            </td>
                            <td>${article.views}</td>
                            <td>${article.dateRegistered }</td>
                            <td>${article.fileAttached }</td>
                            <td>
                                <button onclick="deleteArticleById(${article.id})">X</button>
                            </td>

                        </tr>
                    </c:forEach>
                </table>
                <div id="pageParameters">
                    <c:if test="${pageParameters.pageNumber <= fn:length(pageParameters.displayedPageNumbers)}">
                        <button onclick="toPageOf(${pageParameters.startPage})" class="pageButton">
                            &lt;&lt;
                        </button>
                        <c:choose>
                            <c:when test="${pageParameters.pageNumber == pageParameters.startPage}">
                                <button class="pageButton">&lt;</button>
                            </c:when>
                            <c:otherwise>
                                <button onclick="toPageOf(${pageParameters.pageNumber - 1})"
                                        class="pageButton">
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
                                    <button onclick="toPageOf(${page})"
                                            class="pageButton">${page}</button>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${pageParameters.pageNumber == pageParameters.endPage}">
                                <button class="pageButton">&gt;</button>
                            </c:when>
                            <c:otherwise>
                                <button onclick="toPageOf(${pageParameters.pageNumber + 1})"
                                        class="pageButton">
                                    &gt;
                                </button>
                            </c:otherwise>
                        </c:choose>
                        <button onclick="toPageOf(${pageParameters.endPage})" class="pageButton">
                            &gt;&gt;
                        </button>
                    </c:if>
                </div>
                <div id="searchBar">
                    <c:choose>
                        <c:when test="${pageParameters.searchKeyword != null}">
                            <input type="text" id="searchKeyword"
                                   value="${pageParameters.searchKeyword}">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="searchKeyword" placeholder="키워드 입력">
                        </c:otherwise>
                    </c:choose>
                    <button onclick="searchKeyword()">검색</button>
                </div>
                <button onclick="location.href=`<%=AdminCommands.NOTICE_INPUT_FORM.getPath()%>`">글
                    등록
                </button>
            </div>
        </div>
    </div>
</div>
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
    let parameterObject = {'pageNumber': pageNumber}
    let searchKeyword = `${pageParameters.searchKeyword}`
    if (searchKeyword.length > 0) {
      parameterObject.searchKeyword = searchKeyword
    }
    linkTo("<%=AdminCommands.NOTICE_MANAGEMENT.getPath()%>", parameterObject)
  }
  const deleteArticleById = (articleId) => {
    const parameterObject = {'articleId': articleId}
    const method = 'post'
    const action = '<%=AdminCommands.ARTICLE_DELETE.getPath()%>'
    sendFormWithParameter(parameterObject, method, action)
  }
</script>

</html>
