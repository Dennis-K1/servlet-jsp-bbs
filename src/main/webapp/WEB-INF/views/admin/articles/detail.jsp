<%@ page import="com.bbs.command.AdminCommands" %>
<%@ page import="com.bbs.properties.JspComponents" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-16
  Time: 오전 1:23
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Title</title>
</head>
<body>
<%--  탑 네브 바    --%>
<jsp:include page="<%=JspComponents.TOP_NAV.getPath()%>"></jsp:include>

<%-- 센터 --%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <%--        사이드 메뉴      --%>
        <jsp:include page="<%=JspComponents.SIDE_MENU.getPath()%>"></jsp:include>
        <%--         메인 콘텐츠      --%>
        <div class="col" style="background-color: #f5f5f5">
            <c:choose>
                <c:when test="${article.boardId == 1}">
                    <div class="fs-3 fw-bold mt-4 text-secondary">
                        공지사항 정보
                    </div>
                    <div class="card bg-white p-4 mt-3">
                        <table class="mt-3 table table-borderless">
                            <tr>
                                <th class="w-25 font-weight-bold text-primary">제목</th>
                                <td>${article.title}</td>
                            </tr>
                            <tr>
                                <th class="w-25 font-weight-bold text-primary">작성자</th>
                                <td>${article.user.account}</td>
                            </tr>
                            <tr>
                                <th class="w-25 font-weight-bold text-primary">조회수</th>
                                <td>${article.views}</td>
                            </tr>
                            <tr>
                                <th class="w-25 font-weight-bold text-primary">등록일</th>
                                <td>${article.dateRegistered}</td>
                            </tr>
                            <tr>
                                <th class="w-25 font-weight-bold text-primary">내용</th>
                                <td>${article.content}</td>
                            </tr>
                            <tr>
                                <th class="w-25 font-weight-bold text-primary">파일</th>
                                <c:choose>
                                    <c:when test="${article.image != null}">
                                        <td><img src="data:image/jpg;base64,${article.image}" width="240"
                                                 height="300"/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>이미지 없음</td>
                                    </c:otherwise>
                                </c:choose>

                            </tr>
                        </table>
                        <div class="d-flex justify-content-start" name="buttons">
                            <button class="btn btn-primary" onclick="location.href=`<%=AdminCommands.NOTICE_EDIT_FORM.getPath()%>?articleId=${article.id}`">
                                수정
                            </button>
                            <button class="btn btn-primary ms-2"onclick="deleteArticleById(${article.id})">삭제</button>
                            <button class="btn btn-primary ms-2" onclick="location.href=`<%=AdminCommands.NOTICE_MANAGEMENT.getPath()%>`">목록</button>
                        </div>
                    </div>
                </c:when>
                <c:when test="${article.boardId == 3}">
                <div class="fs-3 fw-bold mt-4 text-secondary">
                    1:1문의 정보
                </div>
                <div class="card bg-white p-4 mt-3">
                    <table class="mt-3 table table-borderless">
                        <tr>
                            <th class="w-25 font-weight-bold text-primary">제목</th>
                            <td>${article.title}</td>
                        </tr>
                        <tr>
                            <th class="w-25 font-weight-bold text-primary">작성자</th>
                            <td>${article.user.account}</td>
                        </tr>
                        <tr>
                            <th class="w-25 font-weight-bold text-primary">등록일</th>
                            <td>${article.dateRegistered}</td>
                        </tr>
                        <tr>
                            <th class="w-25 font-weight-bold text-primary">내용</th>
                            <td>${article.content}</td>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(article.replyList) > 0}">
                                <tr>
                                    <th class="w-25 font-weight-bold text-primary">답변 여부</th>
                                    <td>답변 완료</td>
                                </tr>
                                <tr>
                                    <th class="w-25 font-weight-bold text-primary">답변</th>
                                    <td class="reply">
                                        ${article.replyList[0].content}
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <th class="w-25 font-weight-bold text-primary">답변 여부</th>
                                    <td>미답변</td>
                                </tr>
                                <tr>
                                    <th class="w-25 font-weight-bold text-primary">답변</th>
                                    <td class="reply">
                                        <textarea id="reply" class="form-control"></textarea>
                                        <button onclick="replyInquiry(${article.id})" class="btn btn-primary float-end mt-2">답변하기</button>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </div>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
<script>
  const deleteArticleById = (articleId) => {
    const parameterObject = {'articleId': articleId}
    const method = 'post'
    const action = '<%=AdminCommands.ARTICLE_DELETE.getPath()%>'
    sendFormWithParameter(parameterObject, method, action)
  }

  const replyInquiry = (articleId) => {
    let content = document.getElementById('reply').value
    const parameterObject = {'articleId': articleId, 'content':content}
    const method = 'post'
    const action = '<%=AdminCommands.INQUIRY_REPLY.getPath()%>'
    sendFormWithParameter(parameterObject, method, action)
  }
</script>
</html>
