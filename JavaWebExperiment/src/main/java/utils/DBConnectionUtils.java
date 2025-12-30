package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/yht_ems?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "qwe88qwe";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("MySQL驱动未找到，请确保 WEB-INF/lib 中存在 mysql-connector-j.jar");
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("DBConnectionUtils connecting: " + URL + " user=" + USER);
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("DBConnectionUtils connection OK");
        return conn;
    }
}
