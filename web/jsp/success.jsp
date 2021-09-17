<%--
  Created by IntelliJ IDEA.
  User: Windlinxy
  Date: 2021/5/9
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
    <style>
        h1{
            margin-top: 20%;
            text-align: center;
            color: #f30707;
            font-size:50px;
        }
    </style>

</head>

<body>
    <h1>登录成功</h1>
    <p><%=request.getParameter("username")%></p>
    <p><%=request.getParameter("password")%></p>
    <br>
    <p>您可以访问：</p>
    <a href="/jsp/upload.jsp">文件上传</a>
</body>
</html>
