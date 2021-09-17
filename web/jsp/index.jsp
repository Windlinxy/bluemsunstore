<%--
  Created by IntelliJ IDEA.
  User: Windlinxy
  Date: 2021/5/9
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
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
            margin-bottom: 15px;
            border: none;
            font-size: 25px;
            border-bottom: 3px solid rgb(48, 2, 2);
        }
        #result {
            width: 800px;
            height: 100px;
            border: 3px solid red;
        }
    </style>
</head>
<body>
<div class="sign,over2">
    <h1>登录</h1>
    <input type="text" placeholder="用户名" id="username">
    <input type="password" placeholder="密码" id="password">
    <input type="button" class="btn2" value="登录">
    <a href="/jsp/register.jsp">没注册？</a>
</div>
<div id="result"></div>
<script>
    const btn = document.getElementsByClassName('btn2')[0];
    const result = document.getElementById("result");
    btn.onclick = function () {
        //1. 创建对象
        const xhr = new XMLHttpRequest();
        //2. 初始化 设置类型与 URL
        xhr.open('POST', 'http://localhost:8080/login');
        //设置请求头
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        //3. 发送
        xhr.send(JSON.stringify({
            username: document.getElementById("username").value,
            password: document.getElementById("password").value,
        }));

        //4. 事件绑定
        xhr.onreadystatechange = function () {
            //判断
            if (xhr.readyState === 4) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    //处理服务端返回的结果
                    var res = JSON.parse(xhr.responseText)
                    result.innerHTML = xhr.response;
                }
            }
        }
    }
</script>
</body>
</html>
