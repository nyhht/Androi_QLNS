package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Department;

public class DepartmentDAO {
    private final SQLiteDatabase db;

    public DepartmentDAO(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    // Thêm mới phòng ban
    public long insertDepartment(Department department) {
        ContentValues values = new ContentValues();
        values.put("name", department.getName());
        values.put("description", department.getDescription());
        values.put("leader", department.getLeader());
        values.put("employeeCount", department.getEmployeeCount());
        return db.insert(DatabaseHelper.TABLE_DEPARTMENT, null, values);
    }

    // Cập nhật phòng ban
    public int updateDepartment(Department department) {
        ContentValues values = new ContentValues();
        values.put("name", department.getName());
        values.put("description", department.getDescription());
        values.put("leader", department.getLeader());
        values.put("employeeCount", department.getEmployeeCount());

        return db.update(
                DatabaseHelper.TABLE_DEPARTMENT,
                values,
                "id = ?",
                new String[]{String.valueOf(department.getId())}
        );
    }

    // Lấy danh sách tất cả phòng ban
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        Cursor cursor = db.query(DatabaseHelper.TABLE_DEPARTMENT, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Department d = new Department(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4)
            );
            departments.add(d);
        }
        cursor.close();
        return departments;
    }

    // Lấy danh sách tên phòng ban (phục vụ Spinner)
    public List<String> getDepartmentNames() {
        List<String> names = new ArrayList<>();
        Cursor cursor = db.query(DatabaseHelper.TABLE_DEPARTMENT, new String[]{"name"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            names.add(cursor.getString(0));
        }
        cursor.close();
        return names;
    }
}
