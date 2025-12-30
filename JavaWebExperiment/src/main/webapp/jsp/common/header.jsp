<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>益禾堂EMS</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css?v=20251229">
</head>
<body>
<div class="top-bar">
    <span class="logo">益禾堂管理系统</span>
    <div class="user-info">
        👉 当前用户：${sessionScope.currentUser.name}
        （${sessionScope.currentUser.role}）
        <a href="LogoutServlet">退出</a>
    </div>
</div>
