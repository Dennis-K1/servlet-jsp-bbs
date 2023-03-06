<%@ page import="com.bbs.properties.JspComponents" %>
<%@ page import="com.bbs.command.AdminCommands" %><%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-02-28
  Time: 오후 2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 회원 등록 form--%>
<html>
<head>
    <title>회원 등록</title>
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
        <div class="col" style="background-color: #f5f5f5">

            <div class="fs-3 fw-bold mt-4 text-secondary">
                회원 등록
            </div>
            <div class="card bg-white p-4 mt-3">
                <form name="registerForm" action="<%=AdminCommands.USER_REGISTER.getPath()%>"
                      method="post" onsubmit="return validateRegisterForm();">
                    <table class="mt-3 table table-borderless">
                        <tr>
                            <th class="font-weight-bold text-primary" style="width: 15%;">아이디</th>
                            <td class="w-25"><input class="form-control" type="text" name="account"
                                                    id="account"
                                                    autocomplete="off"></td>
                            <td style="width: 15%">
                                <button type="button" id="IdAvailabilityCheck"
                                        class="btn btn-primary">중복 검사
                                </button>
                            </td>
                            <td class="text-justify pt-3"><span id="idAvailabilityMessage"></span>
                            </td>
                        </tr>
                        <tr>
                            <th class="font-weight-bold text-primary" style="width: 15%;">비밀번호</th>
                            <td class="w-25"><input class="form-control" type="password"
                                                    name="password"
                                                    id="password" autocomplete="off"></td>
                        </tr>
                        <tr>
                            <th class="font-weight-bold text-primary" style="width: 15%;">비밀번호 확인</th>
                            <td class="w-25"><input class="form-control" type="password"
                                                    name="passwordValidation"
                                                    id="passwordValidation" autocomplete="off"></td>
                        </tr>
                    </table>
                    <button type="submit" class="btn btn-primary"
                            style="width: 10%">등록
                    </button>
                </form>
            </div>
        </div>
</body>


<%--AJAX를 활용한 아이디 중복 검사--%>
<script>
  window.onload = function () {
    let httpRequest;
    document.getElementById("IdAvailabilityCheck").addEventListener('click', () => {
      let account = document.getElementById("account").value;
      let messageElement = document.getElementById("idAvailabilityMessage");

      if (account === '') {
        messageElement.innerHTML = "아이디를 입력해주세요."
        messageElement.classList.remove(...messageElement.classList);
        messageElement.classList.add("text-danger")
        return;
      }

      httpRequest = new XMLHttpRequest();
      httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
          if (httpRequest.status === 200) {
            let result = httpRequest.response;

            if (result.idAvailability) {
              messageElement.innerHTML = "사용할 수 있는 아이디입니다."
              messageElement.classList.remove(...messageElement.classList);
              messageElement.classList.toggle("text-primary")
            } else {
              messageElement.innerHTML = "이미 사용중인 아이디입니다."
              messageElement.classList.remove(...messageElement.classList);
              messageElement.classList.toggle("text-danger")
            }
          }
        }
      };
      httpRequest.open('GET',
          `<%=AdminCommands.USER_ID_AVAILABILITY_CHECK.getPath()%>?account=\${account}`);
      httpRequest.responseType = "json";
      httpRequest.send();
    });
  }

  const validateRegisterForm = () => {
    let registerForm = document.forms['registerForm'];
    let account = registerForm['account'];
    let password = registerForm['password'];
    let passwordValidation = registerForm['passwordValidation'];

    //아이디
    if (account.value === "") {
      alert("아이디를 입력해주세요.")
      account.focus();
      return false;
    }
    if (account.value.length < 3 || account.value.length > 10) {
      alert("아이디를 3글자 이상 10글자 미만으로 입력해주세요.")
      account.focus();
      return false;
    }

    //비밀번호
    if (password.value === "") {
      alert("비밀번호를 입력해주세요.")
      password.focus();
      return false;
    }
    if (password.value.length < 4 || password.value.length > 16) {
      alert("비밀번호는 특수문자( '!,@,$,%,^,&,*' 만 허용), 영문 대소문자, 숫자를 포함하여 " +
          "4글자 이상 16글자 미만으로 입력해주세요.")
      password.focus();
      return false;
    }
    if (!password.value.match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/)) {
      alert("비밀번호는 특수문자( '!,@,$,%,^,&,*' 만 허용), 영문 대소문자, 숫자를 포함하여 " +
          "4글자 이상 16글자 미만으로 입력해주세요.")
      password.focus();
      return false;
    }
    if (password.value !== passwordValidation.value) {
      alert("비밀번호 확인란에 동일한 비밀번호를 입력해주세요.")
      passwordValidation.focus();
      return false;
    }
  }
</script>
</html>

