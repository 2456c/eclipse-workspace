package dao;

import java.sql.*;
import com.model.User;
import utils.DBConnectionUtils;

public class UserDao {

    public User login(String username, String password) {
        String sql = "SELECT * FROM user_account WHERE username = ? AND password = ? AND status = 'active'";
        try (Connection conn = DBConnectionUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            System.out.println("UserDao.login query username=" + username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("real_name"));
                user.setStudentId(null);
                user.setRole(rs.getString("role"));
                user.setStatus("active".equals(rs.getString("status")) ? 1 : 0);
                System.out.println("UserDao.login found username=" + user.getUsername());
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean exists(String username) {
        String sql = "SELECT count(*) FROM user_account WHERE username = ?";
        try (Connection conn = DBConnectionUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            System.out.println("UserDao.exists username=" + username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int c = rs.getInt(1);
                System.out.println("UserDao.exists count=" + c);
                return c > 0;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
