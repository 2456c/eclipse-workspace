package com.controller;

import com.util.DBUtil;
import com.dao.EmployeeDao;
import com.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AccountApprovalServlet")
public class AccountApprovalServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("approve".equals(action)) {
            approve(request, response);
            return;
        } else if ("reject".equals(action)) {
            reject(request, response);
            return;
        }
        list(request, response);
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT id, applicant_name, department, position, phone, desired_username, request_time, status, approver_name " +
                "FROM user_registration_request ORDER BY request_time DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> m = new HashMap<>();
                m.put("id", rs.getInt("id"));
                m.put("applicant_name", rs.getString("applicant_name"));
                m.put("department", rs.getString("department"));
                m.put("position", rs.getString("position"));
                m.put("phone", rs.getString("phone"));
                m.put("desired_username", rs.getString("desired_username"));
                m.put("request_time", rs.getTimestamp("request_time"));
                m.put("status", rs.getString("status"));
                m.put("approver_name", rs.getString("approver_name"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("pendingList", list);
        request.getRequestDispatcher("/boss/approval.jsp").forward(request, response);
    }

    private void approve(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Starting approval process...");
        try {
            String idParam = request.getParameter("id");
            System.out.println("Approval ID param: " + idParam);
            int id = Integer.parseInt(idParam);
            
            // Remove getting role from request since it's no longer in the form
            // String roleParam = request.getParameter("role");
            // ...

            String selectSql = "SELECT applicant_name, department, position, desired_username, desired_password " +
                    "FROM user_registration_request WHERE id=? AND status='pending'";
            
            Connection conn = null;
            try {
                conn = DBUtil.getConnection();
                conn.setAutoCommit(false); // Start transaction

                String name = null;
                String dept = null;
                String pos = null;
                String username = null;
                String password = null;

                try (PreparedStatement sel = conn.prepareStatement(selectSql)) {
                    sel.setInt(1, id);
                    try (ResultSet rs = sel.executeQuery()) {
                        if (rs.next()) {
                            name = rs.getString("applicant_name");
                            dept = rs.getString("department");
                            pos = rs.getString("position");
                            username = rs.getString("desired_username");
                            password = rs.getString("desired_password");
                            System.out.println("Found request: " + name + ", " + username + ", dept=" + dept + ", pos=" + pos);
                        } else {
                            System.out.println("Request not found or not pending for ID: " + id);
                            throw new Exception("申请记录不存在或已处理");
                        }
                    }
                }
                
                // Deduce ROLE based on position or department logic
                // Logic:
                // If position contains 'manager' -> MANAGER
                // If position contains 'supervisor' -> MANAGER (or create new enum?) - Let's map to MANAGER for now
                // If department is 'hr' -> HR (unless position is manager/boss)
                // If username starts with 'boss' or position is 'boss' -> BOSS
                // Default -> EMPLOYEE
                
                String role = "EMPLOYEE"; // Default
                if (pos != null) {
                    String lowerPos = pos.toLowerCase();
                    if (lowerPos.contains("manager") || lowerPos.contains("director")) {
                        role = "MANAGER";
                    } else if (lowerPos.contains("boss") || lowerPos.contains("ceo")) {
                        role = "BOSS";
                    }
                }
                if (dept != null && "hr".equalsIgnoreCase(dept) && !"BOSS".equals(role)) {
                    // HR department staff are usually HR role, unless they are managers
                    if (!"MANAGER".equals(role)) {
                        role = "HR";
                    }
                }
                
                System.out.println("Deduced Role: " + role);

                // Create user account (Using user_account table as per provided ER diagram)
                // user_account schema: id(PK), username, password, real_name, role(enum), department(enum), status(enum), create_time
                
                int userId = 0;
                String insertUser = "INSERT INTO user_account(username, password, real_name, role, department, status, create_time) " +
                        "VALUES (?, ?, ?, ?, ?, 'active', NOW())";
                try (PreparedStatement iu = conn.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)) {
                    iu.setString(1, username);
                    iu.setString(2, password);
                    iu.setString(3, name);
                    iu.setString(4, role); // Use deduced role (enum values are lowercase in DB? No, previous fix used uppercase. Let's stick to lowercase for DB enum if we recreated it.)
                    // Wait, db_schema.sql used: ENUM('boss', 'manager', 'hr', 'staff') -> LOWERCASE!
                    // My previous fix tried uppercase which might be wrong if I just recreated DB with lowercase enums.
                    // Let's use lowercase based on db_schema.sql content I just wrote.
                    
                    iu.setString(4, role.toLowerCase().replace("employee", "staff")); // Map EMPLOYEE -> staff to match DB enum
                    iu.setString(5, dept.toLowerCase()); // Department
                    iu.executeUpdate();
                    try (ResultSet gen = iu.getGeneratedKeys()) {
                        if (gen.next()) userId = gen.getInt(1);
                    }
                }
                System.out.println("User record created in 'user_account' table, ID: " + userId);
                
                if (userId == 0) throw new Exception("创建用户账号失败，无法获取ID");

                // Create employee record (Using employee table as per provided ER diagram)
                // employee schema: id(PK), user_id(FK), name, position, entry_date, quit_flag
                
                String insertEmp = "INSERT INTO employee(user_id, name, position, entry_date, quit_flag) " +
                        "VALUES (?, ?, ?, CURDATE(), 0)";
                try (PreparedStatement ie = conn.prepareStatement(insertEmp)) {
                    ie.setInt(1, userId);
                    ie.setString(2, name);
                    ie.setString(3, pos);
                    ie.executeUpdate();
                }
                System.out.println("Employee record created in 'employee' table");
                
                // Update request status
                // Get current user (approver) name
                User currentUser = (User) request.getSession().getAttribute("loginUser");
                String approverName = (currentUser != null) ? currentUser.getName() : "Unknown";
                System.out.println("AccountApprovalServlet: Approving request. CurrentUser=" + (currentUser!=null ? currentUser.getUsername() : "null") + ", ApproverName=" + approverName);

                try (PreparedStatement up = conn.prepareStatement(
                        "UPDATE user_registration_request SET status='approved', owner_approval_time=NOW(), approver_name=? WHERE id=?")) {
                    up.setString(1, approverName);
                    up.setInt(2, id);
                    int updated = up.executeUpdate();
                    System.out.println("Request status updated, rows: " + updated);
                }

                conn.commit();
                System.out.println("Transaction committed");
                request.getSession().setAttribute("msg", "审批通过成功！");
            } catch (Exception e) {
                System.out.println("Error during approval: " + e.getMessage());
                e.printStackTrace();
                if (conn != null) {
                    try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
                }
                throw e;
            } finally {
                if (conn != null) {
                    try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("msg", "审批失败：" + e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/AccountApprovalServlet?action=list");
    }

    private void reject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            // Get current user (approver) name
            User currentUser = (User) request.getSession().getAttribute("loginUser");
            String approverName = (currentUser != null) ? currentUser.getName() : "Unknown";

            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(
                         "UPDATE user_registration_request SET status='rejected', owner_approval_time=NOW(), approver_name=? WHERE id=?")) {
                ps.setString(1, approverName);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
            request.getSession().setAttribute("msg", "已拒绝该申请");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("msg", "操作失败：" + e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/AccountApprovalServlet?action=list");
    }
}
