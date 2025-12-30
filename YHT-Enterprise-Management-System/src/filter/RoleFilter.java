package filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

//@WebFilter("/*")
public class RoleFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("currentUser") : null;

        if (user != null) {
            String role = user.getRole();
            
            // HR 页面保护
            if (uri.contains("/jsp/hr/") && !"hr".equals(role) && !"boss".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/jsp/error/AccessDenied.jsp");
                return;
            }
            
            // Boss 页面保护
            if (uri.contains("/jsp/boss/") && !"boss".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/jsp/error/AccessDenied.jsp");
                return;
            }
            
            // Inventory 页面保护
            // 规则：HR不可访问库存；普通员工(staff)视部门而定，这里简化为：
            // 只有 boss, manager, 和非HR/非Sales的staff (即Store/Warehouse的staff) 可以访问
            // 或者更简单：只有 boss 和 (manager/staff 且 部门不是HR)
            if (uri.contains("/jsp/inventory/")) {
                boolean isBoss = "boss".equals(role);
                // 简单的反向排除：HR 不能看库存
                if (!isBoss && "hr".equals(role)) {
                    response.sendRedirect(request.getContextPath() + "/jsp/error/AccessDenied.jsp");
                    return;
                }
                // 也可以根据部门做更细致的隔离，例如：
                // if (!isBoss && !"Store".equals(user.getDepartment()) && !"Warehouse".equals(user.getDepartment())) ...
            }
        }
        
        chain.doFilter(req, res);
    }

    public void init(FilterConfig f) {}
    public void destroy() {}
}
