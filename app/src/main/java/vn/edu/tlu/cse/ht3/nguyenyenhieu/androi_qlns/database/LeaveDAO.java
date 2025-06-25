package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.LeaveRequest;

public class LeaveDAO {

    private final SQLiteDatabase db;

    public LeaveDAO(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    public long insert(LeaveRequest leave) {
        ContentValues values = new ContentValues();
        values.put("name", leave.getName());
        values.put("date", leave.getDate());
        values.put("position", leave.getPosition());
        values.put("department", leave.getDepartment());
        values.put("email", leave.getEmail());
        values.put("reason", leave.getReason());
        return db.insert(DatabaseHelper.TABLE_LEAVE, null, values);
    }

    public List<LeaveRequest> getAll() {
        List<LeaveRequest> list = new ArrayList<>();
        Cursor cursor = db.query(DatabaseHelper.TABLE_LEAVE, null, null, null, null, null, "id DESC");
        while (cursor.moveToNext()) {
            LeaveRequest leave = new LeaveRequest(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
            list.add(leave);
        }
        cursor.close();
        return list;
    }

    public LeaveRequest getById(int id) {
        Cursor cursor = db.query(DatabaseHelper.TABLE_LEAVE, null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            LeaveRequest leave = new LeaveRequest(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
            cursor.close();
            return leave;
        }
        cursor.close();
        return null;
    }
}
