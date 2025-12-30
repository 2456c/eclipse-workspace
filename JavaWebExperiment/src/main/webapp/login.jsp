<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>益禾堂EMS - 系统登录</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css?v=20251229">
    <script>
        function validateLogin() {
            var u = document.getElementsByName("username")[0].value;
            var p = document.getElementsByName("password")[0].value;
            if(u.trim() === "" || p.trim() === "") {
                alert("用户名和密码不能为空");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="login-box">
        <h2>益禾堂企业管理系统</h2>
        <p class="error">${errorMessage}</p>
        <form action="LoginServlet" method="post" onsubmit="return validateLogin()">
            <input type="text" name="username" placeholder="请输入账号" required><br>
            <input type="password" name="password" placeholder="请输入密码" required><br>
            <button type="submit">登 录</button>
        </form>
        <div class="footer">
            ©2025 益禾堂企业信息管理系统 | 学生课程项目
        </div>
    </div>
</body>
</html>
