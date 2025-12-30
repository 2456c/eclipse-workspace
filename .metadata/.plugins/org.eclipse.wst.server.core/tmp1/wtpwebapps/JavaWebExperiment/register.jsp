<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" href="css/style.css">
    <script>
        // 页面加载完成后绑定事件
        window.onload = function() {
            // 绑定输入框的blur事件，实现实时验证
            document.getElementById("username").onblur = function() {
                validateUsername();
            };
            document.getElementById("password").onblur = function() {
                validatePassword();
            };
            document.getElementById("name").onblur = function() {
                validateName();
            };
            document.getElementById("studentId").onblur = function() {
                validateStudentId();
            };
        };

        // 前端验证所有字段
        function validateForm() {
            let isValid = true;
            
            // 验证每个字段
            if (!validateUsername()) isValid = false;
            if (!validatePassword()) isValid = false;
            if (!validateName()) isValid = false;
            if (!validateStudentId()) isValid = false;
            
            return isValid;
        }

        // 验证用户名
        function validateUsername() {
            let username = document.getElementById("username").value.trim();
            let errorElement = document.getElementById("usernameError");
            
            if (username === "") {
                showError("username", "用户名不能为空");
                return false;
            }
            
            if (!checkFormat(username)) {
                showError("username", "用户名格式错误！需至少8位，含2个字母、2个数字、1个大写、1个小写");
                return false;
            }
            
            hideError("username");
            return true;
        }

        // 验证密码
        function validatePassword() {
            let password = document.getElementById("password").value.trim();
            let errorElement = document.getElementById("passwordError");
            
            if (password === "") {
                showError("password", "密码不能为空");
                return false;
            }
            
            if (!checkFormat(password)) {
                showError("password", "密码格式错误！需至少8位，含2个字母、2个数字、1个大写、1个小写");
                return false;
            }
            
            hideError("password");
            return true;
        }

        // 验证姓名
        function validateName() {
            let name = document.getElementById("name").value.trim();
            let errorElement = document.getElementById("nameError");
            
            if (name === "") {
                showError("name", "姓名不能为空");
                return false;
            }
            
            hideError("name");
            return true;
        }

        // 验证学号
        function validateStudentId() {
            let studentId = document.getElementById("studentId").value.trim();
            let errorElement = document.getElementById("studentIdError");
            
            if (studentId === "") {
                showError("studentId", "学号不能为空");
                return false;
            }
            
            hideError("studentId");
            return true;
        }

        // 显示错误信息
        function showError(fieldId, message) {
            let inputElement = document.getElementById(fieldId);
            let errorElement = document.getElementById(fieldId + "Error");
            
            inputElement.classList.add("error-input");
            errorElement.textContent = message;
            errorElement.style.display = "block";
            errorElement.style.color = "#f56c6c"; // 显式设置错误信息颜色为红色
        }

        // 隐藏错误信息
        function hideError(fieldId) {
            let inputElement = document.getElementById(fieldId);
            let errorElement = document.getElementById(fieldId + "Error");
            
            inputElement.classList.remove("error-input");
            errorElement.style.display = "none";
        }

        // 格式验证方法（与登录页一致）
        function checkFormat(str) {
            if (str.length < 8) return false;
            let lower = 0, upper = 0, digit = 0;
            for (let i = 0; i < str.length; i++) {
                let c = str.charAt(i);
                if (c >= 'a' && c <= 'z') lower++;
                else if (c >= 'A' && c <= 'Z') upper++;
                else if (c >= '0' && c <= '9') digit++;
            }
            return (lower + upper) >= 2 && digit >= 2 && upper >= 1 && lower >= 1;
        }
    </script>
</head>
<body>
    <div class="container">
        <h2 class="title">用户注册</h2>
        <%-- 提示信息 --%>
        <% String msg = (String) request.getAttribute("msg");
           if (msg != null) { %>
               <div class="msg <%= msg.contains("成功") ? "success" : "error" %>">
                   <%= msg %>
               </div>
        <% } %>
        
        <%-- 注册表单（回显用户输入） --%>
        <form action="register" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="username">用户名：</label>
                <input type="text" id="username" name="username" 
                       value="<%= request.getAttribute("username") == null ? "" : request.getAttribute("username") %>"
                       placeholder="≥8字符，含2字母、2数字、1大小写">
                <div id="usernameError" class="error-message"></div>
            </div>
            <div class="form-group">
                <label for="password">密码：</label>
                <input type="password" id="password" name="password" placeholder="≥8字符，含2字母、2数字、1大小写">
                <div id="passwordError" class="error-message"></div>
            </div>
            <div class="form-group">
                <label for="name">姓名：</label>
                <input type="text" id="name" name="name" 
                       value="<%= request.getAttribute("name") == null ? "" : request.getAttribute("name") %>"
                       placeholder="请输入真实姓名">
                <div id="nameError" class="error-message"></div>
            </div>
            <div class="form-group">
                <label for="studentId">学号：</label>
                <input type="text" id="studentId" name="studentId" 
                       value="<%= request.getAttribute("studentId") == null ? "" : request.getAttribute("studentId") %>"
                       placeholder="请输入学号">
                <div id="studentIdError" class="error-message"></div>
            </div>
            <button type="submit" class="btn">注册</button>
            <div class="link">
                已有账号？<a href="login">立即登录</a>
            </div>
        </form>
    </div>
</body>
</html>