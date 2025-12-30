package com.filter;

import com.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class RoleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        // 静态资源和公开页面放行
        if (uri.contains("/css/") || uri.contains("/js/") || uri.endsWith("/login.jsp") || uri.endsWith("/register.jsp")
                || uri.endsWith("/LoginServlet") || uri.endsWith("/RegisterServlet")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        User current = (session == null) ? null : (User) session.getAttribute("currentUser");
        if (current == null) {
            chain.doFilter(request, response);
            return;
        }
        String role = current.getRole();

        // HR模块：仅 hr 与 boss
        if (uri.contains("/hr/") || uri.contains("/EmployeeApplyServlet")) {
            if (!("hr".equalsIgnoreCase(role) || "boss".equalsIgnoreCase(role))) {
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
                return;
            }
        }
        // Boss模块：仅 boss
        if (uri.contains("/boss/") || uri.contains("/AccountApprovalServlet")) {
            if (!"boss".equalsIgnoreCase(role)) {
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
                return;
            }
        }
        // Inventory模块：允许 boss/manager/staff
        if (uri.contains("/inventory") || uri.contains("/inventoryServlet")) {
            if (!("boss".equalsIgnoreCase(role) || "manager".equalsIgnoreCase(role) || "staff".equalsIgnoreCase(role))) {
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
