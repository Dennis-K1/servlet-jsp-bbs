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
        let startDate = document.getElementById('startDate').value;
        let endDate = document.getElementById('endDate').value;
        let searchCategory = document.getElementById('searchCategory').value;
        let parameterObject = {};
        if (searchKeyword.length > 0) {
          parameterObject.searchKeyword = searchKeyword
        }
        if (startDate != '') {
          parameterObject.startDate = startDate
        }
        if (endDate != '') {
          parameterObject.endDate = endDate
        }
        if (searchCategory != "0") {
          parameterObject.searchCategory = searchCategory
        }
        linkTo(searchPath, parameterObject)
      }
    </script>
</head>
<div id="searchBar" class="d-flex justify-content-end">
    <c:choose>
        <c:when test="${pageParameters.startDate != null}">
           <span class="mt-2"> 기간 : </span><input class="form-control ms-2" style="width: 10%;" type="date" id="startDate"
                        name="startDate"
                        value="${pageParameters.startDate}">
        </c:when>
        <c:otherwise>
           <span class="mt-2"> 기간 : </span><input class="form-control ms-2" style="width: 10%;" type="date" id="startDate"
                        name="startDate">
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${pageParameters.endDate != null}">
            <span class="mt-2 ms-2"> ~ </span><input class="form-control ms-2" style="width: 10%;" type="date" id="endDate" name="endDate"
                   value="${pageParameters.endDate}">
        </c:when>
        <c:otherwise>
            <span class="mt-2 ms-2"> ~ </span><input class="form-control ms-2" style="width: 10%;" type="date" id="endDate" name="endDate">
        </c:otherwise>
    </c:choose>

    <%--    유저 목록 페이지에서 페이지 스코프 변수로 설정한 userManagement 가 참이 아닐 경우에만 카테고리 검색 태그 노출 --%>
    <c:if test="${userManagement != true}">
        <c:choose>
            <c:when test="${pageParameters.searchCategory != null}">
                <select id="searchCategory" name="searchCategory" class="form-select form-select-sm ms-2" style="width: 10%" aria-label=".form-select-sm example">
                    <option value="0" selected>모두 선택</option>
                    <option value="1">제목</option>
                    <option value="2">작성자</option>
                    <option value="3">내용</option>
                    <script>
                      let category = document.getElementById("searchCategory");
                      category.value = "${pageParameters.searchCategory}"
                    </script>
                </select>
            </c:when>
            <c:otherwise>
                <select id="searchCategory" name="searchCategory" class="form-select form-select-sm ms-2" style="width: 10%" aria-label=".form-select-sm example">
                    <option value="0" selected>모두 선택</option>
                    <option value="1">제목</option>
                    <option value="2">작성자</option>
                    <option value="3">내용</option>
                </select>
            </c:otherwise>
        </c:choose>
    </c:if>

    <c:choose>
        <c:when test="${pageParameters.searchKeyword != null}">
            <input class="form-control w-25 ms-2" type="text" id="searchKeyword"
                   value="${pageParameters.searchKeyword}">
        </c:when>
        <c:otherwise>
            <input class="form-control w-25 ms-2" type="text" id="searchKeyword" placeholder="키워드 입력">
        </c:otherwise>
    </c:choose>


    <button class="btn btn-primary ms-2" style="width: 10%" onclick="searchKeyword()">검색</button>
</div>
