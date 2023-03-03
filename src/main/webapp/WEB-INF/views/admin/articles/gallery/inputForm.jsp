<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-03-03
  Time: 오후 7:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bbs.command.AdminCommands" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--갤러리 등록 컴포넌트 --%>

<div class="fs-3 fw-bold mt-4 text-secondary">
    갤러리 등록
</div>

<div class="card bg-white p-4 mt-3">
    <form action="<%=AdminCommands.GALLERY_INPUT.getPath()%>" method="post"
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
                <td class="w-25"><img id="preview" style="max-width: 200px; max-height: 200px"></td>
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


