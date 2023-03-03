<%@ page import="com.bbs.command.AdminCommands" %>
<%@ page import="com.bbs.properties.JspComponents" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-03-03
  Time: 오후 6:22
  To change this template use File | Settings | File Templates.
--%>


<%-- 공지사항 관리 목록 페이지 컴포넌트 --%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="fs-3 fw-bold mt-4 text-secondary">
    공지사항 관리
</div>
<div class="card bg-white p-4 mt-3" id="component">
    <jsp:include page="<%=JspComponents.SEARCH_BAR.getPath()%>"/>
    <table class="mt-3 table text-center table-borderless">
        <tr class="font-weight-bold text-primary">
            <th scope="col">번호</th>
            <th scope="col">작성자</th>
            <th scope="col">제목</th>
            <th scope="col">조회수</th>
            <th scope="col">등록일</th>
            <th scope="col">이미지</th>
            <th scope="col">게시글 삭제</th>
        </tr>
        <c:forEach items="${articleList}" var="article">
            <tr scope="row">
                <td>${article.id}</td>
                <td>${article.user.account}</td>
                <td>
                    <a href="<%=AdminCommands.NOTICE_DETAIL.getPath()%>?articleId=${article.id}">${article.title}</a>
                </td>
                <td>${article.views}</td>
                <td>${article.dateRegistered }</td>
                <td>${article.fileAttached }</td>
                <td>
                    <button class="btn btn-primary"
                            onclick="deleteArticleById(${article.id})">X
                    </button>
                </td>

            </tr>
        </c:forEach>
    </table>
    <jsp:include page="<%=JspComponents.PAGINATION.getPath()%>"/>
    <button class="btn btn-primary" style="width: 10%"
            onclick="location.href=`<%=AdminCommands.NOTICE_INPUT_FORM.getPath()%>`">
        등록
    </button>
</div>
