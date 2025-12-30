package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.RegistrationRequest;
import utils.DBConnectionUtils;

public class RequestDao {

    public boolean createRequest(RegistrationRequest req) {
        String sql = "INSERT INTO user_registration_request (applicant_name, department, position, phone, desired_username, desired_password, hr_handler_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, req.getApplicantName());
            ps.setString(2, req.getDepartment());
            ps.setString(3, req.getPosition());
            ps.setString(4, req.getPhone());
            ps.setString(5, req.getDesiredUsername());
            ps.setString(6, req.getDesiredPassword());
            ps.setInt(7, req.getHrHandlerId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<RegistrationRequest> findPendingRequests() {
        List<RegistrationRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM user_registration_request WHERE status = 'pending' ORDER BY request_time DESC";
        try (Connection conn = DBConnectionUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                RegistrationRequest r = new RegistrationRequest();
                r.setId(rs.getInt("id"));
                r.setApplicantName(rs.getString("applicant_name"));
                r.setDepartment(rs.getString("department"));
                r.setPosition(rs.getString("position"));
                r.setPhone(rs.getString("phone"));
                r.setDesiredUsername(rs.getString("desired_username"));
                r.setDesiredPassword(rs.getString("desired_password")); // 仅展示用，实际不应透出
                r.setStatus(rs.getString("status"));
                r.setRequestTime(rs.getTimestamp("request_time"));
                r.setHrHandlerId(rs.getInt("hr_handler_id"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean approveRequest(int requestId) {
        Connection conn = null;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false); // 开启事务

            // 1. 获取申请详情
            String getSql = "SELECT * FROM user_registration_request WHERE id = ?";
            PreparedStatement getPs = conn.prepareStatement(getSql);
            getPs.setInt(1, requestId);
            ResultSet rs = getPs.executeQuery();
            
            if (!rs.next()) {
                conn.rollback();
                return false;
            }

            String username = rs.getString("desired_username");
            String password = rs.getString("desired_password");
            String realName = rs.getString("applicant_name");
            String department = rs.getString("department");
            String position = rs.getString("position");
            
            // 2. 插入 user_account
            // 默认角色逻辑：根据申请部门判断，或者简化为统一是 staff，只有 HR 申请的才是 hr？
            // 需求里：HR可提交新员工入职账号申请。
            // 这里为了简单，如果部门是HR，则角色hr，否则根据职位判断，若无特殊逻辑默认为 staff，部门负责人为 manager
            String role = "staff";
            if ("HR".equalsIgnoreCase(department)) role = "hr";
            if (position.toLowerCase().contains("manager") || position.toLowerCase().contains("店长")) role = "manager";

            String insertUserSql = "INSERT INTO user_account (username, password, real_name, role, department, status) VALUES (?, ?, ?, ?, ?, 'active')";
            PreparedStatement userPs = conn.prepareStatement(insertUserSql, Statement.RETURN_GENERATED_KEYS);
            userPs.setString(1, username);
            userPs.setString(2, password);
            userPs.setString(3, realName);
            userPs.setString(4, role);
            userPs.setString(5, department);
            userPs.executeUpdate();
            
            ResultSet keyRs = userPs.getGeneratedKeys();
            int newUserId = 0;
            if (keyRs.next()) {
                newUserId = keyRs.getInt(1);
            }

            // 3. 插入 employee
            String insertEmpSql = "INSERT INTO employee (user_id, name, position, entry_date, quit_flag) VALUES (?, ?, ?, CURDATE(), 0)";
            PreparedStatement empPs = conn.prepareStatement(insertEmpSql);
            empPs.setInt(1, newUserId);
            empPs.setString(2, realName);
            empPs.setString(3, position);
            empPs.executeUpdate();

            // 4. 更新 request 状态
            String updateSql = "UPDATE user_registration_request SET status = 'approved', owner_approval_time = NOW() WHERE id = ?";
            PreparedStatement updatePs = conn.prepareStatement(updateSql);
            updatePs.setInt(1, requestId);
            updatePs.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            return false;
        } finally {
            DBConnectionUtils.close(conn);
        }
    }

    public boolean rejectRequest(int requestId) {
        String sql = "UPDATE user_registration_request SET status = 'rejected', owner_approval_time = NOW() WHERE id = ?";
        try (Connection conn = DBConnectionUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, requestId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
