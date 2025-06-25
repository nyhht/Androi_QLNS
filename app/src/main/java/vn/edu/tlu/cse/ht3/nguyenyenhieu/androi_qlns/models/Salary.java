package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

public class Salary {
    private int id;
    private int employeeId;
    private String employeeName;
    private String position;
    private String month;
    private double baseSalary;
    private double bonus;
    private double deduction;
    private double totalSalary;

    // Constructor dùng khi tính lương đơn giản từ danh sách nhân viên
    public Salary(String employeeName, String position, double totalSalary) {
        this.employeeName = employeeName;
        this.position = position;
        this.totalSalary = totalSalary;
    }

    // Constructor đầy đủ cho bảng lương chi tiết
    public Salary(int id, int employeeId, String month, double baseSalary, double bonus, double deduction) {
        this.id = id;
        this.employeeId = employeeId;
        this.month = month;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.deduction = deduction;
        recalculateTotal();
    }

    // Constructor không có ID (khi thêm mới)
    public Salary(int employeeId, String month, double baseSalary, double bonus, double deduction) {
        this.employeeId = employeeId;
        this.month = month;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.deduction = deduction;
        recalculateTotal();
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
        recalculateTotal();
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
        recalculateTotal();
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
        recalculateTotal();
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    private void recalculateTotal() {
        this.totalSalary = baseSalary + bonus - deduction;
    }
}
