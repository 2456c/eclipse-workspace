package com.dao;

import com.model.User;
import com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 用户数据访问层（仅处理数据库操作，无业务逻辑）
public class UserDao {

    // 注册：新增用户（返回false表示用户名重复/失败）
    public boolean insert(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            // 开启事务（防止并发问题）
            conn.setAutoCommit(false);
            
            // 先检查用户名是否存在（双重保障，避免主键冲突）
            if (findByUsername(user.getUsername()) != null) {
                return false;
            }
            
            // 使用新表 user_account
            // 映射: username, password, real_name (from name), role (from role, lowercase), department (default?), status
            // 注意：User对象里可能没有department，这里先给默认值或者从user获取如果不为空
            String sql = "INSERT INTO user_account(username, password, real_name, role, department, status, create_time) VALUES(?, ?, ?, ?, 'hr', ?, NOW())";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            
            // Role处理: Model是UPPERCASE，DB是lowercase enum
            String role = user.getRole() != null ? user.getRole().toLowerCase() : "staff";
            if ("employee".equals(role)) role = "staff"; // 兼容
            ps.setString(4, role);
            
            // Status处理
            String status = (user.getStatus() == 1) ? "active" : "disabled";
            ps.setString(5, status);
            
            int rows = ps.executeUpdate();
            conn.commit(); // 提交事务
            return rows > 0;
        } catch (SQLException e) {
            // 回滚事务
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, ps);
        }
    }

    // 根据用户名查询用户（登录/验证用）
    public User findByUsername(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            // 使用新表 user_account
            String sql = "SELECT * FROM user_account WHERE username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("real_name")); // 映射 real_name -> name
                user.setStudentId(""); // 新表无学号
                
                // 角色映射: DB(lowercase) -> Model(UPPERCASE)
                String dbRole = rs.getString("role");
                user.setRole(dbRole != null ? dbRole.toUpperCase() : "EMPLOYEE");
                
                // 状态映射: active -> 1, disabled -> 0
                String dbStatus = rs.getString("status");
                user.setStatus("active".equalsIgnoreCase(dbStatus) ? 1 : 0);
                
                return user;
            }
            return null; // 用户名不存在
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    // 修改用户名（旧用户名→新用户名）
    public boolean updateUsername(String oldUsername, String newUsername) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            
            // 检查新用户名是否已存在
            if (findByUsername(newUsername) != null) {
                return false;
            }
            
            String sql = "UPDATE user_account SET username = ? WHERE username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, newUsername);
            ps.setString(2, oldUsername);
            
            int rows = ps.executeUpdate();
            conn.commit();
            return rows > 0;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, ps);
        }
    }

    // 修改密码
    public boolean updatePassword(String username, String newPassword) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE user SET password = ? WHERE username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, username);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, ps);
        }
    }

    // 查询所有用户
    public java.util.List<User> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.util.List<User> list = new java.util.ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setStudentId(rs.getString("student_id"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getInt("status"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

    // 更新用户状态
    public boolean updateStatus(String username, int status) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE user SET status = ? WHERE username = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setString(2, username);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, ps);
        }
    }
}