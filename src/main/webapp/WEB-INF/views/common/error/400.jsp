<%@ page import="com.bbs.command.AdminCommands" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-03-09
  Time: 오후 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>400 에러</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        rel="stylesheet">
</head>
<body>
<div class="d-flex align-items-center justify-content-center vh-100">
  <div class="text-center">
    <h1 class="display-1 fw-bold">400</h1>
    <p class="fs-3"> 요청 오류 </p>
    <p class="lead">
      잘못된 요청입니다, 다시 시도해주세요
    </p>
    <a href="<%=AdminCommands.INDEX.getPath()%>" class="btn btn-primary">메인으로</a>
  </div>
</div>
</body>
</html>