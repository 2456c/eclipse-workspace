<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <script>
        // 前端验证密码格式
        function validateForm() {
            let oldPwd = document.getElementById("oldPwd").value.trim();
            let newPwd = document.getElementById("newPwd").value.trim();
            let confirmPwd = document.getElementById("confirmPwd").value.trim();
            
            if (oldPwd === "") { alert("旧密码不能为空！"); return false; }
            if (newPwd === "") { alert("新密码不能为空！"); return false; }
            if (confirmPwd === "") { alert("确认密码不能为空！"); return false; }
            
            if (newPwd !== confirmPwd) {
                alert("新密码与确认密码不一致！");
                return false;
            }
            
            if (!checkFormat(newPwd)) {
                alert("新密码格式错误！需至少8位，含2个字母、2个数字、1个大写、1个小写");
                return false;
            }
            return confirm("确认修改密码吗？");
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
        <h2 class="title">修改密码</h2>
        <% String msg = (String) request.getAttribute("msg");
           if (msg != null) { %>
               <div class="msg <%= msg.contains("成功") ? "success" : "error" %>">
                   <%= msg %>
               </div>
        <% } %>
        
        <form action="updatePassword" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="oldPwd">旧密码：</label>
                <input type="password" id="oldPwd" name="oldPwd" placeholder="请输入旧密码">
            </div>
            <div class="form-group">
                <label for="newPwd">新密码：</label>
                <input type="password" id="newPwd" name="newPwd" placeholder="请输入新密码">
            </div>
            <div class="form-group">
                <label for="confirmPwd">确认新密码：</label>
                <input type="password" id="confirmPwd" name="confirmPwd" placeholder="请再次输入新密码">
            </div>
            <button type="submit" class="btn">提交修改</button>
            <div class="link" style="margin-top: 15px;">
                <a href="<%=request.getContextPath()%>/index.jsp">返回首页</a>
            </div>
        </form>
    </div>
</body>
</html>
