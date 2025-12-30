package com.dao;

import com.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseDao {

    public List<Map<String, Object>> getAllPurchases() {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT p.*, m.name as material_name FROM purchase_record p JOIN material m ON p.material_id = m.id ORDER BY create_time DESC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", rs.getInt("id"));
                map.put("user_id", rs.getString("user_id"));
                map.put("material_name", rs.getString("material_name"));
                map.put("quantity", rs.getInt("quantity"));
                map.put("create_time", rs.getTimestamp("create_time"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }
}
