package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class ConnectionPool {
    private static LinkedList<Connection> pool = new LinkedList<>();
    private static final int INITIAL_SIZE = 5;
    private static final int MAX_SIZE = 20;
    
    // DB Config
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/yht_ems?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "qwe88qwe"; // Keeping original password

    static {
        try {
            Class.forName(DRIVER);
            for (int i = 0; i < INITIAL_SIZE; i++) {
                pool.add(DriverManager.getConnection(URL, USER, PASSWORD));
            }
            System.out.println("Connection Pool initialized with " + INITIAL_SIZE + " connections.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Connection Pool");
        }
    }

    public static synchronized Connection getConnection() {
        if (pool.isEmpty()) {
            // Pool empty, create new if below max or just create new
            // For simplicity, create new one if empty but try to limit in real scenario
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException("Failed to create new connection", e);
            }
        }
        return pool.removeFirst();
    }

    public static synchronized void releaseConnection(Connection conn) {
        if (conn != null) {
            if (pool.size() < MAX_SIZE) {
                pool.addLast(conn);
            } else {
                try {
                    conn.close(); // Pool full, close it
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
