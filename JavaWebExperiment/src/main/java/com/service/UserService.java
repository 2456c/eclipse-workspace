package com.service;

import java.util.ArrayList;
import java.util.List;
import com.model.User;

// 业务逻辑层：封装注册、登录、修改等核心功能
public class UserService {
    // 模拟数据库：存储所有用户（内存存储，重启项目后数据丢失）
    private static List<User> userList = new ArrayList<>();

    // 注册业务：返回true=注册成功，false=失败（用户名已存在/密码格式错误）
    public boolean register(User user) {
        if (checkUsernameExists(user.getUsername())) {
            return false; // 用户名已存在
        }
        if (!validatePasswordFormat(user.getPassword())) {
            return false; // 密码格式错误
        }
        userList.add(user);
        return true;
    }

    // 登录业务：返回User=登录成功，null=失败（用户名不存在/密码错误）
    public User login(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // 修改用户名业务：返回true=成功，false=失败（新用户名已存在/格式错误）
    public boolean updateUsername(String oldUsername, String newUsername) {
        if (checkUsernameExists(newUsername)) {
            return false;
        }
        if (!validatePasswordFormat(newUsername)) {
            return false;
        }
        for (User user : userList) {
            if (user.getUsername().equals(oldUsername)) {
                user.setUsername(newUsername);
                return true;
            }
        }
        return false;
    }

    // 修改密码业务：返回true=成功，false=失败（旧密码错误/新密码格式错误）
    public boolean updatePassword(String username, String oldPwd, String newPwd) {
        User user = getuserByUsername(username);
        if (user == null || !user.getPassword().equals(oldPwd)) {
            return false; // 旧密码错误
        }
        if (!validatePasswordFormat(newPwd)) {
            return false; // 新密码格式错误
        }
        user.setPassword(newPwd);
        return true;
    }

    // 辅助方法：检查用户名是否已存在
    public boolean checkUsernameExists(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // 辅助方法：验证密码/用户名格式（≥8字符，含2字母、2数字、1大小写）
    public boolean validatePasswordFormat(String str) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d.*\\d)(?=.*[a-zA-Z].*[a-zA-Z]).{8,}$";
        return str.matches(regex);
    }

    // 辅助方法：通过用户名获取用户对象
    public User getuserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}