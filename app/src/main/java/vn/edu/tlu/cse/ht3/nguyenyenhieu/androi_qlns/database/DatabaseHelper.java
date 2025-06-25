package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "employee_management.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_EMPLOYEE = "Employee";
    public static final String TABLE_DEPARTMENT = "Department";
    public static final String TABLE_LEAVE = "LeaveRequest";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createEmployee = "CREATE TABLE " + TABLE_EMPLOYEE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "email TEXT UNIQUE," +
                "password TEXT," +
                "position TEXT," +
                "department TEXT," +
                "isManager INTEGER DEFAULT 0)";
        db.execSQL(createEmployee);

        String createDepartment = "CREATE TABLE " + TABLE_DEPARTMENT + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "description TEXT," +
                "leader TEXT," +
                "number INTEGER)";
        db.execSQL(createDepartment);

        String createLeave = "CREATE TABLE " + TABLE_LEAVE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "date TEXT," +
                "position TEXT," +
                "department TEXT," +
                "email TEXT," +
                "reason TEXT)";
        db.execSQL(createLeave);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEAVE);
        onCreate(db);
    }
}
