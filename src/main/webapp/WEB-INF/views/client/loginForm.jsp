<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-06
  Time: 오후 6:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="loginForm" action="/login" method="post">
    <table>
        <tr>
            <td>
                <h1>로그인 페이지</h1>
            </td>
        </tr>
        <tr>
            <td><label for="account">아이디 : </label></td>
            <td><input type="text" name="account" id="account" autocomplete="off"></td>
        </tr>
        <tr>
            <td><label for="password">비밀번호 : </label></td>
            <td><input type="password" name="password" id="password" autocomplete="off"></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">로그인</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
