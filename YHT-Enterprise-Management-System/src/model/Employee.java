package model;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int userId;
    private String name;
    private String position;
    private Date entryDate;
    private int quitFlag; // 0=在职, 1=离职

    public Employee() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Date getEntryDate() { return entryDate; }
    public void setEntryDate(Date entryDate) { this.entryDate = entryDate; }

    public int getQuitFlag() { return quitFlag; }
    public void setQuitFlag(int quitFlag) { this.quitFlag = quitFlag; }
}
