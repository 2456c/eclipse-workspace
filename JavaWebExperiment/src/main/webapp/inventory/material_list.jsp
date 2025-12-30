<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <title>原料库存</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="header">
        <div class="user-info">当前登录：${loginUser.username}</div>
        <div class="logout"><a href="../login">退出</a></div>
        <div style="clear: both;"></div>
    </div>

    <div class="content">
        <h2>原料库存列表</h2>
        <div class="actions">
            <a href="javascript:history.back()" class="btn">返回</a>
        </div>
        
        <table border="1" style="width: 100%; border-collapse: collapse; margin-top: 20px;">
            <thead>
                <tr style="background-color: #f5f5f5;">
                    <th>ID</th>
                    <th>名称</th>
                    <th>库存</th>
                    <th>单位</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("list");
                    if (list != null) {
                        for (Map<String, Object> m : list) {
                %>
                <tr>
                    <td><%= m.get("id") %></td>
                    <td><%= m.get("name") %></td>
                    <td><%= m.get("stock") %></td>
                    <td><%= m.get("unit") %></td>
                </tr>
                <%      }
                    } else { 
                %>
                    <tr><td colspan="4">暂无数据</td></tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
