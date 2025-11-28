<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript" src="js/validate.js"></script>
</head>
<body>
    <div class="container">
        <h2>用户注册</h2>
        <!-- 显示错误提示 -->
        <% String msg = (String) request.getAttribute("msg"); %>
        <% if (msg != null) { %>
            <div class="msg error"><%= msg %></div>
        <% } %>

        <!-- 表单：method=post，action=RegisterServlet -->
        <form action="RegisterServlet" method="post" onsubmit="return checkRegisterForm()">
            <div class="form-item">
                <label>用户名：</label>
                <input type="text" name="username" id="username" 
                       value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>" 
                       required placeholder="≥8字符，含2字母、2数字、1大小写">
            </div>
            <div class="form-item">
                <label>密　码：</label>
                <input type="password" name="password" id="password" required placeholder="格式同用户名">
            </div>
            <div class="form-item">
                <label>姓　名：</label>
                <input type="text" name="name" id="name" 
                       value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>" 
                       required placeholder="请输入真实姓名">
            </div>
            <div class="form-item">
                <label>学　号：</label>
                <input type="text" name="studentId" id="studentId" 
                       value="<%= request.getAttribute("studentId") != null ? request.getAttribute("studentId") : "" %>" 
                       required placeholder="请输入学号">
            </div>
            <div class="form-btn">
                <button type="submit">注册</button>
                <a href="login.jsp">已有账号？去登录</a>
            </div>
        </form>
    </div>
</body>
</html>