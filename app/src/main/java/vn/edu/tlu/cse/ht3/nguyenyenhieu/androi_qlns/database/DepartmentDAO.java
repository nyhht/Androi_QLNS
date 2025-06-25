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

    public long insert(Department department) {
        ContentValues values = new ContentValues();
        values.put("name", department.getName());
        values.put("description", department.getDescription());
        values.put("leader", department.getLeader());
        values.put("number", department.getNumber());
        return db.insert(DatabaseHelper.TABLE_DEPARTMENT, null, values);
    }

    public List<Department> getAll() {
        List<Department> list = new ArrayList<>();
        Cursor cursor = db.query(DatabaseHelper.TABLE_DEPARTMENT, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Department department = new Department(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4)
            );
            list.add(department);
        }
        cursor.close();
        return list;
    }

    public Department getById(int id) {
        Cursor cursor = db.query(DatabaseHelper.TABLE_DEPARTMENT, null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            Department department = new Department(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4)
            );
            cursor.close();
            return department;
        }
        cursor.close();
        return null;
    }
}
