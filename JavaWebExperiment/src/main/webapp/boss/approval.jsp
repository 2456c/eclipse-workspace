<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>账号审批 - 益禾堂企业管理系统</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<div class="content">
    <%-- 消息提示 --%>
    <% 
        String msg = (String) session.getAttribute("msg"); 
        if (msg != null) { 
            session.removeAttribute("msg"); // 显示后清除，避免刷新重复显示
    %>
    <div class="msg <%= msg.contains("失败") || msg.contains("错误") ? "error" : "success" %>" style="margin-bottom: 20px;">
        <%= msg %>
    </div>
    <% } %>

    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; border-bottom: 1px solid #eee; padding-bottom: 15px;">
        <h2 style="margin: 0; color: #303133;">入职申请审批记录</h2>
        <span style="font-size: 14px; color: #909399;">共 <%= request.getAttribute("pendingList") != null ? ((List)request.getAttribute("pendingList")).size() : 0 %> 条记录</span>
    </div>

    <table class="approval-table">
        <thead>
        <tr>
            <th width="5%">ID</th>
            <th width="10%">姓名</th>
            <th width="10%">部门</th>
            <th width="10%">职位</th>
            <th width="10%">申请账号</th>
            <th width="15%">申请时间</th>
            <th width="10%">审批人</th>
            <th width="25%">操作</th>
        </tr>
        </thead>
        <tbody>
        <% 
            List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("pendingList");
            if (list != null && !list.isEmpty()) {
                for (Map<String, Object> m : list) {
                    String status = (String) m.get("status");
                    String approver = (String) m.get("approver_name");
                    if (approver == null) approver = "-";
        %>
        <tr>
            <td><%= m.get("id") %></td>
            <td><%= m.get("applicant_name") %></td>
            <td><%= m.get("department") %></td>
            <td><%= m.get("position") %></td>
            <td><%= m.get("desired_username") %></td>
            <td><%= m.get("request_time") %></td>
            <td><%= approver %></td>
            <td>
                <% if ("pending".equals(status)) { %>
                <form action="<%=request.getContextPath()%>/AccountApprovalServlet" method="get" class="action-form">
                    <input type="hidden" name="id" value="<%= m.get("id") %>">
                    <button type="submit" name="action" value="approve" class="btn btn-approve">通过</button>
                    <button type="submit" name="action" value="reject" class="btn btn-reject">拒绝</button>
                </form>
                <% } else if ("approved".equals(status)) { %>
                    <span style="color: green; font-weight: bold;">已通过</span>
                <% } else if ("rejected".equals(status)) { %>
                    <span style="color: red; font-weight: bold;">已拒绝</span>
                <% } %>
            </td>
        </tr>
        <% 
                }
            } else { 
        %>
        <tr>
            <td colspan="8" style="text-align: center; color: #909399; padding: 30px;">暂无申请记录</td>
        </tr>
        <% } %>
        </tbody>
    </table>
    
    <div class="link" style="margin-top: 30px; text-align: left;">
        <a href="<%=request.getContextPath()%>/index.jsp" style="display: inline-flex; align-items: center; color: #606266; font-size: 14px;">
            <span style="margin-right: 5px;">&larr;</span> 返回首页
        </a>
    </div>
    
    <div class="footer" style="margin-top: 40px; border-top: 1px solid #eee; padding-top: 20px;">
        由学生开发 - 益禾堂企业管理系统
    </div>
</div>
</body>
</html>
