<%--
  Created by IntelliJ IDEA.
  User: 白驹
  Date: 2018/11/12
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>无权访问</title>
</head>
<body>
抱歉，您无权访问此页面!
<%--<a href="<%=basePath%>page/login.jsp" style="font-size:16px; color:red; " >点此登陆</a>--%>
</body>
</html>
