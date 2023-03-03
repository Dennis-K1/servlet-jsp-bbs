<%@ page import="com.bbs.command.AdminCommands" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-03-04
  Time: 오전 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 갤러리 게시판 카드 리스트 (갤러리뷰) 컴포넌트 --%>

<style>
  .card-img-top {
    width: 95%;
    height: 30vw;
    object-fit: cover;
  }
</style>
<div class="row row-cols-1 row-cols-md-2 g-4">
    <c:forEach items="${articleList}" var="article">
        <div class="col">
            <div class="card">
                <a href="<%=AdminCommands.GALLERY_DETAIL.getPath()%>?articleId=${article.id}">
                    <img src="data:image/jpg;base64,${article.image}"
                         class="card-img-top m-auto pt-2"
                         alt="...">
                </a>
                <div class="card-body">
                    <h5 class="card-title">
                        <a href="<%=AdminCommands.GALLERY_DETAIL.getPath()%>?articleId=${article.id}">
                                ${article.title}
                        </a>
                    </h5>
                    <p class="card-text">${article.content}</p>
                </div>
                <div class="card-footer">
                    <small class="text-muted">${article.dateRegistered}</small>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
