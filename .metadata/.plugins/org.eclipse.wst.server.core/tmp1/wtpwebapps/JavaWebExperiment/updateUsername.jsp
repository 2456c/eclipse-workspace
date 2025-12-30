<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户名</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <script>
        // 前端验证新用户名格式
        function validateForm() {
            let newUsername = document.getElementById("newUsername").value.trim();
            if (newUsername === "") {
                alert("新用户名不能为空！");
                return false;
            }
            // 格式验证（与注册页一致）
            if (!checkFormat(newUsername)) {
                alert("新用户名格式错误！需至少8位，含2个字母、2个数字、1个大写、1个小写");
                return false;
            }
            return confirm("确认修改用户名吗？修改后需重新登录！");
        }

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
    <%-- 头部：显示当前登录用户 --%>
    <div class="header">
        <div class="user-info">
            当前登录：${loginUser.username}（姓名：${loginUser.name}）
        </div>
        <div class="logout">
            <a href="login">退出登录</a>
        </div>
        <div style="clear: both;"></div>
    </div>

    <%-- 修改表单 --%>
    <div class="update-container">
        <h2 class="title">修改用户名</h2>
        <% String msg = (String) request.getAttribute("msg");
           if (msg != null) { %>
               <div class="msg <%= msg.contains("成功") ? "success" : "error" %>">
                   <%= msg %>
               </div>
        <% } %>
        
        <form action="updateUsername" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <label>原用户名：</label>
                <input type="text" value="${loginUser.username}" readonly style="background-color: #f5f5f5;">
            </div>
            <div class="form-group">
                <label for="newUsername">新用户名：</label>
                <input type="text" id="newUsername" name="newUsername" placeholder="请输入新用户名">
            </div>
            <button type="submit" class="btn">提交修改</button>
            <div class="link" style="margin-top: 15px;">
                <a href="<%=request.getContextPath()%>/index.jsp">返回首页</a>
            </div>
        </form>
    </div>
</body>
</html>
