package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtils {
    // 默认使用 localhost:3306，数据库名为 yht_ems
    // 请确保已在 MySQL 中执行 db_schema.sql
    private static final String URL = "jdbc:mysql://localhost:3306/yht_ems?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "qwe88qwe"; // 默认密码，请根据实际环境修改

    static {
        try {
            // 加载 MySQL 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("未找到 MySQL 驱动，请确保 WEB-INF/lib 下包含 mysql-connector-java.jar");
        }
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() throws SQLException {
        System.out.println("DBConnectionUtils connecting: " + URL + " user=" + USER);
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("DBConnectionUtils connection OK");
        return conn;
    }

    /**
     * 关闭资源
     */
    public static void close(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
