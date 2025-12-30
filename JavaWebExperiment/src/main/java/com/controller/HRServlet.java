package com.controller;

import com.model.User;
import com.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/hrServlet")
public class HRServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            List<User> userList = userService.getAllUsers();
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("/hr/employee_list.jsp").forward(request, response);
        } else if ("disable".equals(action)) {
            String username = request.getParameter("username");
            userService.changeUserStatus(username, 0); // 禁用
            response.sendRedirect("hrServlet?action=list");
        } else if ("enable".equals(action)) {
            String username = request.getParameter("username");
            userService.changeUserStatus(username, 1); // 启用
            response.sendRedirect("hrServlet?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            // 添加员工
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String studentId = request.getParameter("studentId"); // 工号
            String role = request.getParameter("role");
            
            User user = new User(username, password, name, studentId, role, 1);
            String result = userService.register(user); // 复用注册逻辑
            
            if (result.contains("成功")) {
                response.sendRedirect("hrServlet?action=list");
            } else {
                request.setAttribute("msg", result);
                request.getRequestDispatcher("/hr/add_employee.jsp").forward(request, response);
            }
        }
    }
}
