package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Salary;

public class SalaryDAO {
    private final SQLiteDatabase db;

    public SalaryDAO(Context context) {
        db = new DatabaseHelper(context).getReadableDatabase();
    }

    public List<Salary> calculateSalaries(int baseSalary, int workDays) {
        List<Salary> salaries = new ArrayList<>();
        Cursor cursor = db.query(DatabaseHelper.TABLE_EMPLOYEE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Employee employee = new Employee(
                    cursor.getInt(0),                 // id
                    cursor.getString(1),              // name
                    cursor.getString(2),              // email
                    cursor.getString(3),              // password
                    cursor.getString(4),              // position
                    cursor.getString(5),              // department
                    cursor.getInt(6) == 1             // isManager
            );

            int salary = baseSalary * workDays;
            if (employee.isManager()) salary = (int)(salary * 1.5);

            salaries.add(new Salary(employee.getName(), employee.getPosition(), salary));
        }
        cursor.close();
        return salaries;
    }
}
