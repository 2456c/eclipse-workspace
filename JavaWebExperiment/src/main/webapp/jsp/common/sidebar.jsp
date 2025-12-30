<%@ page pageEncoding="UTF-8" %>
<%
    com.model.User cu = (com.model.User) session.getAttribute("currentUser");
    String role = cu == null ? "guest" : cu.getRole();
%>
<div class="sidebar">
    <ul>
        <li><a href="<%=request.getContextPath()%>/index.jsp">🏠 首页</a></li>
        <% if ("boss".equalsIgnoreCase(role)) { %>
            <li><a href="<%=request.getContextPath()%>/AccountApprovalServlet?action=list">📝 账号审批</a></li>
            <li><a href="<%=request.getContextPath()%>/hr/employeeApply.jsp">👥 员工入职申请</a></li>
            <li><a href="<%=request.getContextPath()%>/inventoryServlet?action=materials">📦 原材料库存</a></li>
        <% } else if ("hr".equalsIgnoreCase(role)) { %>
            <li><a href="<%=request.getContextPath()%>/hr/employeeApply.jsp">👥 员工入职申请</a></li>
        <% } else if ("manager".equalsIgnoreCase(role) || "staff".equalsIgnoreCase(role)) { %>
            <li><a href="<%=request.getContextPath()%>/inventoryServlet?action=materials">📦 原材料库存</a></li>
        <% } %>
        <li><a href="<%=request.getContextPath()%>/viewProfile.jsp">👤 个人信息</a></li>
    </ul>
</div>
