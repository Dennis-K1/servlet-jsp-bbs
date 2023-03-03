<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-03-03
  Time: 오후 6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--1:1문의 상제 컴포넌트 --%>

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
                        <button onclick="replyArticle(${article.id})"
                                class="btn btn-primary float-end mt-2">답변하기
                        </button>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
</div>
