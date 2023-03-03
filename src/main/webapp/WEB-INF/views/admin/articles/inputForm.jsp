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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="<%=JspComponents.ERROR_MESSAGE.getPath()%>"/>

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
        <div class="col py-3" style="background-color: #f5f5f5">
            <c:choose>
                <c:when test="${boardId == 1}">
                    <div class="fs-3 fw-bold mt-4 text-secondary">
                        공지사항 등록
                    </div>
                    <div class="card bg-white p-4 mt-3">
                        <form action="<%=AdminCommands.NOTICE_INPUT.getPath()%>" method="post"
                              enctype="multipart/form-data">
                            <table class="mt-3 table table-borderless">
                                <tr>
                                    <th class="font-weight-bold text-primary" style="width: 15%;">
                                        제목
                                    </th>
                                    <td class="w-25"><input class="form-control" type="text"
                                                            name="title" id="title"
                                                            autocomplete="off">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="font-weight-bold text-primary" style="width: 15%;">
                                        내용
                                    </th>
                                    <td class="w-25"><textarea class="form-control" name="content"
                                                               id="content"
                                                               autocomplete="off"></textarea></td>
                                </tr>
                                <tr>
                                    <th class="font-weight-bold text-primary" style="width: 15%;">
                                        이미지 업로드
                                    </th>
                                    <td class="w-25"><input class="form-control" type="file"
                                                            name="file" id="file"
                                                            accept=".jpg, .jpeg, .png"
                                                            onchange="setImagePreview(event)"></td>
                                    <td class="w-25"><img id="preview"></td>
                                    <td>
                                        <button class="btn btn-primary" type="button"
                                                id="removePreviewButton"
                                                onclick="removePreview()" style="display: none">삭제
                                        </button>
                                    </td>
                                </tr>
                            </table>
                            <button type="submit" class="btn btn-primary"
                                    style="width: 10%">등록
                            </button>
                        </form>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>

</body>
<script>
  const removePreview = () => {
    let preview = document.getElementById("preview");
    let removePreview = document.getElementById("removePreviewButton");
    let file = document.getElementById("file");
    file.value = "";
    delete preview.src;
    preview.style.display = "none";
    removePreview.style.display = "none";
  }

  const setImagePreview = (event) => {
    let preview = document.getElementById("preview");
    let removePreviewButton = document.getElementById("removePreviewButton");
    if (event.target.files.length > 0) {
      let src = URL.createObjectURL(event.target.files[0]);
      preview.src = src;
      preview.style.display = "block";
      removePreviewButton.style.display = "block";
    } else {
      removePreview();
    }
  }
</script>
</html>
