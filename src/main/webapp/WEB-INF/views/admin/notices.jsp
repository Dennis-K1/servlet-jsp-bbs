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
</head>
<style scoped>
  a:link {
    color: black;
    text-decoration: none;
  }

  a:visited {
    color: black;
    text-decoration: none;
  }
  .pageButton{
    background-color: white;
    border: none;
    padding: 5px;
    cursor: pointer;

  }

  .pageButtonClicked{
    background-color: white;
    border: none;
    padding: 5px;
    color: red;
  }

  .titleButton{
    background-color: white;
    border: none;
    text-decoration: underline;
    font-size: 16px;
    cursor: pointer;
  }

  .pageButton:hover{
    background-color: grey;
  }
</style>
<body>
<div align ="center">
    <hr width="70%">
    <h1>공지사항 관리</h1>
    <hr width="70%">

    <table border="1" style="width: 70%;">
        <tr>
            <th width="7%">번호</th>
            <th width="15%">작성자</th>
            <th width="15%">제목</th>
            <th width="15%">등록일</th>
            <th width="3%">삭제여부</th>
            <th width="5%">삭제일</th>
            <th width="3%">게시글 삭제</th>
            <th width="3%">게시글 복구</th>
        </tr>
        <c:forEach items="${noticeList}" var="notice">
            <tr>
                <td>${notice.id}</td>
                    <%--다른 경로일 경우(다른 폴더)
                    <a href="/Misiion-Web/jsp/notice/detail.jsp">
                     --%>
<%--                <td><a href="detail.jsp?no=${notice.no }">${notice.account }</a></td>--%>
                <td>${notice.account}</td>
                <td>${notice.title}</td>
                <td>${notice.dateRegistered }</td>
                <td>${notice.articleDeleted }</td>
                <td>${notice.dateDeleted }</td>
                <c:choose>
                    <c:when test="${notice.articleDeleted == 0}">
                        <td><button onclick="deleteUserById(${notice.id})">X</button></td>
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
                        <td><button onclick="recoverUserById(${notice.id})">X</button></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
    <div id="pageParameters">
        <c:if test="${pageParameters.pageNumber <= fn:length(pageParameters.displayedPageNumbers)}">
            <button onclick="toPageOf(${pageParameters.startPage})" class="pageButton">&lt;&lt;</button>
            <c:choose>
                <c:when test="${pageParameters.pageNumber == pageParameters.startPage}"><button class="pageButton">&lt;</button> </c:when>
                <c:otherwise><button onclick="toPageOf(${pageParameters.pageNumber - 1})" class="pageButton">&lt;</button></c:otherwise>
            </c:choose>
        <c:forEach items="${pageParameters.displayedPageNumbers}" var="page">
            <c:choose>
                <c:when test="${page == pageParameters.pageNumber}"><button class="pageButtonClicked">${page}</button></c:when>
                <c:otherwise><button onclick="toPageOf(${page})" class="pageButton">${page}</button></c:otherwise>
            </c:choose>
        </c:forEach>
            <c:choose>
                <c:when test="${pageParameters.pageNumber == pageParameters.endPage}"><button class="pageButton">&gt;</button> </c:when>
                <c:otherwise><button onclick="toPageOf(${pageParameters.pageNumber + 1})" class="pageButton">&gt;</button></c:otherwise>
            </c:choose>
            <button onclick="toPageOf(${pageParameters.endPage})" class="pageButton">&gt;&gt;</button>
        </c:if>
    </div>

    <ul>
        <c:forEach items="${requestScope}" var="p">
            <li>${p.key} -> ${p.value}</li>
        </c:forEach>
    </ul>
</div>
</body>
<script>
    const toPageOf = (pageNumber) => {
      window.location.href=`<%=AdminCommands.NOTICE_MANAGEMENT.getPath()%>?pageNumber=\${pageNumber}`
    }

    <%--const recoverUserById = (userId) => {--%>
    <%--  const parameterKey = 'userId'--%>
    <%--  const action = '<%=AdminCommands.USER_RECOVERY.getPath()%>'--%>
    <%--  const method = 'post'--%>
    <%--  sendFormWithParameter(userId, parameterKey, action, method)--%>
    <%--}--%>
    <%--const deleteUserById = (userId) => {--%>
    <%--  const parameterKey = 'userId'--%>
    <%--  const action = '<%=AdminCommands.USER_DELETE.getPath()%>'--%>
    <%--  const method = 'post'--%>
    <%--  sendFormWithParameter(userId, parameterKey, action, method)--%>
    <%--}--%>

    const sendFormWithParameter = (parameter, parameterKey, action, method) => {
      let form = document.createElement("form");
      let parameterArray = new Array();
      let inputArray = new Array();
      form.action = action;
      form.method = method;

      parameterArray.push( [parameterKey, parameter] );

      for (let i = 0; i < parameterArray.length; i++) {
        inputArray[i] = document.createElement("input");
        inputArray[i].setAttribute("type", "hidden");
        inputArray[i].setAttribute('name', parameterArray[i][0]);
        inputArray[i].setAttribute("value", parameterArray[i][1]);
        form.appendChild(inputArray[i]);
      }
      document.body.appendChild(form);
      form.submit();
    }
</script>

</html>
