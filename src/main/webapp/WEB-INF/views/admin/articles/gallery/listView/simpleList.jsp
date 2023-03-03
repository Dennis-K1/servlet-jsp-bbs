<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-03-04
  Time: 오전 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bbs.command.AdminCommands" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 갤러리 게시판 심플 리스트 (리스트뷰) 컴포넌트 --%>

<table class="mt-3 table text-center table-borderless">
    <tr class="font-weight-bold text-primary">
        <th scope="col">번호</th>
        <th scope="col">작성자</th>
        <th scope="col">제목</th>
        <th scope="col">조회수</th>
        <th scope="col">등록일</th>
        <th scope="col">게시글 삭제</th>
    </tr>
    <c:forEach items="${articleList}" var="article">
        <tr scope="row">
            <td>${article.id}</td>
            <td>${article.user.account}</td>
            <td>
                <a href="<%=AdminCommands.GALLERY_DETAIL.getPath()%>?articleId=${article.id}">${article.title}</a>
            </td>
            <td>${article.views}</td>
            <td>${article.dateRegistered }</td>
            <td>
                <button class="btn btn-primary"
                        onclick="deleteArticleById(${article.id})">X
                </button>
            </td>
        </tr>
    </c:forEach>
</table>