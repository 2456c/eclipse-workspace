package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 数据库连接工具类
public class DBUtil {
    // ========== 需修改的地方 ==========
    // 替换为你的MySQL密码（安装时设置的root密码）
    private static final String PASSWORD = "qwe88qwe";
    // =================================

    // 固定配置（无需修改，除非端口/数据库名改了）
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/yht_ems?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";
    private static final String USER = "root";

    // 加载驱动（只执行一次）
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL驱动加载失败！");
        }
    }

    // 获取数据库连接
    public static Connection getConnection() {
        return ConnectionPool.getConnection();
    }

    // 关闭资源（连接、预编译语句、结果集）
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Return connection to pool instead of closing
        if (conn != null) {
            ConnectionPool.releaseConnection(conn);
        }
    }

    // 重载：只关闭连接和预编译语句（无结果集时用）
    public static void close(Connection conn, PreparedStatement ps) {
        close(conn, ps, null);
    }
}
