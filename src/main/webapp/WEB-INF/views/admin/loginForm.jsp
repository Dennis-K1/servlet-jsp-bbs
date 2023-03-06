<%@ page import="com.bbs.properties.JspComponents" %>
<%@ page import="com.bbs.command.AdminCommands" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-03
  Time: 오후 4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 이전 페이지에서 로그인이 안되어 로그인 페이지로 넘어올 경우 redirectURL 설정 --%>
<c:set var="redirectURL" scope="request" value="${param.redirectURL}"/>
<%
    request.setAttribute("redirectURL", request.getParameter("redirectURL"));
%>
<script>
  <%--이전 페이지에서 뒤로가기 하여 해당 페이지 도착한 경우 새로고침--%>
  window.addEventListener( "pageshow", function ( event ) {
    let historyTraversal = event.persisted ||
        ( typeof window.performance != "undefined" &&
            window.performance.navigation.type === 2 );
    if ( historyTraversal ) {
      // Handle page restore.
      window.location.reload();
    }
  });
</script>
<html>
<head>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
          crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
            crossorigin="anonymous"></script>
    <title>어드민 로그인</title>

    <%--  백엔드에서 에러가 날라 올 경우 Alert  --%>
    <jsp:include page="<%=JspComponents.ERROR_HANDLER.getPath()%>"/>
</head>

<body class="text-center">
<div class="form-login">
    <form name="loginForm" action="<%=AdminCommands.LOGIN.getPath()%>?redirectURL=${param.redirectURL}"
          method="post">
        <h1 class="h3 mb-3 fw-normal">어드민 로그인 페이지</h1>
        <div class="form-floating">
            <input class="form-control" type="text" name="account" id="account" autocomplete="off">
            <label for="account">아이디 : </label>
        </div>
        <div class="form-floating">
            <input class="form-control" type="password" name="password" id="password" autocomplete="off">
            <label for="password">비밀번호 : </label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
    </form>
</div>
</body>
<style>

  html,
  body {
    height: 100%;
  }

  body {
    display: flex;
    align-items: center;
    padding-top: 40px;
    padding-bottom: 40px;
    background-color: #f5f5f5;
  }

  .form-login {
    width: 100%;
    max-width: 330px;
    padding: 15px;
    margin: auto;
  }

  .form-login .checkbox {
    font-weight: 400;
  }

  .form-login .form-floating:focus-within {
    z-index: 2;
  }

  .form-login input[type="text"] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }

  .form-login input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
  }

</style>
</html>
