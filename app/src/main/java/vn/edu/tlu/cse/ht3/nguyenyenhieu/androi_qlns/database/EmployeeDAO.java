package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;

public class EmployeeDAO {
    private final DatabaseHelper db;

    public EmployeeDAO(Context context) {
        db = new DatabaseHelper(context);
    }

    public void insertEmployee(Employee e) {
        ContentValues cv = new ContentValues();
        cv.put("name", e.getName());
        cv.put("birthday", e.getBirthday());
        cv.put("phone", e.getPhone());
        cv.put("position", e.getPosition());
        cv.put("email", e.getEmail());
        cv.put("password", e.getPassword());
        cv.put("isManager", e.isManager() ? 1 : 0);
        db.getWritableDatabase().insert("Employee", null, cv);
    }

    public void deleteEmployee(int id) {
        db.getWritableDatabase().delete("Employee", "id=?", new String[]{String.valueOf(id)});
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        Cursor c = db.getReadableDatabase().rawQuery("SELECT * FROM Employee", null);
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                String birthday = c.getString(2);
                String phone = c.getString(3);
                String position = c.getString(4);
                String email = c.getString(5);
                String password = c.getString(6);
                boolean isManager = c.getInt(7) == 1;

                list.add(new Employee(id, name, birthday, phone, position, email, password, isManager));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }
}
