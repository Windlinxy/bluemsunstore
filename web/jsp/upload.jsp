<%--
  Created by IntelliJ IDEA.
  User: Windlinxy
  Date: 2021/7/22
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        form {
        position: absolute;
        top: 50%;
        margin-top: -200px;
        left: 50%;
        margin-left: -200px;
        background-color: whitesmoke;
        width: 400px;
        height: 500px;
        padding: 5px 40px;
        box-sizing: border-box;
        }

        input {
        background-color: rgb(246, 248, 246);
        width: 90%;
        height: 50px;
        margin-top: 60px;
        margin-bottom: 15px;
        border: none;
        font-size: 25px;
        border-bottom: 3px solid rgb(48, 2, 2);
        }
    </style>
<title>Title</title>
</head>
<body>
<form enctype="multipart/form-data" action="/upload" method="post">
    <input type="file" name="file">
    <input type="submit" value="提交">
</form>
</body>
</html>
