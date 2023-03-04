<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-24
  Time: 오후 4:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 각 목록에 표시되는 페지네이션 공통 컴포넌트 --%>
<div id="pagination" class="d-flex justify-content-center">
  <c:if test="${pageParameters.pageNumber <= fn:length(pageParameters.displayedPageNumbers)}">
    <button onclick="toPageOf(${pageParameters.startPage})" class="pageButton">
      &lt;&lt;
    </button>
    <c:choose>
      <c:when test="${pageParameters.pageNumber == pageParameters.startPage}">
        <button class="pageButton">&lt;</button>
      </c:when>
      <c:otherwise>
        <button onclick="toPageOf(${pageParameters.pageNumber - 1})"
                class="pageButton">
          &lt;
        </button>
      </c:otherwise>
    </c:choose>
    <c:forEach items="${pageParameters.displayedPageNumbers}" var="page">
      <c:choose>
        <c:when test="${page == pageParameters.pageNumber}">
          <button class="pageButtonClicked">${page}</button>
        </c:when>
        <c:otherwise>
          <button onclick="toPageOf(${page})"
                  class="pageButton">${page}</button>
        </c:otherwise>
      </c:choose>
    </c:forEach>
    <c:choose>
      <c:when test="${pageParameters.pageNumber == pageParameters.endPage}">
        <button class="pageButton">&gt;</button>
      </c:when>
      <c:otherwise>
        <button onclick="toPageOf(${pageParameters.pageNumber + 1})"
                class="pageButton">
          &gt;
        </button>
      </c:otherwise>
    </c:choose>
    <button onclick="toPageOf(${pageParameters.endPage})" class="pageButton">
      &gt;&gt;
    </button>
  </c:if>
</div>

<script>
  let paginationPath = window.location.pathname
  const toPageOf = (pageNumber) => {
    let parameterObject = {'pageNumber': pageNumber}
    let searchKeyword = `${pageParameters.searchKeyword}`
    if (searchKeyword.length > 0) {
      parameterObject.searchKeyword = searchKeyword
    }
    linkTo(paginationPath, parameterObject)
  }
</script>
