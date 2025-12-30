package com.controller;

import com.model.User;
import com.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// 登录控制器
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 跳转到登录页（GET请求）
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 解决中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取前端参数
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        // 调用业务层验证
        String result = userService.login(username, password);
        if ("success".equals(result)) {
            // 登录成功：保存用户信息到Session，跳转到成功页
            User user = userService.getUserByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", user); // 存储登录状态
            System.out.println("LoginServlet: User logged in - " + user.getUsername() + ", ID=" + user.getId() + ", Name=" + user.getName());
            
            request.setAttribute("msg", user.getUsername() + "，登录成功！");
            
            // 根据角色跳转
            String role = user.getRole();
            if ("BOSS".equals(role)) {
                request.getRequestDispatcher("/boss/dashboard.jsp").forward(request, response);
            } else if ("MANAGER".equals(role)) {
                request.getRequestDispatcher("/manager/dashboard.jsp").forward(request, response);
            } else if ("HR".equals(role)) {
                request.getRequestDispatcher("/hr/dashboard.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            // 登录失败：返回登录页，显示错误信息
            request.setAttribute("msg", result);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
