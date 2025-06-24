package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class PayrollDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "payroll.db";
    private static final int DATABASE_VERSION = 1;

    // Table Name
    private static final String TABLE_PAYROLL = "payroll";

    // Table Columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_POSITION = "position";
    private static final String COLUMN_DAYS_OFF = "days_off";
    private static final String COLUMN_BASE_SALARY = "base_salary";
    private static final String COLUMN_TOTAL_SALARY = "total_salary";

    // Create table SQL query
    private static final String CREATE_TABLE_PAYROLL =
            "CREATE TABLE " + TABLE_PAYROLL + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT NOT NULL,"
                    + COLUMN_POSITION + " TEXT NOT NULL,"
                    + COLUMN_DAYS_OFF + " INTEGER DEFAULT 0,"
                    + COLUMN_BASE_SALARY + " REAL NOT NULL,"
                    + COLUMN_TOTAL_SALARY + " REAL NOT NULL"
                    + ");";

    public PayrollDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PAYROLL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYROLL);
        // Create tables again
        onCreate(db);
    }

    // Add a new employee to the database
    public long addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, employee.getName());
        values.put(COLUMN_POSITION, employee.getPosition());
        values.put(COLUMN_DAYS_OFF, employee.getDaysOff());
        values.put(COLUMN_BASE_SALARY, employee.getBaseSalary());
        values.put(COLUMN_TOTAL_SALARY, employee.getTotalSalary());

        // Inserting Row
        long id = db.insert(TABLE_PAYROLL, null, values);
        db.close(); // Closing database connection
        return id;
    }

    // Get all employees from the database
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PAYROLL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSITION)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DAYS_OFF)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_BASE_SALARY)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_SALARY))
                );
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return employeeList;
    }

    // You can add more methods here like updateEmployee, deleteEmployee, getEmployeeById if needed.
    // For this example, we'll focus on add and get all.
}