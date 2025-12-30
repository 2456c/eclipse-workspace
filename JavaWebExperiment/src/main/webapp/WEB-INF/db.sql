/* 数据库初始化脚本 */
DROP DATABASE IF EXISTS yihetang_db;
CREATE DATABASE yihetang_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE yihetang_db;

/* 用户表 */
CREATE TABLE user (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    student_id VARCHAR(50) NOT NULL, /* 兼容原有设计，兼作员工工号 */
    role VARCHAR(20) NOT NULL DEFAULT 'EMPLOYEE', /* BOSS, MANAGER, EMPLOYEE, HR */
    status INT NOT NULL DEFAULT 1 /* 1: Active, 0: Disabled (离职) */
);

/* 初始用户 */
/* 密码统一为: Admin123 (符合规则: 8位, 2字母, 2数字, 1大写, 1小写) */
INSERT INTO user (username, password, name, student_id, role, status) VALUES 
('boss001', 'Admin123', '张老板', 'B001', 'BOSS', 1),
('manager001', 'Admin123', '李经理', 'M001', 'MANAGER', 1),
('hr001', 'Admin123', '王HR', 'H001', 'HR', 1),
('emp001', 'Admin123', '赵员工', 'E001', 'EMPLOYEE', 1);

/* 产品表 (奶茶) */
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 0
);

INSERT INTO product (name, price, stock) VALUES 
('益禾烤奶', 8.00, 100),
('泷珠奶茶', 9.00, 100),
('翠峰茉莉', 6.00, 100);

/* 原料表 */
CREATE TABLE material (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    unit VARCHAR(20) NOT NULL
);

INSERT INTO material (name, stock, unit) VALUES 
('茶叶', 500, 'kg'),
('牛奶', 200, 'L'),
('糖', 100, 'kg'),
('珍珠', 50, 'kg');

/* 销售订单表 */
CREATE TABLE sales_order (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50),
    total_amount DECIMAL(10, 2),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(username) ON UPDATE CASCADE
);

/* 采购记录表 */
CREATE TABLE purchase_record (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50),
    material_id INT,
    quantity INT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(username) ON UPDATE CASCADE,
    FOREIGN KEY (material_id) REFERENCES material(id)
);
