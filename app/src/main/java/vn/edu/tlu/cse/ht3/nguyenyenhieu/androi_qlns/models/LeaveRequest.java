package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

public class LeaveRequest {
    private long id; // Tùy chọn: dùng để lưu ID từ cơ sở dữ liệu nếu cần
    private String fullName;
    private String leaveDate;
    private String position;
    private String department;
    private String email;
    private String reason;

    // Constructor để tạo đối tượng LeaveRequest
    public LeaveRequest(String fullName, String leaveDate, String position, String department, String email, String reason) {
        this.fullName = fullName;
        this.leaveDate = leaveDate;
        this.position = position;
        this.department = department;
        this.email = email;
        this.reason = reason;
    }

    // Constructor với ID (ví dụ khi đọc từ DB)
    public LeaveRequest(long id, String fullName, String leaveDate, String position, String department, String email, String reason) {
        this.id = id;
        this.fullName = fullName;
        this.leaveDate = leaveDate;
        this.position = position;
        this.department = department;
        this.email = email;
        this.reason = reason;
    }

    // Getters
    public long getId() {
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

    // Setters (nếu bạn cần chỉnh sửa dữ liệu sau khi tạo đối tượng)
    public void setId(long id) {
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