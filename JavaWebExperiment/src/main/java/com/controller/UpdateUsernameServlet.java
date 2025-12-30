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

// 修改用户名控制器
@WebServlet("/updateUsername")
public class UpdateUsernameServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取当前登录用户
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String oldUsername = loginUser.getUsername();

        // 获取新用户名
        String newUsername = request.getParameter("newUsername").trim();

        // 调用业务层修改
        String result = userService.updateUsername(oldUsername, newUsername);
        if ("用户名修改成功！".equals(result)) {
            // 修改成功：更新Session中的用户信息，跳转到首页
            User newUser = userService.getUserByUsername(newUsername);
            session.setAttribute("loginUser", newUser);
            request.setAttribute("msg", result);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            // 修改失败：返回修改页
            request.setAttribute("msg", result);
            request.getRequestDispatcher("/updateUsername.jsp").forward(request, response);
        }
    }
}
