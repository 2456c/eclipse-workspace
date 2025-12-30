package service;

import dao.MaterialDao;
import model.Material;
import java.util.List;

public class InventoryService {
    private MaterialDao materialDao = new MaterialDao();

    public List<Material> getAllMaterials() {
        return materialDao.findAll();
    }
}
