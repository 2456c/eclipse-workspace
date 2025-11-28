package com.model;

// 用户实体类：存储用户信息
public class User {
    private String username;  // 用户名（唯一）
    private String password;  // 密码
    private String name;      // 真实姓名
    private String studentId; // 学号

    // 无参构造
    public User() {}

    // 全参构造
    public User(String username, String password, String name, String studentId) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.studentId = studentId;
    }

    // Getter和Setter（必须有，用于JSP EL表达式取值）
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
}