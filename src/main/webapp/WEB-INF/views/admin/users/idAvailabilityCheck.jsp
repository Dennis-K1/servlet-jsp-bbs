<%--
  Created by IntelliJ IDEA.
  User: bw120
  Date: 2023-03-02
  Time: 오후 3:17
  To change this template use File | Settings | File Templates.
--%>
<%@page import="org.json.simple.JSONObject"%>

<%-- 등록form에서 AJAX 요청한 아이디 중복여부를
    idAvailabilityCheck 커맨드에서 확인후 Attribute로 전달하고,
    해당 JSP에서 Json 객체에 담아 AJAX 반환 --%>
<%
  JSONObject jsonObject = new JSONObject();
  jsonObject.put("idAvailability", request.getAttribute("idAvailability"));
  response.setContentType("application/json");
%>
<%=jsonObject.toJSONString()%>
