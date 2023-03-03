<%@ page import="com.bbs.command.AdminCommands" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-03-04
  Time: 오전 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 갤러리 게시판 이미지 리스트 (웹진뷰) 컴포넌트 --%>

<c:forEach items="${articleList}" var="article">
    <div class="card p-2 mb-2">
        <div class="row g-0">
            <div class="col-md-4">
                <a href="<%=AdminCommands.GALLERY_DETAIL.getPath()%>?articleId=${article.id}">
                    <img src="data:image/jpg;base64,${article.image}"
                         class="rounded-start img-fluid mx-auto"
                         alt="...">
                </a>
            </div>
            <div class="col-md-6">
                <div class="card-body">
                    <h5 class="card-title">
                        <a href="<%=AdminCommands.GALLERY_DETAIL.getPath()%>?articleId=${article.id}">
                                ${article.title}
                        </a>
                    </h5>
                    <p class="card-text">${article.content}</p>
                    <p class="card-text"><small class="text-muted">${article.dateRegistered}</small>
                    </p>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

