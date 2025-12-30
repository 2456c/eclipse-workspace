<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.User" %>
<html>
<head>
    <title>员工列表</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="header">
        <div class="user-info">
            当前登录：${loginUser.username}
        </div>
        <div class="logout">
            <a href="../login">退出登录</a>
        </div>
        <div style="clear: both;"></div>
    </div>

    <div class="content">
        <h2>员工管理</h2>
        <div class="actions">
            <a href="../boss/dashboard.jsp" class="btn">返回工作台</a>
            <a href="add_employee.jsp" class="btn">添加员工</a>
        </div>
        
        <table border="1" style="width: 100%; border-collapse: collapse; margin-top: 20px;">
            <thead>
                <tr style="background-color: #f5f5f5;">
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>工号</th>
                    <th>角色</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<User> list = (List<User>) request.getAttribute("userList");
                    if (list != null) {
                        for (User u : list) {
                %>
                <tr>
                    <td><%= u.getUsername() %></td>
                    <td><%= u.getName() %></td>
                    <td><%= u.getStudentId() %></td>
                    <td><%= u.getRole() %></td>
                    <td><%= u.getStatus() == 1 ? "在职" : "<span style='color:red'>离职</span>" %></td>
                    <td>
                        <% if (u.getStatus() == 1) { %>
                            <a href="../hrServlet?action=disable&username=<%= u.getUsername() %>" onclick="return confirm('确定要禁用该账号吗？')">禁用/离职</a>
                        <% } else { %>
                            <a href="../hrServlet?action=enable&username=<%= u.getUsername() %>" onclick="return confirm('确定要启用该账号吗？')">启用/复职</a>
                        <% } %>
                    </td>
                </tr>
                <%      }
                    } else { 
                %>
                    <tr><td colspan="6">暂无数据，请先访问Servlet获取数据</td></tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
