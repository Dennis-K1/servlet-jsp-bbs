<%@ page import="com.bbs.command.AdminCommands" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-17
  Time: 오후 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>


<nav class="navbar navbar-expand d-flex flex-column align-item-start bg-primary" id="sidebar">
    <a href="#" class="navbar-brand text-light mt-5">
        <div class="display-5 font-weight-bold">어드민</div>
    </a>
    <ul class="navbar-nav d-flex flex-column mt-5 w-100">
        <li class="nav-item w-100">
            <a href="<%=AdminCommands.INDEX.getPath()%>" class="nav-link text-light">
                Home
            </a>
        </li>
        <li class="nav-item w-100">
            <a href="<%=AdminCommands.USER_MANAGEMENT.getPath()%>" class="nav-link text-light">
                유저 관리
            </a>
            <ul>
                <li>
                    <a href="<%=AdminCommands.USER_MANAGEMENT.getPath()%>" class="dropdown-item text-light ps-4 p-2">유저 목록</a>
                </li>
            </ul>
        </li>
        <li class="nav-item w-100">
            <a href="<%=AdminCommands.NOTICE_MANAGEMENT.getPath()%>" class="nav-link text-light">
                게시판 관리
            </a>
            <ul>
                <li>
                    <a href="<%=AdminCommands.NOTICE_MANAGEMENT.getPath()%>" class="dropdown-item text-light ps-4 p-2">공지사항 관리</a>
                </li>
                <li>
                    <a href="#" class="dropdown-item text-light ps-4 p-2">자유게시판 관리</a>
                </li>
            </ul>
        </li>
    </ul>
</nav>
<style>
.navbar {
  width: 250px;
  height: 100vh;
  position: fixed;
  transition: 0.4s;
}

.nav-link {
  font-size: 1.25em;
}
.nav-link:active,
.nav-link:focus,

.dropdown-item:hover{
  background-color: cornflowerblue;
}
ul{
  list-style-type: none;
  padding: 0;
}


</style>