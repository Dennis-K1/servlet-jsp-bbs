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
<jsp:include page="util/topNav.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="util/sideMenu.jsp"></jsp:include>

        <div class="col" style="background-color: #f5f5f5">
            <div class="fs-3 fw-bold mt-4 text-secondary">
                유저 목록
            </div>

            <div class="card bg-white  p-4 mt-3" id="component">
                <jsp:include page="util/searchBar.jsp"></jsp:include>

                <table class="mt-5 table text-center table-borderless">
                    <tr class="text-xs font-weight-bold text-primary">
                        <th scope="col">번호</th>
                        <th scope="col">아이디</th>
                        <th scope="col">게시글 수</th>
                        <th scope="col">방문 횟수</th>
                        <th scope="col">마지막 접속일</th>
                        <th scope="col">등록일</th>
                        <th scope="col">삭제여부</th>
                        <th scope="col">삭제일</th>
                        <th scope="col">유저 삭제</th>
                        <th scope="col">유저 복구</th>
                    </tr>
                    <c:forEach items="${userList}" var="user">
                        <tr scope="row">
                            <td>${user.id}</td>
                            <td>${user.account}</td>
                            <td>${user.countArticle}</td>
                            <td>${user.countVisit}</td>
                            <td>${user.dateLastLogin}</td>
                            <td>${user.dateRegistered }</td>
                            <td>${user.userDeleted }</td>
                            <td>${user.dateDeleted }</td>
                            <c:choose>
                                <c:when test="${user.userDeleted == 0}">
                                    <td>
                                        <button class="btn btn-primary"
                                                onclick="deleteUserById(${user.id})">X
                                        </button>
                                    </td>
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
                                    <td>
                                        <button class="btn btn-primary"
                                                onclick="recoverUserById(${user.id})">X
                                        </button>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
                <jsp:include page="util/pagination.jsp"></jsp:include>
            </div>
        </div>
    </div>
</div>
</body>
<script>
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
