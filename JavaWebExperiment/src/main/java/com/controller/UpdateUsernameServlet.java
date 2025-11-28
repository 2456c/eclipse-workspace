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

// 修改用户名接口：接收表单POST请求
@WebServlet("/UpdateUsernameServlet")
public class UpdateUsernameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 1. 获取当前登录用户（从Session中）
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String oldUsername = loginUser.getUsername();

        // 2. 获取新用户名
        String newUsername = request.getParameter("newUsername");

        // 3. 调用业务逻辑
        UserService service = new UserService();
        boolean isSuccess = service.updateUsername(oldUsername, newUsername);

        // 4. 处理结果：成功→更新Session，失败→返回修改页
        if (isSuccess) {
            loginUser.setUsername(newUsername); // 更新Session中的用户名
            session.setAttribute("loginUser", loginUser);
            request.setAttribute("msg", "用户名修改成功！");
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "新用户名已存在或格式错误！");
            request.getRequestDispatcher("/updateUsername.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}