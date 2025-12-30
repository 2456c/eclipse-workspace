package com.service;

import com.dao.UserDao;
import com.model.User;

// 用户业务逻辑层（处理验证、业务规则，不直接操作数据库）
public class UserService {
    private UserDao userDao = new UserDao();

    // 注册验证+新增
    public String register(User user) {
        // 1. 验证用户名格式
        if (!validateUserPassFormat(user.getUsername())) {
            return "用户名格式错误！需至少8位，含2个字母、2个数字、1个大写、1个小写";
        }
        // 2. 验证密码格式
        if (!validateUserPassFormat(user.getPassword())) {
            return "密码格式错误！需至少8位，含2个字母、2个数字、1个大写、1个小写";
        }
        // 3. 验证姓名/学号非空
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            return "姓名不能为空！";
        }
        if (user.getStudentId() == null || user.getStudentId().trim().isEmpty()) {
            return "学号不能为空！";
        }
        // 4. 执行注册
        boolean success = userDao.insert(user);
        return success ? "注册成功！" : "用户名已存在！";
    }

    // 登录验证
    public String login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return "用户名不存在！";
        }
        if (!user.getPassword().equals(password)) {
            return "密码错误！";
        }
        if (user.getStatus() == 0) {
            return "账号已被禁用（离职），请联系管理员！";
        }
        return "success"; // 登录成功
    }

    // 修改用户名
    public String updateUsername(String oldUsername, String newUsername) {
        // 验证新用户名格式
        if (!validateUserPassFormat(newUsername)) {
            return "新用户名格式错误！需至少8位，含2个字母、2个数字、1个大写、1个小写";
        }
        // 执行修改
        boolean success = userDao.updateUsername(oldUsername, newUsername);
        return success ? "用户名修改成功！" : "新用户名已存在！";
    }

    // 修改密码
    public String updatePassword(String username, String oldPwd, String newPwd) {
        // 验证旧密码
        User user = userDao.findByUsername(username);
        if (user == null || !user.getPassword().equals(oldPwd)) {
            return "旧密码错误！";
        }
        // 验证新密码格式
        if (!validateUserPassFormat(newPwd)) {
            return "新密码格式错误！需至少8位，含2个字母、2个数字、1个大写、1个小写";
        }
        // 执行修改
        boolean success = userDao.updatePassword(username, newPwd);
        return success ? "密码修改成功！" : "密码修改失败！";
    }

    // 获取用户信息（用于页面显示）
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    // 验证用户名/密码格式（核心规则）
    public boolean validateUserPassFormat(String str) {
        if (str == null || str.length() < 8) {
            return false; // 长度不足8位
        }
        // 统计：小写字母、大写字母、数字的数量
        int lower = 0, upper = 0, digit = 0;
        for (char c : str.toCharArray()) {
            if (Character.isLowerCase(c)) lower++;
            else if (Character.isUpperCase(c)) upper++;
            else if (Character.isDigit(c)) digit++;
        }
        // 规则：至少2个字母（大小写合计）、2个数字、1个大写、1个小写
        return (lower + upper) >= 2 && digit >= 2 && upper >= 1 && lower >= 1;
    }

    // 获取所有用户
    public java.util.List<User> getAllUsers() {
        return userDao.findAll();
    }

    // 更改用户状态
    public boolean changeUserStatus(String username, int status) {
        return userDao.updateStatus(username, status);
    }
}