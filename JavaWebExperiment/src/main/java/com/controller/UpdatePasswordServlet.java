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

// 修改密码接口：接收表单POST请求
@WebServlet("/UpdatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 1. 获取当前登录用户
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String username = loginUser.getUsername();

        // 2. 获取表单参数
        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");
        String confirmPwd = request.getParameter("confirmPwd");

        // 3. 前端二次验证：新密码与确认密码一致
        if (!newPwd.equals(confirmPwd)) {
            request.setAttribute("msg", "新密码与确认密码不一致！");
            request.getRequestDispatcher("/updatePassword.jsp").forward(request, response);
            return;
        }

        // 4. 调用业务逻辑
        UserService service = new UserService();
        boolean isSuccess = service.updatePassword(username, oldPwd, newPwd);

        // 5. 处理结果
        if (isSuccess) {
            request.setAttribute("msg", "密码修改成功！");
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "旧密码错误或新密码格式错误！");
            request.getRequestDispatcher("/updatePassword.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}