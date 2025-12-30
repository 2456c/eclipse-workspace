<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <title>采购报表</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="header">
        <div class="user-info">当前登录：${loginUser.username}</div>
        <div class="logout"><a href="../login">退出</a></div>
        <div style="clear: both;"></div>
    </div>

    <div class="content">
        <h2>采购记录列表</h2>
        <div class="actions">
            <a href="javascript:history.back()" class="btn">返回</a>
        </div>
        
        <table border="1" style="width: 100%; border-collapse: collapse; margin-top: 20px;">
            <thead>
                <tr style="background-color: #f5f5f5;">
                    <th>ID</th>
                    <th>操作人</th>
                    <th>原料名称</th>
                    <th>数量</th>
                    <th>创建时间</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("list");
                    if (list != null) {
                        for (Map<String, Object> p : list) {
                %>
                <tr>
                    <td><%= p.get("id") %></td>
                    <td><%= p.get("user_id") %></td>
                    <td><%= p.get("material_name") %></td>
                    <td><%= p.get("quantity") %></td>
                    <td><%= p.get("create_time") %></td>
                </tr>
                <%      }
                    } else { 
                %>
                    <tr><td colspan="5">暂无采购记录</td></tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
