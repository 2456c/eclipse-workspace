<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp" %>

<div class="main-container">
    <%@ include file="../common/sidebar.jsp" %>
    
    <div class="content">
        <h3>账号审批中心 (Boss)</h3>
        <p class="msg">${msg}</p>
        
        <table>
            <thead>
                <tr>
                    <th>申请ID</th>
                    <th>申请人</th>
                    <th>部门</th>
                    <th>职位</th>
                    <th>期望账号</th>
                    <th>申请时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty pendingRequests}">
                        <tr><td colspan="7" style="text-align:center;">暂无待审批记录</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${pendingRequests}" var="req">
                            <tr>
                                <td>${req.id}</td>
                                <td>${req.applicantName}</td>
                                <td>${req.department}</td>
                                <td>${req.position}</td>
                                <td>${req.desiredUsername}</td>
                                <td>${req.requestTime}</td>
                                <td>
                                    <form action="AccountApprovalServlet" method="post" style="display:inline;">
                                        <input type="hidden" name="requestId" value="${req.id}">
                                        <button type="submit" name="action" value="approve" style="background:green; color:white; border:none; padding:5px 10px; cursor:pointer;">同意</button>
                                        <button type="submit" name="action" value="reject" style="background:red; color:white; border:none; padding:5px 10px; cursor:pointer;">拒绝</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
