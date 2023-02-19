<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-06
  Time: 오후 4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>

<form name="registerForm" action="/register" method="post">
    <table>
        <tr>
            <td>
               <h1>회원가입 페이지</h1>
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
                <button type="submit">회원가입</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
