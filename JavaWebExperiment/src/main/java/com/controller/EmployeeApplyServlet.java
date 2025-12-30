package com.controller;

import com.dao.UserRegistrationDao;
import com.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EmployeeApplyServlet")
public class EmployeeApplyServlet extends HttpServlet {
    private final UserRegistrationDao dao = new UserRegistrationDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String applicantName = request.getParameter("applicant_name");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        String desiredUsername = request.getParameter("desired_username");
        String desiredPassword = request.getParameter("desired_password");

        // 获取当前HR用户的ID
        User currentUser = (User) request.getSession().getAttribute("loginUser");
        int hrId = (currentUser != null) ? currentUser.getId() : 0;
        System.out.println("EmployeeApplyServlet: Submitting request. CurrentUser=" + (currentUser!=null ? currentUser.getUsername() : "null") + ", HR_ID=" + hrId);

        boolean ok = dao.insertRequest(applicantName, department, position, phone, desiredUsername, desiredPassword, hrId);
        if (ok) {
            request.setAttribute("msg", "申请已提交，待HR与老板审批");
        } else {
            request.setAttribute("msg", "申请提交失败，请稍后重试");
        }
        request.getRequestDispatcher("/hr/employeeApply.jsp").forward(request, response);
    }
}
