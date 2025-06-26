package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

import java.io.Serializable;

public class Department implements Serializable {
    private int id;
    private String name;
    private String description;
    private String leader;
    private int employeeCount; // Đổi tên từ "number" sang "employeeCount"

    public Department() {}

    // Constructor đầy đủ có ID
    public Department(int id, String name, String description, String leader, int employeeCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.leader = leader;
        this.employeeCount = employeeCount;
    }

    // Constructor khi thêm mới (không có ID)
    public Department(String name, String description, String leader, int employeeCount) {
        this.name = name;
        this.description = description;
        this.leader = leader;
        this.employeeCount = employeeCount;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }
}
