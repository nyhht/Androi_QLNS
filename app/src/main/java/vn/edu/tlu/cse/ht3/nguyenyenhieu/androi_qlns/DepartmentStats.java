package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns;



public class DepartmentStats {
    private String departmentName;
    private int employeeCount;
    private double totalBaseSalary;
    private double totalDepartmentSalary; // Tổng lương của phòng ban

    public DepartmentStats(String departmentName, int employeeCount, double totalBaseSalary, double totalDepartmentSalary) {
        this.departmentName = departmentName;
        this.employeeCount = employeeCount;
        this.totalBaseSalary = totalBaseSalary;
        this.totalDepartmentSalary = totalDepartmentSalary;
    }

    // Getters
    public String getDepartmentName() {
        return departmentName;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public double getTotalBaseSalary() {
        return totalBaseSalary;
    }

    public double getTotalDepartmentSalary() {
        return totalDepartmentSalary;
    }
}