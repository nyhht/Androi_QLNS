package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "LoginApp.db";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và các cột
    public static final String TABLE_USERS = "users";
    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password"; // Lưu ý: Trong ứng dụng thực tế, mật khẩu phải được băm (hashed)
    public static final String COL_ROLE = "role"; // "admin" hoặc "employee"

    // Câu lệnh tạo bảng
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_USERNAME + " TEXT UNIQUE NOT NULL," // Tên đăng nhập phải là duy nhất và không null
            + COL_PASSWORD + " TEXT NOT NULL,"
            + COL_ROLE + " TEXT NOT NULL"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating database tables...");
        db.execSQL(CREATE_TABLE_USERS);
        Log.d(TAG, "Table " + TABLE_USERS + " created.");

        // Thêm dữ liệu mặc định (admin và employee) khi tạo DB lần đầu
        insertDefaultUsers(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
        Log.d(TAG, "Database upgraded. Table " + TABLE_USERS + " dropped and recreated.");
    }

    public boolean addUser(String username, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_PASSWORD, password); // Cần băm mật khẩu
        contentValues.put(COL_ROLE, role);

        long result = db.insert(TABLE_USERS, null, contentValues);
        db.close();
        if (result == -1) {
            Log.e(TAG, "Failed to add user: " + username);
            return false;
        } else {
            Log.d(TAG, "User " + username + " added with ID: " + result);
            return true;
        }
    }

    public String checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_ROLE};
        String selection = COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = null;
        String role = null;
        try {
            cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                role = cursor.getString(cursor.getColumnIndexOrThrow(COL_ROLE));
                Log.d(TAG, "User " + username + " found with role: " + role);
            } else {
                Log.d(TAG, "User " + username + " not found or password incorrect.");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error checking user: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return role;
    }

    public boolean doesUserExist(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_ID};
        String selection = COL_USERNAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = null;
        boolean exists = false;
        try {
            cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
            exists = cursor.getCount() > 0;
            if (exists) {
                Log.d(TAG, "Username " + username + " already exists.");
            } else {
                Log.d(TAG, "Username " + username + " does not exist.");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error checking if user exists: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return exists;
    }

    // Thêm dữ liệu mặc định cho Admin và Employee khi tạo DB lần đầu
    private void insertDefaultUsers(SQLiteDatabase db) {
        // Sử dụng một bản sao tạm thời của doesUserExist để không đóng DB khi đang trong onCreate
        if (!tempDoesUserExist(db, "admin")) {
            ContentValues adminValues = new ContentValues();
            adminValues.put(COL_USERNAME, "admin");
            adminValues.put(COL_PASSWORD, "admin123"); // Cần băm mật khẩu
            adminValues.put(COL_ROLE, "admin");
            long result = db.insert(TABLE_USERS, null, adminValues);
            Log.d(TAG, "Added default admin user, result: " + result);
        } else {
            Log.d(TAG, "Default admin user already exists.");
        }

        if (!tempDoesUserExist(db, "employee")) {
            ContentValues employeeValues = new ContentValues();
            employeeValues.put(COL_USERNAME, "employee");
            employeeValues.put(COL_PASSWORD, "employee123"); // Cần băm mật khẩu
            employeeValues.put(COL_ROLE, "employee");
            long result = db.insert(TABLE_USERS, null, employeeValues);
            Log.d(TAG, "Added default employee user, result: " + result);
        } else {
            Log.d(TAG, "Default employee user already exists.");
        }
    }

    // Phương thức kiểm tra tồn tại người dùng tạm thời, không đóng DB
    private boolean tempDoesUserExist(SQLiteDatabase db, String username) {
        String[] columns = {COL_ID};
        String selection = COL_USERNAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = null;
        boolean exists = false;
        try {
            cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
            exists = cursor.getCount() > 0;
        } catch (Exception e) {
            Log.e(TAG, "Error in tempDoesUserExist: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return exists;
    }

}