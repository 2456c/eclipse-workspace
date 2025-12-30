package controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.AccountService;
import model.RegistrationRequest;
import model.User;

@WebServlet("/AccountApprovalServlet")
public class AccountApprovalServlet extends HttpServlet {
    private AccountService accountService = new AccountService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RegistrationRequest> list = accountService.getPendingRequests();
        request.setAttribute("pendingRequests", list);
        request.getRequestDispatcher("/jsp/boss/approveAccount.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null || !"boss".equals(currentUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/jsp/error/AccessDenied.jsp");
            return;
        }

        String action = request.getParameter("action");
        String idStr = request.getParameter("requestId");
        if(idStr != null) {
            int requestId = Integer.parseInt(idStr);
            if ("approve".equals(action)) {
                if(accountService.approveRequest(requestId)) {
                    request.setAttribute("msg", "审批通过");
                } else {
                    request.setAttribute("msg", "审批失败");
                }
            } else if ("reject".equals(action)) {
                accountService.rejectRequest(requestId);
                request.setAttribute("msg", "已拒绝");
            }
        }
        
        doGet(request, response); // Reload list
    }
}
