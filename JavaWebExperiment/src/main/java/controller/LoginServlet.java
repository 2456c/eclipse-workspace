package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import service.AuthService;
import com.model.User;

public class LoginServlet extends HttpServlet {
    private AuthService authService = new AuthService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginServlet(doPost) username=" + request.getParameter("username"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = authService.login(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            session.setAttribute("loginUser", user);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            String errorMsg = "登录失败";
            if (!authService.isUserExists(username)) {
                errorMsg = "用户名不存在";
            } else {
                errorMsg = "密码错误";
            }
            request.setAttribute("errorMessage", errorMsg);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
