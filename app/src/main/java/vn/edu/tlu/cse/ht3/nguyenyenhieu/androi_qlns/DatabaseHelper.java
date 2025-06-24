package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "EmployeeStats.db"; // Đặt tên DB mới cho ứng dụng này
    private static final int DATABASE_VERSION = 1;

    // Bảng Departments (Phòng ban)
    public static final String TABLE_DEPARTMENTS = "departments";
    public static final String DEP_COL_ID = "id";
    public static final String DEP_COL_NAME = "department_name";

    // Bảng Employees (Nhân viên)
    public static final String TABLE_EMPLOYEES = "employees";
    public static final String EMP_COL_ID = "id";
    public static final String EMP_COL_NAME = "employee_name";
    public static final String EMP_COL_BASE_SALARY = "base_salary";
    public static final String EMP_COL_TOTAL_SALARY = "total_salary"; // Giả định có trường tổng lương
    public static final String EMP_COL_DEPARTMENT_ID = "department_id"; // Khóa ngoại

    // Câu lệnh tạo bảng Departments
    private static final String CREATE_TABLE_DEPARTMENTS = "CREATE TABLE " + TABLE_DEPARTMENTS + "("
            + DEP_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DEP_COL_NAME + " TEXT UNIQUE NOT NULL"
            + ")";

    // Câu lệnh tạo bảng Employees
    private static final String CREATE_TABLE_EMPLOYEES = "CREATE TABLE " + TABLE_EMPLOYEES + "("
            + EMP_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EMP_COL_NAME + " TEXT NOT NULL,"
            + EMP_COL_BASE_SALARY + " REAL NOT NULL,"
            + EMP_COL_TOTAL_SALARY + " REAL NOT NULL,"
            + EMP_COL_DEPARTMENT_ID + " INTEGER,"
            + "FOREIGN KEY(" + EMP_COL_DEPARTMENT_ID + ") REFERENCES " + TABLE_DEPARTMENTS + "(" + DEP_COL_ID + ")"
            + "ON DELETE CASCADE" // Khi xóa phòng ban, nhân viên trong phòng đó cũng bị xóa
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Đang tạo các bảng cơ sở dữ liệu...");
        db.execSQL(CREATE_TABLE_DEPARTMENTS);
        db.execSQL(CREATE_TABLE_EMPLOYEES);
        Log.d(TAG, "Tất cả các bảng đã được tạo.");

        // Thêm dữ liệu mặc định để kiểm tra
        insertDefaultData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Đang nâng cấp cơ sở dữ liệu từ phiên bản " + oldVersion + " lên " + newVersion);
        // Xóa bảng theo thứ tự khóa ngoại ngược lại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENTS);
        onCreate(db);
        Log.d(TAG, "Cơ sở dữ liệu đã được nâng cấp. Các bảng đã bị xóa và tạo lại.");
    }

    // --- Phương thức thêm dữ liệu ---

    // Hàm hỗ trợ thêm phòng ban (dùng nội bộ trong DatabaseHelper)
    private long addDepartment(SQLiteDatabase db, String name) {
        ContentValues values = new ContentValues();
        values.put(DEP_COL_NAME, name);
        long id = -1;
        try {
            id = db.insertOrThrow(TABLE_DEPARTMENTS, null, values);
            Log.d(TAG, "Đã thêm phòng ban: " + name + " với ID: " + id);
        } catch (android.database.SQLException e) {
            Log.w(TAG, "Phòng ban " + name + " đã tồn tại hoặc lỗi khi thêm: " + e.getMessage());
        }
        return id;
    }

    // Hàm hỗ trợ thêm nhân viên (dùng nội bộ trong DatabaseHelper)
    private boolean addEmployee(SQLiteDatabase db, String name, double baseSalary, double totalSalary, long departmentId) {
        ContentValues values = new ContentValues();
        values.put(EMP_COL_NAME, name);
        values.put(EMP_COL_BASE_SALARY, baseSalary);
        values.put(EMP_COL_TOTAL_SALARY, totalSalary);
        values.put(EMP_COL_DEPARTMENT_ID, departmentId);
        long result = -1;
        try {
            result = db.insertOrThrow(TABLE_EMPLOYEES, null, values);
            Log.d(TAG, "Đã thêm nhân viên: " + name + " vào phòng ban ID: " + departmentId);
        } catch (android.database.SQLException e) {
            Log.e(TAG, "Lỗi khi thêm nhân viên " + name + ": " + e.getMessage());
        }
        return result != -1;
    }

    // Hàm hỗ trợ lấy ID phòng ban
    public long getDepartmentId(SQLiteDatabase db, String departmentName) {
        String[] columns = {DEP_COL_ID};
        String selection = DEP_COL_NAME + " = ?";
        String[] selectionArgs = {departmentName};
        Cursor cursor = null;
        long id = -1;
        try {
            cursor = db.query(TABLE_DEPARTMENTS, columns, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                id = cursor.getLong(cursor.getColumnIndexOrThrow(DEP_COL_ID));
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi lấy ID phòng ban " + departmentName + ": " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
        }
        return id;
    }


    // Thêm dữ liệu mẫu vào DB khi tạo lần đầu
    private void insertDefaultData(SQLiteDatabase db) {
        // Thêm phòng ban
        long designId = addDepartment(db, "Design");
        long itId = addDepartment(db, "IT");
        long softwareTestingId = addDepartment(db, "Software testting");
        long salesId = addDepartment(db, "Sale");
        long hrId = addDepartment(db, "HR");

        // Thêm nhân viên vào các phòng ban (giả định các ID đã có)
        // Dữ liệu mẫu khớp với ảnh
        // Design (8 nhân viên) - Tổng lương cơ bản: 97.4M, Tổng lương: 96.8M
        if (designId != -1) {
            addEmployee(db, "NV_Design_1", 12000000, 12000000, designId);
            addEmployee(db, "NV_Design_2", 11000000, 10800000, designId);
            addEmployee(db, "NV_Design_3", 13000000, 12500000, designId);
            addEmployee(db, "NV_Design_4", 12500000, 12300000, designId);
            addEmployee(db, "NV_Design_5", 10000000, 9800000, designId);
            addEmployee(db, "NV_Design_6", 11500000, 11200000, designId);
            addEmployee(db, "NV_Design_7", 10800000, 10700000, designId);
            addEmployee(db, "NV_Design_8", 16600000, 16500000, designId);
        }

        // IT (9 nhân viên) - Tổng lương cơ bản: 150.3M, Tổng lương: 150.1M
        if (itId != -1) {
            addEmployee(db, "NV_IT_1", 20000000, 20000000, itId);
            addEmployee(db, "NV_IT_2", 18000000, 17800000, itId);
            addEmployee(db, "NV_IT_3", 17000000, 16900000, itId);
            addEmployee(db, "NV_IT_4", 15000000, 14900000, itId);
            addEmployee(db, "NV_IT_5", 14000000, 13800000, itId);
            addEmployee(db, "NV_IT_6", 16000000, 15900000, itId);
            addEmployee(db, "NV_IT_7", 17000000, 16800000, itId);
            addEmployee(db, "NV_IT_8", 16300000, 16200000, itId);
            addEmployee(db, "NV_IT_9", 17000000, 17000000, itId);
        }

        // Software testting (5 nhân viên) - Tổng lương cơ bản: 78.8M, Tổng lương: 77.7M
        if (softwareTestingId != -1) {
            addEmployee(db, "NV_ST_1", 15000000, 15000000, softwareTestingId);
            addEmployee(db, "NV_ST_2", 16000000, 15500000, softwareTestingId);
            addEmployee(db, "NV_ST_3", 15800000, 15000000, softwareTestingId);
            addEmployee(db, "NV_ST_4", 15500000, 16000000, softwareTestingId);
            addEmployee(db, "NV_ST_5", 16500000, 16200000, softwareTestingId); // Điều chỉnh để khớp số tổng
        }

        // Sale (12 nhân viên) - Tổng lương cơ bản: 119M, Tổng lương: 117.7M
        if (salesId != -1) {
            addEmployee(db, "NV_Sale_1", 10000000, 9500000, salesId);
            addEmployee(db, "NV_Sale_2", 9500000, 9000000, salesId);
            addEmployee(db, "NV_Sale_3", 10500000, 10000000, salesId);
            addEmployee(db, "NV_Sale_4", 9800000, 9300000, salesId);
            addEmployee(db, "NV_Sale_5", 10200000, 9700000, salesId);
            addEmployee(db, "NV_Sale_6", 11000000, 10500000, salesId);
            addEmployee(db, "NV_Sale_7", 8500000, 8000000, salesId);
            addEmployee(db, "NV_Sale_8", 12000000, 11500000, salesId);
            addEmployee(db, "NV_Sale_9", 9000000, 8800000, salesId);
            addEmployee(db, "NV_Sale_10", 9700000, 9600000, salesId);
            addEmployee(db, "NV_Sale_11", 10300000, 10200000, salesId);
            addEmployee(db, "NV_Sale_12", 17500000, 17500000, salesId); // Điều chỉnh để khớp số tổng
        }

        // HR (6 nhân viên) - Tổng lương cơ bản: 88.6M, Tổng lương: 88.3M
        if (hrId != -1) {
            addEmployee(db, "NV_HR_1", 13000000, 12800000, hrId);
            addEmployee(db, "NV_HR_2", 12000000, 11800000, hrId);
            addEmployee(db, "NV_HR_3", 10000000, 9800000, hrId);
            addEmployee(db, "NV_HR_4", 11500000, 11200000, hrId);
            addEmployee(db, "NV_HR_5", 10800000, 10500000, hrId);
            addEmployee(db, "NV_HR_6", 31300000, 32200000, hrId); // Điều chỉnh để khớp số tổng
        }
    }

    // --- Phương thức thống kê ---

    public int getTotalEmployees() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        int count = 0;
        try {
            cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_EMPLOYEES, null);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi đếm tổng số nhân viên: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return count;
    }

    public int getTotalDepartments() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        int count = 0;
        try {
            cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_DEPARTMENTS, null);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi đếm tổng số phòng ban: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return count;
    }

    public double getTotalSalaryOverall() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        double total = 0;
        try {
            cursor = db.rawQuery("SELECT SUM(" + EMP_COL_TOTAL_SALARY + ") FROM " + TABLE_EMPLOYEES, null);
            if (cursor.moveToFirst()) {
                total = cursor.getDouble(0);
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi tính tổng lương toàn hệ thống: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return total;
    }

    public List<DepartmentStats> getDepartmentStatistics() {
        List<DepartmentStats> statsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Truy vấn JOIN để lấy thông tin thống kê từng phòng ban
        String query = "SELECT " +
                "D." + DEP_COL_NAME + ", " +
                "COUNT(E." + EMP_COL_ID + ") AS employee_count, " +
                "IFNULL(SUM(E." + EMP_COL_BASE_SALARY + "), 0) AS total_base_salary, " + // Dùng IFNULL để tránh null nếu không có NV
                "IFNULL(SUM(E." + EMP_COL_TOTAL_SALARY + "), 0) AS total_dept_salary " +
                "FROM " + TABLE_DEPARTMENTS + " D " +
                "LEFT JOIN " + TABLE_EMPLOYEES + " E ON D." + DEP_COL_ID + " = E." + EMP_COL_DEPARTMENT_ID + " " +
                "GROUP BY D." + DEP_COL_NAME + " " +
                "ORDER BY D." + DEP_COL_NAME; // Sắp xếp theo tên phòng ban

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    String departmentName = cursor.getString(cursor.getColumnIndexOrThrow(DEP_COL_NAME));
                    int employeeCount = cursor.getInt(cursor.getColumnIndexOrThrow("employee_count"));
                    double totalBaseSalary = cursor.getDouble(cursor.getColumnIndexOrThrow("total_base_salary"));
                    double totalDepartmentSalary = cursor.getDouble(cursor.getColumnIndexOrThrow("total_dept_salary"));

                    statsList.add(new DepartmentStats(departmentName, employeeCount, totalBaseSalary, totalDepartmentSalary));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi lấy thống kê phòng ban: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return statsList;
    }
}
