package com.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.model.User;
import com.service.UserService;

// 登录接口：接收表单POST请求
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 处理POST请求（表单提交）
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 解决中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 1. 获取表单参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2. 调用业务逻辑
        UserService service = new UserService();
        User loginUser = service.login(username, password);

        // 3. 处理结果：成功→跳转首页，失败→返回登录页
        if (loginUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser); // 存储登录状态
            response.sendRedirect("/JavaWebExperiment/success.jsp");
        } else {
            boolean isExist = service.checkUsernameExists(username);
            if (!isExist) {
                request.setAttribute("msg", "用户名不存在！");
            } else {
                request.setAttribute("msg", "密码错误！");
            }
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    // 处理GET请求（避免405错误：直接转发到POST处理）
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}