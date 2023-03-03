<%@ page import="com.bbs.command.AdminCommands" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-17
  Time: 오후 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <style>
      .nav-item-selected {
        background-color: cornflowerblue;
      }

      .nav-item-heading {
        padding: 0 1rem;
        font-weight: 800;
        font-size: 0.8rem;
        text-align: left;
        color: #98b9fd;
      }

      .nav-item-border {
        border-top: 1px solid #eaecf4;
      }

      .nav-item {
        margin-top: 10px;
        padding: 10px;
      }

      .nav-item-text {
        color: #abd5ff;
      }

      ul {
        list-style-type: none;
        padding: 0;
      }
    </style>

</head>
<div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-primary">
    <div class="d-flex flex-column align-items-center align-items-sm-start pt-2 text-white min-vh-100">
        <ul class="navbar-nav d-flex flex-column mt-1 w-100">
            <hr class="nav-item-border">
            <li class="w-100">
                <span class="nav-item-heading">대시보드</span>
                <ul>
                    <li class="nav-item" id="<%=AdminCommands.INDEX.getPath()%>">
                        <a href="<%=AdminCommands.INDEX.getPath()%>"><span
                                class="nav-item-text ps-4 p-2">통계</span></a>
                    </li>
                </ul>
            </li>
            <hr class="nav-item-border">
            <li class="w-100">
                <span class="nav-item-heading">유저 관리</span>
                <ul>
                    <li class="nav-item" id="<%=AdminCommands.USER_MANAGEMENT.getPath()%>">
                        <a href="<%=AdminCommands.USER_MANAGEMENT.getPath()%>"><span
                                class="nav-item-text ps-4 p-2">회원 목록</span></a>
                    </li>
                </ul>
            </li>
            <hr class="nav-item-border">
            <li class="w-100">
                <span class="nav-item-heading">게시판 관리</span>
                <ul>
                    <li class="nav-item" id="<%=AdminCommands.NOTICE_MANAGEMENT.getPath()%>">
                        <a href="<%=AdminCommands.NOTICE_MANAGEMENT.getPath()%>">
                            <span class="nav-item-text ps-4 p-2">공지사항 관리</span>
                        </a>
                    </li>
                    <li class="nav-item" id="<%=AdminCommands.COMMUNITY_MANAGEMENT.getPath()%>">
                        <a href="<%=AdminCommands.COMMUNITY_MANAGEMENT.getPath()%>"><span class="nav-item-text ps-4 p-2">자유게시판 관리</span></a>
                    </li>
                    <li class="nav-item" id="<%=AdminCommands.INQUIRY_MANAGEMENT.getPath()%>">
                        <a href="<%=AdminCommands.INQUIRY_MANAGEMENT.getPath()%>">
                            <span class="nav-item-text ps-4 p-2">1:1문의 관리</span>
                        </a>
                    </li>
                </ul>
            </li>
            <hr class="nav-item-border">
        </ul>
    </div>
</div>

<script>

  let mainPath = window.location.pathname.split('/', 3).join('/')

  const navItem = document.getElementById(mainPath);
  navItem.classList.toggle('nav-item-selected');
  navItem.getElementsByTagName('span')[0].classList.toggle('text-light');
</script>
