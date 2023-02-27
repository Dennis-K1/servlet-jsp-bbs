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

    <title>Title</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/templates/javascript/page-util.js"></script>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/templates/css/table.css">
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
<%--  탑 네브 바    --%>
<jsp:include page="<%=JspComponents.TOP_NAV.getPath()%>"></jsp:include>

<%-- 센터 --%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <%--        사이드바     --%>
        <jsp:include page="<%=JspComponents.SIDE_MENU.getPath()%>"></jsp:include>


        <%--      메인 콘텐츠   --%>
        <div class="col" style="background-color: #f5f5f5">
            <c:choose>
                <c:when test="${pageParameters.boardId == 1}">
                    <div class="fs-3 fw-bold mt-4 text-secondary">
                        공지사항 관리
                    </div>
                    <div class="card bg-white p-4 mt-3" id="component">
                        <jsp:include page="<%=JspComponents.SEARCH_BAR.getPath()%>"></jsp:include>

                        <table class="mt-3 table text-center table-borderless">
                            <tr class="font-weight-bold text-primary">
                                <th scope="col">번호</th>
                                <th scope="col">작성자</th>
                                <th scope="col">제목</th>
                                <th scope="col">조회수</th>
                                <th scope="col">등록일</th>
                                <th scope="col">이미지</th>
                                <th scope="col">게시글 삭제</th>
                            </tr>
                            <c:forEach items="${articleList}" var="article">
                                <tr scope="row">
                                    <td>${article.id}</td>
                                    <td>${article.user.account}</td>
                                    <td>
                                        <a href="<%=AdminCommands.NOTICE_DETAIL.getPath()%>?articleId=${article.id}">${article.title}</a>
                                    </td>
                                    <td>${article.views}</td>
                                    <td>${article.dateRegistered }</td>
                                    <td>${article.fileAttached }</td>
                                    <td>
                                        <button class="btn btn-primary"
                                                onclick="deleteArticleById(${article.id})">X
                                        </button>
                                    </td>

                                </tr>
                            </c:forEach>
                        </table>
                        <jsp:include page="<%=JspComponents.PAGINATION.getPath()%>"></jsp:include>
                        <button class="btn btn-primary" style="width: 10%"
                                onclick="location.href=`<%=AdminCommands.NOTICE_INPUT_FORM.getPath()%>`">
                            등록
                        </button>
                    </div>
                </c:when>
                <c:when test="${pageParameters.boardId == 2}">
                    <div class="fs-3 fw-bold mt-4 text-secondary">
                        자유게시판 관리
                    </div>
                    <div class="card bg-white p-4 mt-3">
                        <jsp:include page="<%=JspComponents.SEARCH_BAR.getPath()%>"></jsp:include>

                        <table class="mt-3 table text-center table-borderless">
                            <tr class="font-weight-bold text-primary">
                                <th scope="col">번호</th>
                                <th scope="col">작성자</th>
                                <th scope="col">제목</th>
                                <th scope="col">댓글수</th>
                                <th scope="col">조회수</th>
                                <th scope="col">등록일</th>
                                <th scope="col">파일</th>
                                <th scope="col">게시글 삭제</th>
                            </tr>
                            <c:forEach items="${articleList}" var="article">
                                <tr scope="row">
                                    <td>${article.id}</td>
                                    <td>${article.user.account}</td>
                                    <td>
                                        <a href="<%=AdminCommands.COMMUNITY_DETAIL.getPath()%>?articleId=${article.id}">${article.title}</a>
                                    </td>
                                    <td>${fn:length(article.replyList)}</td>
                                    <td>${article.views}</td>
                                    <td>${article.dateRegistered }</td>
                                    <td>${article.fileAttached }</td>
                                    <td>
                                        <button class="btn btn-primary"
                                                onclick="deleteArticleById(${article.id})">X
                                        </button>
                                    </td>

                                </tr>
                            </c:forEach>
                        </table>
                        <jsp:include page="<%=JspComponents.PAGINATION.getPath()%>"></jsp:include>
                    </div>
                </c:when>
                <c:when test="${pageParameters.boardId == 3}">
                    <div class="fs-3 fw-bold mt-4 text-secondary">
                        1:1문의 관리
                    </div>
                    <div class="card bg-white p-4 mt-3">
                        <jsp:include page="<%=JspComponents.SEARCH_BAR.getPath()%>"></jsp:include>

                        <table class="mt-3 table text-center table-borderless">
                            <tr class="font-weight-bold text-primary">
                                <th scope="col">번호</th>
                                <th scope="col">작성자</th>
                                <th scope="col">제목</th>
                                <th scope="col">등록일</th>
                                <th scope="col">게시글 답변 여부</th>
                                <th scope="col">게시글 삭제</th>
                            </tr>
                            <c:forEach items="${articleList}" var="article">
                                <tr scope="row">
                                    <td>${article.id}</td>
                                    <td>${article.user.account}</td>
                                    <td>
                                        <a href="<%=AdminCommands.INQUIRY_DETAIL.getPath()%>?articleId=${article.id}">${article.title}</a>
                                    </td>
                                    <td>${article.dateRegistered }</td>
                                    <c:choose>
                                        <c:when test="${fn:length(article.replyList)> 0}">
                                            <td>답변 완료</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>미답변</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>
                                        <button class="btn btn-primary"
                                                onclick="deleteArticleById(${article.id})">X
                                        </button>
                                    </td>

                                </tr>
                            </c:forEach>
                        </table>
                        <jsp:include page="<%=JspComponents.PAGINATION.getPath()%>"></jsp:include>
                    </div>
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
