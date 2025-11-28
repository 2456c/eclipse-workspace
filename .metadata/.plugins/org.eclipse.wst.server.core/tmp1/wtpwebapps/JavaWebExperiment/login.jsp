<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript" src="js/validate.js"></script>
</head>
<body>
    <div class="container">
        <h2>用户登录</h2>
        <!-- 显示提示信息（错误/注册成功） -->
        <% String msg = (String) request.getAttribute("msg"); %>
        <% if (msg != null) { %>
            <div class="msg error"><%= msg %></div>
        <% } %>

        <!-- 表单：method=post（解决405错误），action=LoginServlet（与Servlet注解一致） -->
        <form action="LoginServlet" method="post" onsubmit="return checkLoginForm()">
            <div class="form-item">
                <label>用户名：</label>
                <input type="text" name="username" id="username" required placeholder="请输入用户名">
            </div>
            <div class="form-item">
                <label>密　码：</label>
                <input type="password" name="password" id="password" required placeholder="请输入密码">
            </div>
            <div class="form-btn">
                <button type="submit">登录</button>
                <a href="register.jsp">还没有账号？去注册</a>
            </div>
        </form>
    </div>
</body>
</html>