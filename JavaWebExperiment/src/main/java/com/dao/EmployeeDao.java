package com.dao;

import com.util.DBUtil;
import java.sql.*;

public class EmployeeDao {
    public int insertEmployee(int userId, String name, String department, String position) {
        String sql = "INSERT INTO employee(user_id, name, department, position, entry_date, quit_flag) " +
                "VALUES (?, ?, ?, ?, CURDATE(), 0)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, name);
            ps.setString(3, department);
            ps.setString(4, position);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, ps);
        }
    }
}
