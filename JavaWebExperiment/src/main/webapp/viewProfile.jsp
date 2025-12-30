<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息查看</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
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

    <%-- 个人信息内容区 --%>
    <div class="content">
        <h2>个人信息</h2>
        
        <div class="profile-info">
            <div class="form-group">
                <label>用户名：</label>
                <input type="text" value="${loginUser.username}" readonly style="background-color: #f5f5f5;">
            </div>
            <div class="form-group">
                <label>密码：</label>
                <input type="text" value="${loginUser.password}" readonly style="background-color: #f5f5f5;">
            </div>
            <div class="form-group">
                <label>姓名：</label>
                <input type="text" value="${loginUser.username}" readonly style="background-color: #f5f5f5;">
            </div>
        </div>
        
        <div class="link" style="margin-top: 20px;">
            <a href="<%=request.getContextPath()%>/index.jsp">返回首页</a>
        </div>
    </div>
</body>
</html>
