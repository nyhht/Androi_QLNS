package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "company_app.db";
    private static final int DATABASE_VERSION = 1;

    // Bảng Chi tiết nghỉ phép
    public static final String TABLE_LEAVE_REQUESTS = "leave_requests";
    public static final String COL_LR_ID = "id";
    public static final String COL_LR_FULL_NAME = "full_name";
    public static final String COL_LR_LEAVE_DATE = "leave_date";
    public static final String COL_LR_POSITION = "position";
    public static final String COL_LR_DEPARTMENT = "department";
    public static final String COL_LR_EMAIL = "email";
    public static final String COL_LR_REASON = "reason";

    // Bảng Lịch làm việc (cho chức năng 2)
    public static final String TABLE_WORK_SCHEDULE = "work_schedule";
    public static final String COL_WS_ID = "id";
    public static final String COL_WS_DATE = "schedule_date"; // Để lưu lịch làm việc theo ngày
    public static final String COL_WS_CHECK_IN_TIME = "check_in_time"; // Thời gian check-in (ví dụ: "08:00")
    public static final String COL_WS_CHECK_OUT_TIME = "check_out_time"; // Thời gian check-out (ví dụ: "17:00")
    public static final String COL_WS_TYPE = "entry_type"; // "CheckIn" hoặc "CheckOut" cho các dòng riêng lẻ trong lịch sử chấm công

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng leave_requests
        String CREATE_LEAVE_REQUESTS_TABLE = "CREATE TABLE " + TABLE_LEAVE_REQUESTS + "("
                + COL_LR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_LR_FULL_NAME + " TEXT NOT NULL,"
                + COL_LR_LEAVE_DATE + " TEXT NOT NULL,"
                + COL_LR_POSITION + " TEXT,"
                + COL_LR_DEPARTMENT + " TEXT,"
                + COL_LR_EMAIL + " TEXT,"
                + COL_LR_REASON + " TEXT"
                + ")";
        db.execSQL(CREATE_LEAVE_REQUESTS_TABLE);

        // Tạo bảng work_schedule
        String CREATE_WORK_SCHEDULE_TABLE = "CREATE TABLE " + TABLE_WORK_SCHEDULE + "("
                + COL_WS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_WS_DATE + " TEXT NOT NULL,"
                + COL_WS_CHECK_IN_TIME + " TEXT," // Có thể null nếu chỉ là check-out
                + COL_WS_CHECK_OUT_TIME + " TEXT," // Có thể null nếu chỉ là check-in
                + COL_WS_TYPE + " TEXT" // "CheckIn" hoặc "CheckOut"
                + ")";
        db.execSQL(CREATE_WORK_SCHEDULE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại và tạo lại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEAVE_REQUESTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORK_SCHEDULE);
        onCreate(db);
    }

    // --- Các phương thức CRUD cho Chi tiết nghỉ phép ---

    public long addLeaveRequest(String fullName, String leaveDate, String position,
                                String department, String email, String reason) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LR_FULL_NAME, fullName);
        values.put(COL_LR_LEAVE_DATE, leaveDate);
        values.put(COL_LR_POSITION, position);
        values.put(COL_LR_DEPARTMENT, department);
        values.put(COL_LR_EMAIL, email);
        values.put(COL_LR_REASON, reason);

        long id = db.insert(TABLE_LEAVE_REQUESTS, null, values);
        db.close();
        return id;
    }

    public Cursor getLeaveRequest(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LEAVE_REQUESTS,
                new String[]{COL_LR_ID, COL_LR_FULL_NAME, COL_LR_LEAVE_DATE, COL_LR_POSITION,
                        COL_LR_DEPARTMENT, COL_LR_EMAIL, COL_LR_REASON},
                COL_LR_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public List<String[]> getAllLeaveRequests() {
        List<String[]> leaveRequests = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_LEAVE_REQUESTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String[] request = new String[7]; // ID, FullName, LeaveDate, Position, Department, Email, Reason
                request[0] = String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(COL_LR_ID)));
                request[1] = cursor.getString(cursor.getColumnIndexOrThrow(COL_LR_FULL_NAME));
                request[2] = cursor.getString(cursor.getColumnIndexOrThrow(COL_LR_LEAVE_DATE));
                request[3] = cursor.getString(cursor.getColumnIndexOrThrow(COL_LR_POSITION));
                request[4] = cursor.getString(cursor.getColumnIndexOrThrow(COL_LR_DEPARTMENT));
                request[5] = cursor.getString(cursor.getColumnIndexOrThrow(COL_LR_EMAIL));
                request[6] = cursor.getString(cursor.getColumnIndexOrThrow(COL_LR_REASON));
                leaveRequests.add(request);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return leaveRequests;
    }

    // --- Các phương thức CRUD cho Lịch làm việc (chức năng 2) ---

    public long addWorkEntry(String date, String checkInTime, String checkOutTime, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_WS_DATE, date);
        values.put(COL_WS_CHECK_IN_TIME, checkInTime);
        values.put(COL_WS_CHECK_OUT_TIME, checkOutTime);
        values.put(COL_WS_TYPE, type);
        long id = db.insert(TABLE_WORK_SCHEDULE, null, values);
        db.close();
        return id;
    }

    public Cursor getWorkEntriesForDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WORK_SCHEDULE,
                new String[]{COL_WS_ID, COL_WS_DATE, COL_WS_CHECK_IN_TIME, COL_WS_CHECK_OUT_TIME, COL_WS_TYPE},
                COL_WS_DATE + "=?",
                new String[]{date}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Phương thức để lấy các Check-in/Check-out cho một ngày cụ thể
    public List<String[]> getDailyWorkSchedule(String date) {
        List<String[]> schedule = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_WS_CHECK_IN_TIME + ", " + COL_WS_CHECK_OUT_TIME +
                " FROM " + TABLE_WORK_SCHEDULE +
                " WHERE " + COL_WS_DATE + " = ? ORDER BY " + COL_WS_ID + " ASC";
        Cursor cursor = db.rawQuery(query, new String[]{date});

        if (cursor.moveToFirst()) {
            do {
                String checkIn = cursor.getString(cursor.getColumnIndexOrThrow(COL_WS_CHECK_IN_TIME));
                String checkOut = cursor.getString(cursor.getColumnIndexOrThrow(COL_WS_CHECK_OUT_TIME));
                schedule.add(new String[]{checkIn, checkOut});
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return schedule;
    }
}

