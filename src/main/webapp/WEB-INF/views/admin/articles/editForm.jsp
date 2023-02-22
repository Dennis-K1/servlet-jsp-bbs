<%@ page import="com.bbs.command.AdminCommands" %>
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
</head>
<body>
<jsp:include page="../sideMenu.jsp"></jsp:include>
<c:choose>

    <c:when test="${article.boardId == 1}">

        <form name="articleForm" action="<%=AdminCommands.ARTICLE_EDIT.getPath()%>" method="post"
              enctype="multipart/form-data">

            <h1>게시글 수정 페이지</h1>


            <input type="hidden" name="articleId" id="articleId" value="${article.id}">


            <label for="title">제목 : </label>
            <input type="text" name="title" id="title" autocomplete="off"
                   value="${article.title}">


            <label for="content">내용 : </label>
            <textarea name="content" id="content"
                      autocomplete="off">${article.content}</textarea>


            </div>
            <c:choose>
                <c:when test="${article.image != null}">
                    <div id="uploadedImageDiv">
                        <img id="uploadedImage" src="data:image/jpg;base64,${article.image}"
                             width="240" height="300"/>
                        <button type="button" id="removeUploadedImageButton"
                                onclick="removeUploadedImage()">이미지 삭제
                        </button>
                        <input type="hidden" name="fileStatus" value="previous">
                    </div>
                    <div id="uploadImage" style="display:none;">
                        <label for="file">파일 업로드</label>
                        <input type="file" name="file" id="file" accept=".jpg, .jpeg, .png"
                               onchange="setImagePreview(event)">
                        <img id="preview">
                        <button type="button" id="removePreviewButton" onclick="removePreview()"
                                style="display: none">삭제
                        </button>
                    </div>
                </c:when>
                <c:otherwise>
                    <label for="file">파일 업로드2</label>
                    <input type="file" name="file" id="file" accept=".jpg, .jpeg, .png"
                           onchange="setImagePreview(event)">
                    <input type="hidden" name="fileStatus" value="new">
                    <img id="preview">
                    <button type="button" id="removePreviewButton" onclick="removePreview()"
                            style="display: none">삭제
                    </button>
                </c:otherwise>
            </c:choose>

            <button type="submit">수정하기</button>
        </form>
    </c:when>
    <c:otherwise>
        nothing
    </c:otherwise>
</c:choose>

<button>뒤로가기</button>
</body>
<script>

  const removeUploadedImage = () => {
    let uploadedImageDiv = document.getElementById("uploadedImageDiv");
    let uploadImage = document.getElementById("uploadImage");
    uploadedImageDiv.remove();
    uploadImage.style.display = "block";
    let input = document.createElement("input")
    input.setAttribute("type", "hidden");
    input.setAttribute('name', "fileStatus");
    input.setAttribute("value", "new");
    uploadImage.appendChild(input);
  }

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