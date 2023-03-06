<%@ page import="com.bbs.command.AdminCommands" %>
<%@ page import="com.bbs.properties.SessionKeys" %>
<%@ page import="org.apache.commons.io.FileUtils" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-25
  Time: 오후 8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
  <%--이전 페이지에서 뒤로가기 하여 해당 페이지 도착한 경우 새로고침--%>
  window.addEventListener( "pageshow", function ( event ) {
    let historyTraversal = event.persisted ||
        ( typeof window.performance != "undefined" &&
            window.performance.navigation.type === 2 );
    if ( historyTraversal ) {
      // Handle page restore.
      window.location.reload();
    }
  });
</script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/templates/javascript/page-util.js"></script>
<link rel="stylesheet" type="text/css"
      href="<%=request.getContextPath()%>/templates/css/table.css">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
      crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
<style>
  /* cdn import */
  @import url('https://fonts.googleapis.com/css2?family=Gothic A1:wght@300&display=swap');

  /* 폰트 적용 */
  body {
    font-family: 'Gothic A1', sans-serif;
  }
</style>


<%-- 로그인 페이지를 제외한 모든 관리자 페이지에 노출되는 상단 네비 컴포넌트
     Bootstrap css,js 밑 기타 css 경로는 해당 파일에서 통합 관리 --%>
<nav class="navbar navbar-dark" style="background-color: #0c3d9f">
    <div class="container-fluid">
        <a href="<%=AdminCommands.INDEX.getPath()%>"><span
                class="navbar-brand mb-0 h1">관리자 페이지</span></a>
        <div class="d-flex justify-content-end" id="profile">
            <span class="text-light me-5">
            <img style="width: 30px; border-radius: 7px; height: 30px"
                 src="<%=request.getContextPath()%>/templates/img/profile.png">
                <%=session.getAttribute(SessionKeys.LOGIN_ADMIN)%>
            </span>
            <a href="<%=AdminCommands.LOGOUT.getPath()%>"><span
                    class="text-light fs-5">로그아웃</span></a>
        </div>
    </div>
</nav>