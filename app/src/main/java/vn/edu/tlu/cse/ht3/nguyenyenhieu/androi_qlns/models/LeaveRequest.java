package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

public class LeaveRequest {
    private int id;
    private String name;
    private String date;
    private String position;
    private String department;
    private String email;
    private String reason;

    public LeaveRequest() {}

    public LeaveRequest(int id, String name, String date, String position, String department, String email, String reason) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.position = position;
        this.department = department;
        this.email = email;
        this.reason = reason;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
