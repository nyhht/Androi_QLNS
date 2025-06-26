package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;

public class EmployeeDAO {

    private final SQLiteDatabase db;

    public EmployeeDAO(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    // Thêm nhân viên mới
    public long insertEmployee(Employee employee) {
        ContentValues values = new ContentValues();
        values.put("name", employee.getName());
        values.put("email", employee.getEmail());
        values.put("password", employee.getPassword());
        values.put("position", employee.getPosition());
        values.put("department", employee.getDepartment());
        values.put("salary", employee.getSalary());
        values.put("isManager", employee.isManager() ? 1 : 0);
        values.put("dob", employee.getDob());
        values.put("phone", employee.getPhone());
        return db.insert("Employee", null, values);
    }

    // Cập nhật nhân viên
    public int updateEmployee(Employee employee) {
        ContentValues values = new ContentValues();
        values.put("name", employee.getName());
        values.put("email", employee.getEmail());
        values.put("password", employee.getPassword());
        values.put("position", employee.getPosition());
        values.put("department", employee.getDepartment());
        values.put("salary", employee.getSalary());
        values.put("isManager", employee.isManager() ? 1 : 0);
        values.put("dob", employee.getDob());
        values.put("phone", employee.getPhone());
        return db.update("Employee", values, "id=?", new String[]{String.valueOf(employee.getId())});
    }

    // Xóa nhân viên
    public int delete(int id) {
        return db.delete("Employee", "id=?", new String[]{String.valueOf(id)});
    }

    // Lấy toàn bộ nhân viên
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Employee", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                String position = cursor.getString(cursor.getColumnIndexOrThrow("position"));
                String department = cursor.getString(cursor.getColumnIndexOrThrow("department"));
                double salary = cursor.getDouble(cursor.getColumnIndexOrThrow("salary"));
                boolean isManager = cursor.getInt(cursor.getColumnIndexOrThrow("isManager")) == 1;
                String dob = cursor.getString(cursor.getColumnIndexOrThrow("dob"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                list.add(new Employee(id, name, email, password, position, department, isManager, salary, dob, phone));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // Kiểm tra đăng nhập
    public Employee checkLogin(String email, String password) {
        Cursor cursor = db.rawQuery(
                "SELECT * FROM Employee WHERE email = ? AND password = ?",
                new String[]{email, password}
        );

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String position = cursor.getString(cursor.getColumnIndexOrThrow("position"));
            String department = cursor.getString(cursor.getColumnIndexOrThrow("department"));
            double salary = cursor.getDouble(cursor.getColumnIndexOrThrow("salary"));
            boolean isManager = cursor.getInt(cursor.getColumnIndexOrThrow("isManager")) == 1;
            String dob = cursor.getString(cursor.getColumnIndexOrThrow("dob"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));

            cursor.close();
            return new Employee(id, name, email, password, position, department, isManager, salary, dob, phone);
        }

        cursor.close();
        return null;
    }
}
