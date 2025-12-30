<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>益禾堂EMS</title>
    <!-- 处理相对路径问题 -->
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/validation.js"></script>
</head>
<body>
<div class="top-bar">
    <span class="logo">益禾堂管理系统</span>
    <div class="user-info">
        👉 当前用户：${sessionScope.currentUser.realName} 
        （${sessionScope.currentUser.role} | ${sessionScope.currentUser.department}）
        <a href="LogoutServlet">退出</a>
    </div>
</div>
