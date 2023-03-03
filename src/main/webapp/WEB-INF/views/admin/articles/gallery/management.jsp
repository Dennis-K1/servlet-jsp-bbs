<%@ page import="com.bbs.command.AdminCommands" %>
<%@ page import="com.bbs.properties.JspComponents" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-03-03
  Time: 오후 6:22
  To change this template use File | Settings | File Templates.
--%>


<%-- 갤러리 관리 목록 페이지 컴포넌트 --%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="fs-3 fw-bold mt-4 text-secondary">
    갤러리 관리
</div>
<div class="card bg-white p-4 mt-3" id="component">
    <jsp:include page="<%=JspComponents.SEARCH_BAR.getPath()%>"/>
    <div class="viewButtons">
        <button class="btn btn-primary m-2">리스트</button>
        <button class="btn btn-primary m-2">갤러리</button>
        <button class="btn btn-primary m-2">웹진</button>
    </div>
    <div id="listViews">
        <div id="simpleList">
            <jsp:include page="<%=JspComponents.ADMIN_GALLERY_MANAGEMENT_SIMPLE.getPath()%>"/>
        </div>
        <div id="cardList" style="display: none">
            <jsp:include page="<%=JspComponents.ADMIN_GALLERY_MANAGEMENT_CARD.getPath()%>"/>
        </div>
        <div id="imageList" style="display: none">
            <jsp:include page="<%=JspComponents.ADMIN_GALLERY_MANAGEMENT_IMAGE.getPath()%>"/>
        </div>
        <jsp:include page="<%=JspComponents.PAGINATION.getPath()%>"/>
    </div>
    <button class="btn btn-primary" style="width: 10%"
            onclick="location.href=`<%=AdminCommands.GALLERY_INPUT_FORM.getPath()%>`">
        등록
    </button>
</div>
<script>

  const btnGroup = document.querySelector('.viewButtons');
  const buttons = btnGroup.querySelectorAll('button');
  const divs = document.querySelectorAll('#listViews > div');

  buttons.forEach((button, index) => {
    button.addEventListener('click', () => {
      divs.forEach((div, divIndex) => {
        div.style.display = divIndex === index ? 'block' : 'none';
      });
    });
  });
</script>

