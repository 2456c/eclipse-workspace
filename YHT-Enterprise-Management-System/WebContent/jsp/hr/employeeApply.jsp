<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp" %>

<div class="main-container">
    <%@ include file="../common/sidebar.jsp" %>
    
    <div class="content">
        <h3>新员工账号申请 (HR)</h3>
        <p class="msg">${msg}</p>
        <p class="error">${error}</p>
        
        <div style="background: white; padding: 20px; max-width: 600px;">
            <form action="EmployeeApplyServlet" method="post" onsubmit="return validateApply()">
                <label>申请人姓名:</label><br>
                <input type="text" name="applicantName" required style="width:100%"><br><br>
                
                <label>所属部门:</label><br>
                <select name="department" style="width:100%; padding: 8px;">
                    <option value="Store">门店部 (Store)</option>
                    <option value="Warehouse">仓库部 (Warehouse)</option>
                    <option value="Sales">销售部 (Sales)</option>
                    <option value="Purchasing">采购部 (Purchasing)</option>
                    <option value="HR">人事部 (HR)</option>
                </select><br><br>
                
                <label>职位:</label><br>
                <input type="text" name="position" required style="width:100%"><br><br>
                
                <label>联系电话:</label><br>
                <input type="text" name="phone" style="width:100%"><br><br>
                
                <label>期望账号:</label><br>
                <input type="text" name="desiredUsername" required style="width:100%"><br><br>
                
                <label>初始密码 (需符合复杂规则):</label><br>
                <input type="text" name="desiredPassword" placeholder="8位以上, 大小写字母+2数字" required style="width:100%"><br><br>
                
                <button type="submit" style="background: #009688; color: white; padding: 10px 20px; border: none; cursor: pointer;">提交审批</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
