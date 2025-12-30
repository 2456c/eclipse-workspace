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

// 修改密码控制器
@WebServlet("/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取当前登录用户
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String username = loginUser.getUsername();

        // 获取前端参数
        String oldPwd = request.getParameter("oldPwd").trim();
        String newPwd = request.getParameter("newPwd").trim();

        // 调用业务层修改
        String result = userService.updatePassword(username, oldPwd, newPwd);
        if ("密码修改成功！".equals(result)) {
            // 修改成功：跳转到首页
            request.setAttribute("msg", result);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            // 修改失败：返回修改页
            request.setAttribute("msg", result);
            request.getRequestDispatcher("/updatePassword.jsp").forward(request, response);
        }
    }
}
