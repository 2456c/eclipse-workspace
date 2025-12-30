益禾堂企业信息管理系统 - 项目说明

1. 数据库配置：
   - 请在 MySQL 中运行 `src/main/webapp/WEB-INF/db.sql` 脚本以初始化数据库。
   - 数据库名：yihetang_db
   - 默认账号：
     - 老板：boss001 / Admin123
     - 经理：manager001 / Admin123
     - 人事：hr001 / Admin123
     - 员工：emp001 / Admin123

2. 功能模块：
   - 登录/注册：带有复杂的密码/用户名验证。
   - 角色管理：不同角色进入不同的工作台（Boss, Manager, HR, Employee）。
   - 业务模块：
     - 人事管理：HR可添加、禁用员工。
     - 库存管理：查看奶茶产品和原料库存。
     - 销售/采购：查看销售订单和采购记录。
   - 个人设置：修改用户名、密码。

3. 技术实现：
   - MVC模式：JSP + Servlet + JavaBean/Service/Dao。
   - 数据库连接池：自定义 ConnectionPool 实现。
   - 过滤器：LoginFilter 实现权限拦截。

4. 注意事项：
   - 若数据库密码不是 qwe88qwe，请修改 src/main/java/com/util/ConnectionPool.java。
