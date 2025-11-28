<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript" src="js/validate.js"></script>
</head>
<body>
    <div class="container">
        <!-- 显示当前登录用户 -->
        <div class="user-info">
            当前登录：${loginUser.username}（${loginUser.name}）
        </div>

        <h2>修改密码</h2>
        <!-- 显示错误提示 -->
        <% String msg = (String) request.getAttribute("msg"); %>
        <% if (msg != null) { %>
            <div class="msg error"><%= msg %></div>
        <% } %>

        <!-- 表单：method=post，action=UpdatePasswordServlet -->
        <form action="UpdatePasswordServlet" method="post" onsubmit="return checkPasswordForm()">
            <div class="form-item">
                <label>旧密码：</label>
                <input type="password" name="oldPwd" id="oldPwd" required placeholder="请输入旧密码">
            </div>
            <div class="form-item">
                <label>新密码：</label>
                <input type="password" name="newPwd" id="newPwd" required placeholder="≥8字符，含2字母、2数字、1大小写">
            </div>
            <div class="form-item">
                <label>确认新密码：</label>
                <input type="password" name="confirmPwd" id="confirmPwd" required placeholder="请再次输入新密码">
            </div>
            <div class="form-btn">
                <button type="submit">确认修改</button>
                <a href="success.jsp">返回首页</a>
            </div>
        </form>
    </div>
</body>
</html>
