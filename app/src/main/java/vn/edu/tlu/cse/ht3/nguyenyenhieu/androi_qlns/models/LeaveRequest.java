package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

public class LeaveRequest {
    private int id;
    private String fullName;
    private String leaveDate;
    private String position;
    private String department;
    private String email;
    private String reason;

    public LeaveRequest(int id, String fullName, String leaveDate, String position, String department, String email, String reason) {
        this.id = id;
        this.fullName = fullName;
        this.leaveDate = leaveDate;
        this.position = position;
        this.department = department;
        this.email = email;
        this.reason = reason;
    }

    // Constructor without ID (for adding new requests)
    public LeaveRequest(String fullName, String leaveDate, String position, String department, String email, String reason) {
        this.fullName = fullName;
        this.leaveDate = leaveDate;
        this.position = position;
        this.department = department;
        this.email = email;
        this.reason = reason;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public String getReason() {
        return reason;
    }

    // Setters (if needed, but for display, getters are often enough)
    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}