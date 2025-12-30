<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Access Denied</title>
    <style>
        body { text-align: center; padding-top: 100px; font-family: sans-serif; }
        h1 { color: red; }
    </style>
</head>
<body>
    <h1>🚫 非法访问</h1>
    <p>您没有权限访问此页面。</p>
    <a href="${pageContext.request.contextPath}/index.jsp">返回首页</a>
</body>
</html>
