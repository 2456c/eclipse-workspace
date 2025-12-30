package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.AccountService;
import model.RegistrationRequest;
import model.User;

@WebServlet("/EmployeeApplyServlet")
public class EmployeeApplyServlet extends HttpServlet {
    private AccountService accountService = new AccountService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        
        RegistrationRequest req = new RegistrationRequest();
        req.setApplicantName(request.getParameter("applicantName"));
        req.setDepartment(request.getParameter("department"));
        req.setPosition(request.getParameter("position"));
        req.setPhone(request.getParameter("phone")); // Assuming added in form
        req.setDesiredUsername(request.getParameter("desiredUsername"));
        req.setDesiredPassword(request.getParameter("desiredPassword"));
        req.setHrHandlerId(currentUser.getId());

        // Password validation logic could be here or frontend, backend is safer
        if (!isValidPassword(req.getDesiredPassword())) {
            request.setAttribute("msg", "密码不符合复杂度要求");
            request.getRequestDispatcher("/jsp/hr/employeeApply.jsp").forward(request, response);
            return;
        }

        if (accountService.submitRequest(req)) {
            request.setAttribute("msg", "申请已提交，等待老板审批");
        } else {
            request.setAttribute("msg", "提交失败，请重试");
        }
        request.getRequestDispatcher("/jsp/hr/employeeApply.jsp").forward(request, response);
    }
    
    private boolean isValidPassword(String password) {
         String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d.*\\d)[A-Za-z\\d@$!%*?&]{8,}$";
         return password != null && password.matches(regex);
    }
}
