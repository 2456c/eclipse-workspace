<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>老板工作台 - 益禾堂企业管理系统</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="header">
        <div class="user-info">
            当前登录：${loginUser.username}（${loginUser.name} - 老板）
        </div>
        <div class="logout">
            <a href="../login">退出登录</a>
        </div>
        <div style="clear: both;"></div>
    </div>

    <div class="content">
        <h2>老板工作台</h2>
        
        <div class="dashboard-section">
            <h3>人事管理</h3>
            <ul>
                <li><a href="../hr/employee_list.jsp">查看所有员工</a></li>
                <li><a href="../hr/add_employee.jsp">添加员工</a></li>
            </ul>
        </div>

        <div class="dashboard-section">
            <h3>业务概览</h3>
            <ul>
                <li><a href="../inventory/product_list.jsp">产品库存管理</a></li>
                <li><a href="../inventory/material_list.jsp">原料库存管理</a></li>
                <li><a href="../sales/order_list.jsp">查看销售报表</a></li>
                <li><a href="../procurement/purchase_list.jsp">查看采购报表</a></li>
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
