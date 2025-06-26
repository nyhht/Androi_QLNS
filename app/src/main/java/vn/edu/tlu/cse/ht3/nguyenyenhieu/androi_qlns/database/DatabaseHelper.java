package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "employee_management.db";
    public static final int DATABASE_VERSION = 4;

    public static final String TABLE_EMPLOYEE = "Employee";
    public static final String TABLE_DEPARTMENT = "Department";
    public static final String TABLE_LEAVE = "LeaveRequest";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Nhân viên (đã thêm dob và phone)
        String createEmployee = "CREATE TABLE " + TABLE_EMPLOYEE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "email TEXT UNIQUE," +
                "password TEXT," +
                "position TEXT," +
                "department TEXT," +
                "isManager INTEGER DEFAULT 0," +
                "salary REAL," +
                "dob TEXT," +
                "phone TEXT)";
        db.execSQL(createEmployee);

        // Bảng phòng ban
        String createDepartment = "CREATE TABLE " + TABLE_DEPARTMENT + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "description TEXT," +
                "leader TEXT," +
                "employeeCount INTEGER)";
        db.execSQL(createDepartment);

        // Bảng nghỉ phép
        String createLeave = "CREATE TABLE " + TABLE_LEAVE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "date TEXT," +
                "position TEXT," +
                "department TEXT," +
                "email TEXT," +
                "reason TEXT)";
        db.execSQL(createLeave);

        // Thêm quản lý
        insertEmployee(db, "Nguyễn Văn A", "admin@gmail.com", "admin123", "Giám đốc", "Ban điều hành", 20000000, 1, "1975-03-20", "0901234567");

        // Thêm 5 nhân viên
        insertEmployee(db, "Trần Thị B", "tranb@gmail.com", "123456", "Nhân sự", "Phòng nhân sự", 10000000, 0, "1990-08-12", "0912345678");
        insertEmployee(db, "Lê Văn C", "levanc@gmail.com", "123456", "Kế toán", "Phòng tài chính", 11000000, 0, "1992-05-25", "0987654321");
        insertEmployee(db, "Phạm Thị D", "phamtd@gmail.com", "123456", "Lập trình viên", "Phòng kỹ thuật", 12000000, 0, "1994-11-10", "0965123456");
        insertEmployee(db, "Ngô Văn E", "ngovane@gmail.com", "123456", "Tester", "Phòng kỹ thuật", 9500000, 0, "1991-01-30", "0934567890");
        insertEmployee(db, "Hoàng Thị F", "hoangtf@gmail.com", "123456", "Marketing", "Phòng marketing", 10500000, 0, "1989-06-15", "0978123456");

        // Phòng ban mẫu
        insertDepartment(db, "Phòng nhân sự", "Quản lý nhân sự", "Trần Thị B", 1);
        insertDepartment(db, "Phòng kỹ thuật", "Phát triển phần mềm", "Phạm Thị D", 2);
        insertDepartment(db, "Phòng marketing", "Truyền thông", "Hoàng Thị F", 1);

        // Đơn nghỉ phép mẫu
        insertLeaveRequest(db, "Ngô Văn E", "2025-06-20", "Tester", "Phòng kỹ thuật", "ngovane@gmail.com", "Nghỉ ốm");
        insertLeaveRequest(db, "Trần Thị B", "2025-06-22", "Nhân sự", "Phòng nhân sự", "tranb@gmail.com", "Việc gia đình");
    }

    private void insertEmployee(SQLiteDatabase db, String name, String email, String password,
                                String position, String department, double salary,
                                int isManager, String dob, String phone) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);
        values.put("position", position);
        values.put("department", department);
        values.put("salary", salary);
        values.put("isManager", isManager);
        values.put("dob", dob);
        values.put("phone", phone);
        db.insert(TABLE_EMPLOYEE, null, values);
    }

    private void insertDepartment(SQLiteDatabase db, String name, String description,
                                  String leader, int employeeCount) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("leader", leader);
        values.put("employeeCount", employeeCount);
        db.insert(TABLE_DEPARTMENT, null, values);
    }

    private void insertLeaveRequest(SQLiteDatabase db, String name, String date, String position,
                                    String department, String email, String reason) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("date", date);
        values.put("position", position);
        values.put("department", department);
        values.put("email", email);
        values.put("reason", reason);
        db.insert(TABLE_LEAVE, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEAVE);
        onCreate(db);
    }

    public long addLeaveRequest(String name, String date, String position,
                                String department, String email, String reason) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("date", date);
        values.put("position", position);
        values.put("department", department);
        values.put("email", email);
        values.put("reason", reason);
        return db.insert(TABLE_LEAVE, null, values);
    }

    public int getEmployeeCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_EMPLOYEE, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }
}
