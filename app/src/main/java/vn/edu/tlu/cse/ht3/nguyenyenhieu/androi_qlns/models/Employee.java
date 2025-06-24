package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

public class Employee {
    private int id;
    private String name, birthday, phone, position, email, password;
    private boolean isManager;

    public Employee(int id, String name, String birthday, String phone, String position, String email, String password, boolean isManager) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.position = position;
        this.email = email;
        this.password = password;
        this.isManager = isManager;
    }

    public Employee(String name, String birthday, String phone, String position, String email, String password, boolean isManager) {
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.position = position;
        this.email = email;
        this.password = password;
        this.isManager = isManager;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getBirthday() { return birthday; }
    public String getPhone() { return phone; }
    public String getPosition() { return position; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public boolean isManager() { return isManager; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setPosition(String position) { this.position = position; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setManager(boolean manager) { isManager = manager; }
}
