<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改用户名</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript" src="js/validate.js"></script>
</head>
<body>
    <div class="container">
        <!-- 显示当前登录用户 -->
        <div class="user-info">
            当前登录：${loginUser.username}（${loginUser.name}）
        </div>

        <h2>修改用户名</h2>
        <!-- 显示错误提示 -->
        <% String msg = (String) request.getAttribute("msg"); %>
        <% if (msg != null) { %>
            <div class="msg error"><%= msg %></div>
        <% } %>

        <!-- 表单：method=post，action=UpdateUsernameServlet -->
        <form action="UpdateUsernameServlet" method="post" onsubmit="return checkUsernameForm()">
            <div class="form-item">
                <label>当前用户名：</label>
                <input type="text" value="${loginUser.username}" disabled> <!-- 不可修改 -->
            </div>
            <div class="form-item">
                <label>新用户名：</label>
                <input type="text" name="newUsername" id="newUsername" required placeholder="≥8字符，含2字母、2数字、1大小写">
            </div>
            <div class="form-btn">
                <button type="submit">确认修改</button>
                <a href="success.jsp">返回首页</a>
            </div>
        </form>
    </div>
</body>
</html>
