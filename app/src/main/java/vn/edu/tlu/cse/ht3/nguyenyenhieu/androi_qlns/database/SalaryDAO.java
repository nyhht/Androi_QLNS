package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Salary;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Statistics;

public class SalaryDAO {
    private final SQLiteDatabase db;

    public SalaryDAO(Context context) {
        db = new DatabaseHelper(context).getReadableDatabase();
    }

    // Tính lương theo tháng và số ngày làm việc
    public List<Salary> calculateSalaries(String month, int baseSalary, int workDays) {
        List<Salary> salaries = new ArrayList<>();
        Cursor cursor = db.query(DatabaseHelper.TABLE_EMPLOYEE, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Employee employee = new Employee(
                    cursor.getInt(0),                    // id
                    cursor.getString(1),                 // name
                    cursor.getString(2),                 // email
                    cursor.getString(3),                 // password
                    cursor.getString(4),                 // position
                    cursor.getString(5),                 // department
                    cursor.getInt(6) == 1,               // isManager
                    cursor.getDouble(7),                 // salary
                    cursor.getString(8),                 // dob
                    cursor.getString(9)                  // phone
            );

            int totalSalary = baseSalary * workDays;
            if (employee.isManager()) {
                totalSalary = (int) (totalSalary * 1.5);
            }

            salaries.add(new Salary(
                    employee.getName(),
                    employee.getPosition(),
                    employee.getDepartment(),
                    totalSalary,
                    month
            ));
        }

        cursor.close();
        return salaries;
    }

    // Lấy danh sách tất cả các tháng (mô phỏng)
    public List<String> getAllMonths() {
        List<String> months = new ArrayList<>();
        months.add("06/2025");
        months.add("05/2025");
        months.add("04/2025");
        return months;
    }

    // Lấy tất cả phòng ban
    public List<String> getAllDepartments() {
        List<String> departments = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT DISTINCT department FROM Employee", null);
        while (cursor.moveToNext()) {
            departments.add(cursor.getString(0));
        }
        cursor.close();
        return departments;
    }

    // Lọc danh sách lương theo tháng và phòng ban
    public List<Salary> getSalaries(String month, String department) {
        List<Salary> salaries = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Employee WHERE department = ?", new String[]{department});

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);            // Tên nhân viên
            String position = cursor.getString(4);        // Chức vụ
            String departmentValue = cursor.getString(5); // Phòng ban
            double baseSalary = cursor.getDouble(7);      // Lương cơ bản

            // Giả lập tiền thưởng và khấu trừ
            double bonus = 1_000_000;
            double deduction = 500_000;
            int totalSalary = (int) (baseSalary + bonus - deduction);

            Salary salary = new Salary(name, position, departmentValue, totalSalary, month);
            salaries.add(salary);
        }

        cursor.close();
        return salaries;
    }

    // Tổng lương toàn bộ nhân viên (giả lập từ bảng Employee)
    public double getTotalSalary() {
        double total = 0;
        Cursor cursor = db.rawQuery("SELECT salary FROM Employee", null);
        while (cursor.moveToNext()) {
            total += cursor.getDouble(0);
        }
        cursor.close();
        return total;
    }

    // Thống kê tổng lương và số nhân viên theo phòng ban
    public List<Statistics> getStatisticsByDepartment() {
        List<Statistics> stats = new ArrayList<>();
        Cursor cursor = db.rawQuery(
                "SELECT department, SUM(salary), COUNT(*) FROM Employee GROUP BY department",
                null
        );
        while (cursor.moveToNext()) {
            String department = cursor.getString(0);
            double totalSalary = cursor.getDouble(1);
            int employeeCount = cursor.getInt(2);
            stats.add(new Statistics(department, totalSalary, employeeCount));
        }
        cursor.close();
        return stats;
    }
}
