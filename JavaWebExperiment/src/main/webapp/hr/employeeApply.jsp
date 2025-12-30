<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工入职申请 - 益禾堂企业管理系统</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <script>
        function validateForm(){
            function strong(p){return /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d.*\d)[A-Za-z\d@$!%*?&]{8,}$/.test(p);}
            var dn=document.getElementsByName("desired_username")[0].value.trim();
            var dp=document.getElementsByName("desired_password")[0].value.trim();
            if(dn===""||dp===""){alert("账号与密码不能为空");return false;}
            if(!strong(dp)){alert("密码需≥8位，含大小写字母与至少2个数字");return false;}
            return true;
        }
    </script>
</head>
<body>
<div class="content">
    <h2>员工入职申请</h2>
    <% String msg = (String) request.getAttribute("msg"); if (msg != null) { %>
    <div class="msg success"><%= msg %></div>
    <% } %>
    <form action="<%=request.getContextPath()%>/EmployeeApplyServlet" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label>申请人姓名：</label>
            <input type="text" name="applicant_name" required>
        </div>
        <div class="form-group">
            <label>部门：</label>
            <select name="department" required style="width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; font-size: 14px; background-color: white;">
                <option value="">请选择部门</option>
                <option value="sales">销售部 (Sales)</option>
                <option value="procurement">采购部 (Procurement)</option>
                <option value="inventory">库存部 (Inventory)</option>
                <option value="hr">人事部 (HR)</option>
                <option value="finance">财务部 (Finance)</option>
            </select>
        </div>
        <div class="form-group">
            <label>职位：</label>
            <select name="position" required style="width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; font-size: 14px; background-color: white;">
                <option value="">请选择职位</option>
                <option value="manager">经理 (Manager)</option>
                <option value="supervisor">主管 (Supervisor)</option>
                <option value="staff">员工 (Staff)</option>
                <option value="intern">实习生 (Intern)</option>
            </select>
        </div>
        <div class="form-group">
            <label>电话：</label>
            <input type="text" name="phone">
        </div>
        <div class="form-group">
            <label>期望账号：</label>
            <input type="text" name="desired_username" required>
        </div>
        <div class="form-group">
            <label>期望密码：</label>
            <input type="text" name="desired_password" required>
        </div>
        <button type="submit" class="btn">提交申请</button>
        <div class="link" style="margin-top: 15px;">
            <a href="<%=request.getContextPath()%>/index.jsp">返回首页</a>
        </div>
    </form>
    <div class="footer">由学生开发 - 益禾堂企业管理系统</div>
</div>
</body>
</html>
