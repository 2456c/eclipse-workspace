<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <style>
        /* 页面容器：整体居中，内部左对齐 */
        .register-box {
            width: 400px;
            margin: 50px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        /* 标题居中 */
        .register-box h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 1px solid #eee;
        }

        /* 表单组：内部左对齐，flex排版 */
        .form-item {
            margin-bottom: 15px;
            text-align: left;
            display: flex;
            flex-direction: column;
        }

        /* 标签样式：左对齐 */
        .form-item label {
            margin-bottom: 5px;
            color: #666;
        }

        /* 输入框样式：左对齐，宽度自适应 */
        .form-item input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        /* 操作区域：按钮左，链接右 */
        .action-bar {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        /* 注册按钮样式 */
        .register-btn {
            background: #28a745;
            color: #fff;
            border: none;
            padding: 10px 30px;
            border-radius: 4px;
            cursor: pointer;
        }

        /* 登录链接样式 */
        .login-link {
            color: #007bff;
            text-decoration: none;
        }
    </style>
    <script src="js/validate.js"></script>
</head>
<body>
    <div class="register-box">
        <h1>用户注册</h1>
        <form action="RegisterServlet" method="post" onsubmit="return checkRegisterForm()">
            <div class="form-item">
                <label>用户名:</label>
                <input type="text" id="username" name="username" placeholder="≥8字符，含2字母、2数字、1大小写">
            </div>
            <div class="form-item">
                <label>密 码:</label>
                <input type="password" id="password" name="password" placeholder="格式同用户名">
            </div>
            <div class="form-item">
                <label>姓 名:</label>
                <input type="text" id="name" name="name" placeholder="请输入真实姓名">
            </div>
            <div class="form-item">
                <label>学 号:</label>
                <input type="text" id="studentId" name="studentId" placeholder="请输入学号">
            </div>
            <div class="action-bar">
                <button type="submit" class="register-btn">注册</button>
                <a href="login.jsp" class="login-link">已有账号? 去登录</a>
            </div>
        </form>
    </div>
</body>
</html>