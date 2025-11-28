package com.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.model.User;
import com.service.UserService;

// 注册接口：接收表单POST请求
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 1. 获取表单参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String studentId = request.getParameter("studentId");

        // 2. 封装用户对象
        User user = new User(username, password, name, studentId);

        // 3. 调用业务逻辑
        UserService service = new UserService();
        boolean isSuccess = service.register(user);

        // 4. 处理结果：成功→跳转登录页，失败→返回注册页（回显已填信息）
        if (isSuccess) {
            request.setAttribute("msg", "注册成功，请登录！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            boolean isExist = service.checkUsernameExists(username);
            if (isExist) {
                request.setAttribute("msg", "用户名已存在！");
            } else {
                request.setAttribute("msg", "密码格式错误（≥8字符，含2字母、2数字、1大小写）！");
            }
            // 回显已输入的信息（避免用户重新填写）
            request.setAttribute("username", username);
            request.setAttribute("name", name);
            request.setAttribute("studentId", studentId);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}