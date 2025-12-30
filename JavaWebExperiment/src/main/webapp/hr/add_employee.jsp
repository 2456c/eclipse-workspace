<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加员工</title>
    <link rel="stylesheet" href="../css/style.css">
    <script>
         // 复用注册页的验证逻辑
         // ... (simplified for brevity, assume HR knows what they are doing or reuse js/validate.js)
    </script>
</head>
<body>
    <div class="container">
        <h2 class="title">录用新员工</h2>
        <% String msg = (String) request.getAttribute("msg");
           if (msg != null) { %>
               <div class="msg error"><%= msg %></div>
        <% } %>
        
        <form action="../hrServlet" method="post">
            <input type="hidden" name="action" value="add">
            <div class="form-group">
                <label>用户名：</label>
                <input type="text" name="username" required placeholder="初始账号">
            </div>
            <div class="form-group">
                <label>密码：</label>
                <input type="text" name="password" required value="Admin123" placeholder="默认密码Admin123">
            </div>
            <div class="form-group">
                <label>姓名：</label>
                <input type="text" name="name" required>
            </div>
            <div class="form-group">
                <label>工号：</label>
                <input type="text" name="studentId" required>
            </div>
            <div class="form-group">
                <label>角色：</label>
                <select name="role">
                    <option value="EMPLOYEE">普通员工</option>
                    <option value="MANAGER">经理</option>
                    <option value="HR">人事</option>
                    <option value="BOSS">老板</option>
                </select>
            </div>
            <button type="submit" class="btn">添加</button>
            <div class="link">
                <a href="../hrServlet?action=list">返回列表</a>
            </div>
        </form>
    </div>
</body>
</html>
