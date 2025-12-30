package model;

import java.io.Serializable;
import java.util.Date;

public class RegistrationRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String applicantName;
    private String department;
    private String position;
    private String phone;
    private String desiredUsername;
    private String desiredPassword;
    private String status; // pending, approved, rejected
    private Date requestTime;
    private int hrHandlerId;
    private Date ownerApprovalTime;

    public RegistrationRequest() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDesiredUsername() { return desiredUsername; }
    public void setDesiredUsername(String desiredUsername) { this.desiredUsername = desiredUsername; }

    public String getDesiredPassword() { return desiredPassword; }
    public void setDesiredPassword(String desiredPassword) { this.desiredPassword = desiredPassword; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getRequestTime() { return requestTime; }
    public void setRequestTime(Date requestTime) { this.requestTime = requestTime; }

    public int getHrHandlerId() { return hrHandlerId; }
    public void setHrHandlerId(int hrHandlerId) { this.hrHandlerId = hrHandlerId; }

    public Date getOwnerApprovalTime() { return ownerApprovalTime; }
    public void setOwnerApprovalTime(Date ownerApprovalTime) { this.ownerApprovalTime = ownerApprovalTime; }
}
