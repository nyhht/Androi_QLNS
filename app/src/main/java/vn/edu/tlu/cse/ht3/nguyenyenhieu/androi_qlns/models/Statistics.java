package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

public class Statistics {
    private String department;
    private double totalSalary;
    private int employeeCount;

    // ✅ Constructor đầy đủ cho 3 tham số
    public Statistics(String department, double totalSalary, int employeeCount) {
        this.department = department;
        this.totalSalary = totalSalary;
        this.employeeCount = employeeCount;
    }

    // ✅ Getter & Setter
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }
}
