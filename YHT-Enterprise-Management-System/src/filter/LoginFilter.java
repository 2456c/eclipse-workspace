package filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@WebFilter("/*")
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();

        // 静态资源和登录相关放行
        if (uri.endsWith("login.jsp") || uri.endsWith("LoginServlet") || 
            uri.contains("/css/") || uri.contains("/js/") || uri.contains("/images/")) {
            chain.doFilter(req, res);
            return;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            // 这里为了防止重定向循环，如果已经是根路径请求，可能需要特殊处理，但一般login.jsp不是根
            // 假设 ContextPath 是 /YHT
            request.setAttribute("errorMessage", "非法访问，请先登录");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            chain.doFilter(req, res);
        }
    }
    
    public void init(FilterConfig f) {}
    public void destroy() {}
}
