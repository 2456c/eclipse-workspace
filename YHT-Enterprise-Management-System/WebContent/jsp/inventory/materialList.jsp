<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp" %>

<div class="main-container">
    <%@ include file="../common/sidebar.jsp" %>
    
    <div class="content">
        <h3>原材料库存管理</h3>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>材料名称</th>
                    <th>库存数量</th>
                    <th>单位</th>
                    <th>更新时间</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${materials}" var="m">
                    <tr>
                        <td>${m.id}</td>
                        <td>${m.materialName}</td>
                        <td>
                            ${m.quantity}
                            <c:if test="${m.quantity < 10}">
                                <span style="color:red; font-weight:bold; margin-left:10px;">(库存不足)</span>
                            </c:if>
                        </td>
                        <td>${m.unit}</td>
                        <td>${m.updateTime}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
