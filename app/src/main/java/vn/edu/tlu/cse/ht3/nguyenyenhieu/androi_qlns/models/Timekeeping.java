package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

public class Timekeeping {
    private int id;
    private int employeeId;
    private String date;
    private String status; // Ví dụ: "Có mặt", "Nghỉ phép", "Muộn", v.v.

    public Timekeeping() {}

    public Timekeeping(int id, int employeeId, String date, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.status = status;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
