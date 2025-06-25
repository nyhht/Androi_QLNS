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
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    // Thêm mới nhân viên
    public long insert(Employee employee) {
        ContentValues values = new ContentValues();
        values.put("name", employee.getName());
        values.put("email", employee.getEmail());
        values.put("password", employee.getPassword());
        values.put("position", employee.getPosition());
        values.put("department", employee.getDepartment());
        values.put("isManager", employee.isManager() ? 1 : 0);
        return db.insert(DatabaseHelper.TABLE_EMPLOYEE, null, values);
    }

    // Cập nhật nhân viên
    public int update(Employee employee) {
        ContentValues values = new ContentValues();
        values.put("name", employee.getName());
        values.put("email", employee.getEmail());
        values.put("password", employee.getPassword());
        values.put("position", employee.getPosition());
        values.put("department", employee.getDepartment());
        values.put("isManager", employee.isManager() ? 1 : 0);
        return db.update(DatabaseHelper.TABLE_EMPLOYEE, values, "id=?", new String[]{String.valueOf(employee.getId())});
    }

    // Xóa nhân viên theo id
    public int delete(int id) {
        return db.delete(DatabaseHelper.TABLE_EMPLOYEE, "id=?", new String[]{String.valueOf(id)});
    }

    // Trả về toàn bộ danh sách nhân viên
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        Cursor cursor = db.query(DatabaseHelper.TABLE_EMPLOYEE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Employee employee = new Employee(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("password")),
                    cursor.getString(cursor.getColumnIndexOrThrow("position")),
                    cursor.getString(cursor.getColumnIndexOrThrow("department")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("isManager")) == 1
            );
            list.add(employee);
        }
        cursor.close();
        return list;
    }

    // Lấy nhân viên theo ID
    public Employee getById(int id) {
        Cursor cursor = db.query(DatabaseHelper.TABLE_EMPLOYEE, null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            Employee employee = new Employee(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("password")),
                    cursor.getString(cursor.getColumnIndexOrThrow("position")),
                    cursor.getString(cursor.getColumnIndexOrThrow("department")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("isManager")) == 1
            );
            cursor.close();
            return employee;
        }
        cursor.close();
        return null;
    }

    // Kiểm tra đăng nhập
    public Employee checkLogin(String email, String password) {
        Cursor cursor = db.query(DatabaseHelper.TABLE_EMPLOYEE, null, "email=? AND password=?", new String[]{email, password}, null, null, null);
        if (cursor.moveToFirst()) {
            Employee employee = new Employee(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("password")),
                    cursor.getString(cursor.getColumnIndexOrThrow("position")),
                    cursor.getString(cursor.getColumnIndexOrThrow("department")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("isManager")) == 1
            );
            cursor.close();
            return employee;
        }
        cursor.close();
        return null;
    }
}
