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
    <title>회원 목록</title>
</head>
<body>
<%--  탑 네브 바    --%>
<jsp:include page="<%=JspComponents.TOP_NAV.getPath()%>"></jsp:include>

<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="<%=JspComponents.SIDE_MENU.getPath()%>"></jsp:include>

        <div class="col" style="background-color: #f5f5f5">
            <div class="fs-3 fw-bold mt-4 text-secondary">
                회원 목록
            </div>

            <div class="card bg-white  p-4 mt-3" id="component">
                <%--게시판 목록이 아닌 유저 목록임을 표시하기 위한 변수 (검색 바에서 카테고리 드롭다운 제외하기 위함)--%>
                <c:set var = "userManagement" scope = "request" value = "${true}"/>
                <jsp:include page="<%=JspComponents.SEARCH_BAR.getPath()%>"></jsp:include>

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
                            <td>
                                <a href="<%=AdminCommands.USER_DETAIL.getPath()%>?account=${user.account}">${user.account}</a>
                            </td>
                            <td>${user.countArticle}</td>
                            <td>${user.countVisit}</td>
                            <td>${user.dateLastLogin}</td>
                            <td>${user.dateRegistered }</td>
                            <td>${user.userDeleted }</td>
                            <td>${user.dateDeleted }</td>


                            <%-- 유저가 삭제되지 않았다면 삭제 버튼, 삭제되었다면 복구 버튼 표시--%>
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

                <jsp:include page="<%=JspComponents.PAGINATION.getPath()%>"></jsp:include>
                <button class="btn btn-primary" style="width: 10%"
                        onclick="location.href=`<%=AdminCommands.USER_REGISTER_FORM.getPath()%>`">
                    회원 등록
                </button>
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
