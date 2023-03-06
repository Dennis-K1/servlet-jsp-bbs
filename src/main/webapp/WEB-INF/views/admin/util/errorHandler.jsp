<%@ page import="com.bbs.command.AdminCommands" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-09
  Time: 오후 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
<%--백엔드에서 error 메세지를 보냈을 경우 alert 후 전 페이지로 이동
    redirectURL로 접근시 인덱스로--%>
<c:if test="${not empty requestScope.error }">
    alert("${requestScope.error}");
    <c:remove var="error" scope="request"/>
    if (window.location.pathname === `<%=AdminCommands.LOGIN.getPath()%>`) {
      window.location.replace(`<%=AdminCommands.LOGIN_FORM.getPath()%>`);
    }
    window.location.replace(`<%=AdminCommands.INDEX.getPath()%>`);
    if (window.location.search.startsWith('?redirectURL')) {
      window.location.replace(`<%=AdminCommands.INDEX.getPath()%>`);
    }
    history.back()
    window.location.replace(window.location.pathname);
</c:if>
</script>
