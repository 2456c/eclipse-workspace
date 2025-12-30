DROP DATABASE IF EXISTS yht_ems;
CREATE DATABASE yht_ems CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE yht_ems;

-- 1. 用户账号表 (user_account)
CREATE TABLE user_account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    real_name VARCHAR(50) NOT NULL,
    role ENUM('boss', 'manager', 'hr', 'staff') NOT NULL,
    department ENUM('sales', 'procurement', 'inventory', 'hr', 'finance') NOT NULL,
    status ENUM('active', 'disabled') NOT NULL DEFAULT 'active',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 2. 员工信息表 (employee)
CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    position VARCHAR(50),
    entry_date DATE,
    quit_flag TINYINT(1) DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES user_account(id) ON DELETE CASCADE
);

-- 3. 用户注册申请表 (user_registration_request)
CREATE TABLE user_registration_request (
    id INT AUTO_INCREMENT PRIMARY KEY,
    applicant_name VARCHAR(50) NOT NULL,
    department VARCHAR(20) NOT NULL,
    position VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    desired_username VARCHAR(50) NOT NULL,
    desired_password VARCHAR(64) NOT NULL,
    status ENUM('pending', 'approved', 'rejected') DEFAULT 'pending',
    request_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    hr_handler_id INT,
    owner_approval_time DATETIME
);

-- 4. 客户记录表 (customer_record)
CREATE TABLE customer_record (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    membership_level VARCHAR(20),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 5. 原材料库存表 (material_stock)
CREATE TABLE material_stock (
    id INT AUTO_INCREMENT PRIMARY KEY,
    material_name VARCHAR(100) NOT NULL,
    quantity DECIMAL(10, 2) DEFAULT 0,
    unit VARCHAR(10),
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 6. 产品库存表 (product_inventory)
CREATE TABLE product_inventory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    quantity INT DEFAULT 0,
    price DECIMAL(10, 2),
    last_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 初始化数据：老板账号
INSERT INTO user_account (username, password, real_name, role, department, status) VALUES ('boss001', 'Admin123', '张老板', 'boss', 'hr', 'active');
INSERT INTO user_account (username, password, real_name, role, department, status) VALUES ('hr001', 'Admin123', '王人事', 'hr', 'hr', 'active');

-- 初始化数据：原材料测试数据
INSERT INTO material_stock (material_name, quantity, unit) VALUES 
('茶叶', 500, 'kg'),
('牛奶', 200, 'L'),
('糖', 100, 'kg');
