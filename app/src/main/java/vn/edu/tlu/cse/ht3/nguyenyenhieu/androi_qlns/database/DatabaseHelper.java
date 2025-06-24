package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "qlns.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Employee (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, birthday TEXT, phone TEXT," +
                "position TEXT, email TEXT, password TEXT," +
                "isManager INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Employee");
        onCreate(db);
    }
}