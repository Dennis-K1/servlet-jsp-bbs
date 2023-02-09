<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-09
  Time: 오후 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--세션스코프에 error 존재지 alert 후 세션에서 error 삭제--%>
<c:if test="${not empty sessionScope.error }">
  <script>
    alert("${sessionScope.error}");
  </script>
  <c:remove var="error" scope="session"></c:remove>
</c:if>
