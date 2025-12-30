package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Material;
import utils.DBConnectionUtils;

public class MaterialDao {
    
    public List<Material> findAll() {
        List<Material> list = new ArrayList<>();
        String sql = "SELECT * FROM material_stock";
        try (Connection conn = DBConnectionUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Material m = new Material();
                m.setId(rs.getInt("id"));
                m.setMaterialName(rs.getString("material_name"));
                m.setQuantity(rs.getDouble("quantity"));
                m.setUnit(rs.getString("unit"));
                m.setUpdateTime(rs.getTimestamp("update_time"));
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
