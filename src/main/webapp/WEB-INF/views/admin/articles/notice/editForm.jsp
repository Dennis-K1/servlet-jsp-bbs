<%@ page import="com.bbs.command.AdminCommands" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-03-03
  Time: 오후 7:20
  To change this template use File | Settings | File Templates.
--%>


<%--공지사항 수정 컴포넌트 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="fs-3 fw-bold mt-4 text-secondary">
    공지사항 수정
</div>

<div class="card bg-white p-4 mt-3">
    <form name="articleForm" action="<%=AdminCommands.ARTICLE_EDIT.getPath()%>" method="post"
          enctype="multipart/form-data">
        <table class="d-flex mt-3 table table-borderless">
            <input type="hidden" name="articleId" id="articleId" value="${article.id}">
            <tr>
                <th class="font-weight-bold text-primary" style="width: 15%;">
                    제목
                </th>
                <td class="w-25"><input class="form-control" type="text" name="title" id="title"
                                        autocomplete="off"
                                        value="${article.title}">
                </td>
            </tr>
            <tr>
                <th class="font-weight-bold text-primary" style="width: 15%;">
                    내용
                </th>
                <td class="w-25"><textarea class="form-control" name="content" id="content"
                                           autocomplete="off">${article.content}</textarea></td>
            </tr>

            <c:choose>
                <c:when test="${article.image != null}">
                    <tr id="uploadedImageDiv">
                        <th class="font-weight-bold text-primary" style="width: 15%;">
                            이미지
                        </th>
                        <td class="w-25">
                            <img id="uploadedImage" src="data:image/jpg;base64,${article.image}"
                                 width="50%" height="50%"/>
                            <button class="btn btn-primary" type="button"
                                    id="removeUploadedImageButton"
                                    onclick="removeUploadedImage()">이미지 삭제
                            </button>
                            <input type="hidden" name="fileStatus" value="previous">
                        </td>
                    </tr>
                    <tr id="uploadImage" style="display:none;">
                        <th class="font-weight-bold text-primary" style="width: 38%;">
                            이미지 업로드
                        </th>
                        <td class="w-25"><input class="form-control" type="file"
                                                name="file" id="file"
                                                accept=".jpg, .jpeg, .png"
                                                onchange="setImagePreview(event)"></td>
                        <td class="w-25"><img id="preview" style="max-width: 200px; max-height: 200px"></td>
                        <td>
                            <button class="btn btn-primary" type="button"
                                    id="removePreviewButton"
                                    onclick="removePreview()" style="display: none">이미지 삭제
                            </button>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <input type="hidden" name="fileStatus" value="new">
                        <th class="font-weight-bold text-primary" style="width: 15%;">
                            이미지 업로드
                        </th>
                        <td class="w-25"><input class="form-control" type="file"
                                                name="file" id="file"
                                                accept=".jpg, .jpeg, .png"
                                                onchange="setImagePreview(event)"></td>
                        <td class="w-25"><img id="preview" style="max-width: 200px; max-height: 200px"></td>
                        <td>
                            <button class="btn btn-primary" type="button"
                                    id="removePreviewButton"
                                    onclick="removePreview()" style="display: none">삭제
                            </button>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
        <button class="btn btn-primary" type="submit" style="width: 10%">수정하기</button>
    </form>
</div>

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
