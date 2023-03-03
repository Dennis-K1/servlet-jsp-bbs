<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-03-03
  Time: 오후 6:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.bbs.command.AdminCommands" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--자유게시판 상세 컴포넌트 --%>

<div class="fs-3 fw-bold mt-4 text-secondary">
    자유게시글 정보
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
                    <td><img src="data:image/jpg;base64,${article.image}"
                             width="240"
                             height="300"/></td>
                </c:when>
                <c:otherwise>
                    <td>이미지 없음</td>
                </c:otherwise>
            </c:choose>

        </tr>
    </table>
    <div class="d-flex justify-content-start" name="buttons">
        <button class="btn btn-primary ms-2"
                onclick="deleteArticleById(${article.id})">삭제
        </button>
        <button class="btn btn-primary ms-2"
                onclick="location.href=`<%=AdminCommands.COMMUNITY_MANAGEMENT.getPath()%>`">
            목록
        </button>
    </div>
</div>
<div class="fs-3 fw-bold mt-4 text-secondary">
    댓글 목록
</div>
<div class="card bg-white p-4 mt-3">
    <table class="mt-3 table text-center table-borderless">
        <c:choose>
            <c:when test="${fn:length(article.replyList) == 0}">
                <tr>
                    <td>등록된 댓글이 없습니다.</td>
                </tr>
                <tr>
                    <td class="reply">
                        <textarea id="reply" class="form-control"></textarea>
                        <button onclick="replyArticle(${article.id})"
                                class="btn btn-primary float-end mt-2">댓글 등록
                        </button>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <hr>
                <c:forEach items="${article.replyList}" var="reply">
                    <div>
                        <div>
                            <span class="font-weight-bold text-primary">${reply.user.account}</span>
                            <span>${reply.dateRegistered}</span>
                        </div>
                        <div>
                            <c:choose>
                                <c:when test="${reply.replyDeleted != 1}">
                                    <p>${reply.content}</p>
                                    <div style="position: relative" id="nestedReplyDiv${reply.id}"
                                         class="collapse">
                                        <textarea class="form-control"
                                                  id="nestedReply${reply.id}"
                                                  rows="5"/>
                                        <button class="btn btn-primary"
                                                onclick="nestedReply(${reply.id},${reply.articleId})"
                                                style="position: absolute; right:0; bottom:0">
                                            등록
                                        </button>
                                    </div>
                                    <button type="button"
                                            class="btn btn-primary"
                                            data-bs-toggle="collapse"
                                            data-bs-target="#nestedReplyDiv${reply.id}"
                                            aria-expanded="false"
                                            aria-controls="nestedReplyDiv${reply.id}">
                                        <span class="collapsed">대댓글 등록</span>
                                        <span class="expanded">접기</span>
                                    </button>
                                    <button onclick="deleteReply(${reply.id}, ${reply.articleId})"
                                            class="btn btn-danger">댓글 삭제
                                    </button>

                                </c:when>
                                <c:otherwise>
                                    <p>(숨김 처리된 댓글)
                                        <del>${reply.content}</del>
                                    </p>
                                    <button onclick="recoverReply(${reply.id}, ${reply.articleId})"
                                            class="btn btn-primary">댓글 복구
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <hr>
                        <c:if test="${fn:length(reply.nestedReplyList) > 0}">
                            <c:forEach items="${reply.nestedReplyList}"
                                       var="nestedReply">
                                <div class="ms-5 border-start border-3">
                                    <div class="ms-1">
                                        <span class="font-weight-bold text-primary">${nestedReply.account}</span>
                                        <span>${nestedReply.dateRegistered}</span>
                                    </div>
                                    <div class="ms-1">
                                        <c:choose>
                                            <c:when test="${nestedReply.replyDeleted != 1}">
                                                <p>${nestedReply.content}</p>
                                                <button onclick="deleteReply(${nestedReply.id}, ${nestedReply.articleId})"
                                                        class="btn btn-danger">
                                                    삭제
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <p>(숨김 처리된 댓글)
                                                    <del>${nestedReply.content}</del>
                                                </p>
                                                <button onclick="recoverReply(${nestedReply.id}, ${nestedReply.articleId})"
                                                        class="btn btn-primary">
                                                    복구
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <hr>
                            </c:forEach>
                        </c:if>
                    </div>
                </c:forEach>
                <tr>
                    <td class="reply">
                        <textarea id="reply" class="form-control" rows="5"></textarea>
                        <button onclick="replyArticle(${article.id})"
                                class="btn btn-primary float-end mt-2">댓글 등록
                        </button>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
</div>
