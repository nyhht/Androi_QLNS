package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String password;
    private String position;
    private String department; // Thêm biến này
    private boolean isManager;

    public Employee(int id, String name, String email, String password, String position, String department, boolean isManager) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.position = position;
        this.department = department;
        this.isManager = isManager;
    }

    public Employee(String name, String email, String password, String position, String department, boolean isManager) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.position = position;
        this.department = department;
        this.isManager = isManager;
    }

    // Getter & Setter đầy đủ
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() { // THÊM HÀM NÀY
        return department;
    }

    public void setDepartment(String department) { // THÊM HÀM NÀY
        this.department = department;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}
