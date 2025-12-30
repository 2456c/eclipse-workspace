package com.dao;

import com.util.DBUtil;
import java.sql.*;

public class UserRegistrationDao {
    public boolean insertRequest(String applicantName, String department, String position, String phone,
                                 String desiredUsername, String desiredPassword, int hrId) {
        String sql = "INSERT INTO user_registration_request " +
                "(applicant_name, department, position, phone, desired_username, desired_password, status, request_time, hr_handler_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, 'pending', NOW(), ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, applicantName);
            ps.setString(2, department);
            ps.setString(3, position);
            ps.setString(4, phone);
            ps.setString(5, desiredUsername);
            ps.setString(6, desiredPassword);
            if (hrId > 0) {
                ps.setInt(7, hrId);
            } else {
                ps.setNull(7, Types.INTEGER);
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, ps);
        }
    }
}
