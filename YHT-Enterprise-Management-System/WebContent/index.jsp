<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="jsp/common/header.jsp" %>

<div class="main-container">
    <%@ include file="jsp/common/sidebar.jsp" %>
    
    <div class="content">
        <h1>欢迎使用益禾堂企业信息管理系统</h1>
        <p>您好，${sessionScope.currentUser.realName}！</p>
        <p>今天是：<%= new java.util.Date().toLocaleString() %></p>
        
        <div style="margin-top: 30px; padding: 20px; background: #fff; border-radius: 8px;">
            <h3>系统公告</h3>
            <ul>
                <li>请各位员工及时完善个人档案。</li>
                <li>库存盘点工作将于本月底进行，请仓库部做好准备。</li>
            </ul>
        </div>
    </div>
</div>

<div class="footer">
    ©2025 益禾堂企业信息管理系统 | 学生课程项目
</div>
</body>
</html>
