package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

public class Salary {
    private String employeeName;
    private String position;
    private String department;
    private int totalSalary;
    private String month;

    public Salary(String employeeName, String position, String department, int totalSalary, String month) {
        this.employeeName = employeeName;
        this.position = position;
        this.department = department;
        this.totalSalary = totalSalary;
        this.month = month;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public int getTotalSalary() {
        return totalSalary;
    }

    public String getMonth() {
        return month;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTotalSalary(int totalSalary) {
        this.totalSalary = totalSalary;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
