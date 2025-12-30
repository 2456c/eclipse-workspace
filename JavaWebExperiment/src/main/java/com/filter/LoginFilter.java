package com.filter;

import com.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// 拦截所有需要登录的页面
@WebFilter(urlPatterns = {
    "/success.jsp", "/updateUsername.jsp", "/updatePassword.jsp", "/viewProfile.jsp",
    "/UpdateUsernameServlet", "/UpdatePasswordServlet",
    "/boss/*", "/manager/*", "/hr/*", "/inventory/*", "/sales/*", "/procurement/*",
    "/hrServlet", "/inventoryServlet", "/salesServlet", "/procurementServlet"
})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 转为HTTP请求/响应
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 获取Session（不创建新Session）
        HttpSession session = req.getSession(false);

        // 检查是否登录（Session中是否有用户信息）
        User loginUser = (session == null) ? null : (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            // 未登录：跳转到登录页，提示非法访问
            req.setAttribute("msg", "非法访问，请先登录！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        // 已登录：继续访问目标页面
        chain.doFilter(request, response);
    }

    // 过滤器初始化（无需实现）
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    // 过滤器销毁（无需实现）
    @Override
    public void destroy() {}
}