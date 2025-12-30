CREATE DATABASE IF NOT EXISTS yht_ems DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE yht_ems;

-- 1. 用户账号表（核心表）
CREATE TABLE user_account (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '登录账号',
    password VARCHAR(64) NOT NULL COMMENT '密码',
    real_name VARCHAR(50) NOT NULL COMMENT '真实姓名',
    role ENUM('boss','hr','manager','staff') NOT NULL COMMENT '角色',
    department ENUM('HR','Sales','Purchasing','Warehouse','Store') NOT NULL COMMENT '所属部门',
    status ENUM('active','disabled') DEFAULT 'active' COMMENT '账号状态',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 2. 员工档案表（与账号关联，但包含更多人事信息）
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT COMMENT '关联账号ID',
    name VARCHAR(50) NOT NULL,
    position VARCHAR(50) COMMENT '职位',
    entry_date DATE NOT NULL COMMENT '入职日期',
    quit_flag TINYINT(1) DEFAULT 0 COMMENT '0=在职, 1=离职',
    FOREIGN KEY (user_id) REFERENCES user_account(id)
);

-- 3. 账号注册申请表（审批流核心）
CREATE TABLE user_registration_request (
    id INT PRIMARY KEY AUTO_INCREMENT,
    applicant_name VARCHAR(50) NOT NULL COMMENT '申请人姓名',
    department VARCHAR(20) NOT NULL,
    position VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    desired_username VARCHAR(50) NOT NULL COMMENT '期望账号',
    desired_password VARCHAR(64) NOT NULL COMMENT '初始密码',
    status ENUM('pending','approved','rejected') DEFAULT 'pending',
    request_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    hr_handler_id INT COMMENT '提交申请的HR ID',
    owner_approval_time DATETIME COMMENT '老板审批时间'
);

-- 4. 原材料库存表
CREATE TABLE material_stock (
    id INT PRIMARY KEY AUTO_INCREMENT,
    material_name VARCHAR(100) NOT NULL,
    quantity DECIMAL(10,2) DEFAULT 0.00 COMMENT '库存数量',
    unit VARCHAR(10) COMMENT '单位(kg/box)',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 5. 成品库存表
CREATE TABLE product_inventory (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    quantity INT DEFAULT 0,
    price DECIMAL(10,2) NOT NULL,
    last_updated DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 6. 客户档案表
CREATE TABLE customer_record (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(50),
    phone VARCHAR(20),
    membership_level VARCHAR(20) DEFAULT 'Normal',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 初始化 BOSS 账号 (密码: Admin@123)
INSERT INTO user_account (username, password, real_name, role, department, status) 
VALUES ('admin', 'Admin@123', '大老板', 'boss', 'Store', 'active');

-- 初始化 HR 账号 (密码: Hr@12345)
INSERT INTO user_account (username, password, real_name, role, department, status) 
VALUES ('hr01', 'Hr@12345', '人事经理', 'hr', 'HR', 'active');
