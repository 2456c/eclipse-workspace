<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <style>
        /* 页面容器：整体水平居中，内部左对齐 */
        .login-box {
            width: 400px;
            margin: 50px auto; /* 容器整体居中 */
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        /* 标题居中 */
        .login-box h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 1px solid #eee;
        }

        /* 表单组：内部元素左对齐，用flex排版更整洁 */
        .form-item {
            margin-bottom: 15px;
            text-align: left; /* 内部元素左对齐 */
            display: flex;    /* 标签和输入框同行排列 */
            flex-direction: column; /* 标签在上，输入框在下 */
        }

        /* 标签样式：左对齐，与输入框拉开间距 */
        .form-item label {
            margin-bottom: 5px;
            color: #666;
        }

        /* 输入框样式：左对齐，宽度自适应容器 */
        .form-item input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box; /* 防止输入框溢出容器 */
        }

        /* 操作区域：按钮左对齐，链接居右（可选） */
        .action-bar {
            margin-top: 20px;
            display: flex;
            justify-content: space-between; /* 按钮左，链接右 */
            align-items: center;
        }

        /* 登录按钮样式 */
        .login-btn {
            background: #28a745;
            color: #fff;
            border: none;
            padding: 10px 30px;
            border-radius: 4px;
            cursor: pointer;
        }

        /* 注册链接样式 */
        .register-link {
            color: #007bff;
            text-decoration: none;
        }
    </style>
    <script src="js/validate.js"></script>
</head>
<body>
    <div class="login-box">
        <h1>用户登录</h1>
        <form action="LoginServlet" method="post" onsubmit="return checkLoginForm()">
            <!-- 用户名输入项：标签左对齐，输入框左对齐 -->
            <div class="form-item">
                <label>用户名:</label>
                <input type="text" id="username" name="username" placeholder="请输入用户名">
            </div>
            <!-- 密码输入项 -->
            <div class="form-item">
                <label>密 码:</label>
                <input type="password" id="password" name="password" placeholder="请输入密码">
            </div>
            <!-- 操作按钮 -->
            <div class="action-bar">
                <button type="submit" class="login-btn">登录</button>
                <a href="register.jsp" class="register-link">还没有账号? 去注册</a>
            </div>
        </form>
    </div>
</body>
</html>