package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBConnectionUtils;

//@WebServlet("/test")
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        System.out.println("TestServlet accessed");
        List<String> users = new ArrayList<>();
        try (Connection conn = DBConnectionUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT username FROM user_account");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("DB error: " + e.getMessage());
            return;
        }
        response.getWriter().println("OK. users=" + users);
    }
}
