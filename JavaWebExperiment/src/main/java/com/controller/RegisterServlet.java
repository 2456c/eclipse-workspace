package com.controller;

import com.model.User;
import com.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 注册控制器
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 跳转到注册页（GET请求）
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取前端参数
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String name = request.getParameter("name").trim();
        String studentId = request.getParameter("studentId").trim();

        // 封装为User对象
        User user = new User(username, password, name, studentId);

        // 调用业务层注册
        String result = userService.register(user);
        if ("注册成功！".equals(result)) {
            // 注册成功：跳转到登录页
            request.setAttribute("msg", result + "请登录");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            // 注册失败：返回注册页，显示错误信息
            request.setAttribute("msg", result);
            // 回显用户输入（避免重新填写）
            request.setAttribute("username", username);
            request.setAttribute("name", name);
            request.setAttribute("studentId", studentId);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}