package com.model;

// 用户实体类（MVC的Model层）
public class User {
    private int id;           // 用户ID (对应数据库user_account.id)
    private String username;  // 用户名
    private String password;  // 密码
    private String name;      // 真实姓名 (对应数据库user_account.real_name)
    private String studentId; // 学号 (废弃，保留兼容)
    private String role;      // 角色
    private int status;       // 状态

    // 空参构造
    public User() {}

    // 全参构造
    public User(int id, String username, String password, String name, String studentId, String role, int status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.studentId = studentId;
        this.role = role;
        this.status = status;
    }

    // 兼容旧构造
    public User(String username, String password, String name, String studentId) {
        this(0, username, password, name, studentId, "EMPLOYEE", 1);
    }
    
    public User(String username, String password, String name, String studentId, String role, int status) {
        this(0, username, password, name, studentId, role, status);
    }

    // Getter和Setter方法
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
