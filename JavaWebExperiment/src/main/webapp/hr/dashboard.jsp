<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人事工作台 - 益禾堂企业管理系统</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="header">
        <div class="user-info">
            当前登录：${loginUser.username}（${loginUser.name} - HR）
        </div>
        <div class="logout">
            <a href="../login">退出登录</a>
        </div>
        <div style="clear: both;"></div>
    </div>

    <div class="content">
        <h2>人事工作台</h2>
        
        <div class="dashboard-section">
            <h3>员工管理</h3>
            <ul>
                <li><a href="../hr/employee_list.jsp">员工列表</a></li>
                <li><a href="../hr/add_employee.jsp">录用新员工</a></li>
            </ul>
        </div>

        <div class="dashboard-section">
            <h3>个人设置</h3>
            <ul>
                <li><a href="../updateUsername.jsp">修改用户名</a></li>
                <li><a href="../updatePassword.jsp">修改密码</a></li>
            </ul>
        </div>
    </div>
</body>
</html>
