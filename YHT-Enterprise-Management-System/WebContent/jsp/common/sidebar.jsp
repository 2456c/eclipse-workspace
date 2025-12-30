<%@ page pageEncoding="UTF-8" %>
<div class="sidebar">
    <ul>
        <li><a href="index.jsp">🏠 首页</a></li>
        
        <c:if test="${sessionScope.currentUser.role == 'boss'}">
            <li><a href="AccountApprovalServlet">📝 账号审批</a></li>
        </c:if>
        
        <c:if test="${sessionScope.currentUser.role == 'hr' || sessionScope.currentUser.role == 'boss'}">
            <li><a href="jsp/hr/employeeApply.jsp">👥 员工入职申请</a></li>
        </c:if>
        
        <c:if test="${sessionScope.currentUser.role != 'staff'}">
            <li><a href="InventoryServlet?action=list">📦 原材料库存</a></li>
        </c:if>
        
        <li><a href="#">👤 个人信息</a></li>
    </ul>
</div>
