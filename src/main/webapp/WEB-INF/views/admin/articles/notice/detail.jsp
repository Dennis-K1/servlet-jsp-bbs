<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-03-03
  Time: 오후 6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.bbs.command.AdminCommands" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--공지사항 상세 컴포넌트 --%>

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
    <button class="btn btn-primary"
            onclick="location.href=`<%=AdminCommands.NOTICE_EDIT_FORM.getPath()%>?articleId=${article.id}`">
      수정
    </button>
    <button class="btn btn-primary ms-2"
            onclick="deleteArticleById(${article.id})">삭제
    </button>
    <button class="btn btn-primary ms-2"
            onclick="location.href=`<%=AdminCommands.NOTICE_MANAGEMENT.getPath()%>`">
      목록
    </button>
  </div>
</div>
