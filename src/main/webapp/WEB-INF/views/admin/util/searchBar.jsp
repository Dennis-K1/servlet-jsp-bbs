<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-24
  Time: 오후 4:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <script>
    let searchPath = window.location.pathname
    const searchKeyword = () => {
      let searchKeyword = document.getElementById('searchKeyword').value;
      let parameterObject = {};
      if (searchKeyword.length > 0) {
        parameterObject.searchKeyword = searchKeyword
      }
      linkTo(searchPath, parameterObject)
    }
  </script>
</head>
<div id="searchBar" class="d-flex justify-content-end">
  <c:choose>
    <c:when test="${pageParameters.searchKeyword != null}">
      <input class="form-control w-25" type="text" id="searchKeyword"
             value="${pageParameters.searchKeyword}">
    </c:when>
    <c:otherwise>
      <input class="form-control w-25" type="text" id="searchKeyword" placeholder="키워드 입력">
    </c:otherwise>
  </c:choose>
  <button class="btn btn-primary ms-2" style="width: 10%" onclick="searchKeyword()">검색</button>
</div>
