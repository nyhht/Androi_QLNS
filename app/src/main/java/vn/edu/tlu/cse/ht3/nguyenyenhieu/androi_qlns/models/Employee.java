package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private String name;
    private String email;
    private String password;
    private String position;
    private String department;
    private boolean isManager;
    private double salary;
    private String dob;
    private String phone;

    public Employee(int id, String name, String email, String password, String position, String department, boolean isManager, double salary, String dob, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.position = position;
        this.department = department;
        this.isManager = isManager;
        this.salary = salary;
        this.dob = dob;
        this.phone = phone;
    }

    public Employee(String name, String email, String password, String position, String department, boolean isManager, double salary, String dob, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.position = position;
        this.department = department;
        this.isManager = isManager;
        this.salary = salary;
        this.dob = dob;
        this.phone = phone;
    }

    // Getters và Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public boolean isManager() { return isManager; }
    public void setManager(boolean manager) { isManager = manager; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    // ➕ Thêm hàm getFullName() dùng trong Adapter
    public String getFullName() {
        return this.name;
    }

    // Optional: override toString()
    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}
