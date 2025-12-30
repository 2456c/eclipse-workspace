package dao;

import java.sql.*;
import model.User;
import utils.DBConnectionUtils;

public class UserDao {

    public User login(String username, String password) {
        String sql = "SELECT * FROM user_account WHERE username = ? AND password = ? AND status = 'active'";
        try (Connection conn = DBConnectionUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            System.out.println("UserDao.login query with username=" + username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRealName(rs.getString("real_name"));
                user.setRole(rs.getString("role"));
                user.setDepartment(rs.getString("department"));
                user.setStatus(rs.getString("status"));
                user.setCreateTime(rs.getTimestamp("create_time"));
                System.out.println("UserDao.login found user id=" + user.getId());
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
            System.out.println("UserDao.exists query username=" + username);
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
