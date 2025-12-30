package model;

import java.io.Serializable;
import java.util.Date;

public class Material implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String materialName;
    private double quantity;
    private String unit;
    private Date updateTime;

    public Material() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
