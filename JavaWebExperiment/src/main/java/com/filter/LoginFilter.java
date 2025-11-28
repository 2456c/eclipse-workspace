package com.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.model.User;

// 过滤所有请求：未登录用户只能访问登录/注册页、CSS/JS
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig fConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 1. 获取请求URL
        String url = req.getRequestURI();

        // 2. 放行无需登录的资源
        boolean isExclude = url.contains("login.jsp") 
                || url.contains("register.jsp") 
                || url.contains("LoginServlet") 
                || url.contains("RegisterServlet") 
                || url.contains("css/") 
                || url.contains("js/");

        if (isExclude) {
            chain.doFilter(request, response);
            return;
        }

        // 3. 已登录用户放行，未登录用户跳转登录页
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            req.setAttribute("msg", "非法访问，请先登录！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}