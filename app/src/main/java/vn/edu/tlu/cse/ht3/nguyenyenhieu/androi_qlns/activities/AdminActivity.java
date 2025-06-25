package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;

public class AdminActivity extends AppCompatActivity {

    private GridLayout gridFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        gridFunctions = findViewById(R.id.gridFunctions);

        // Chức năng: Nhân viên
        gridFunctions.getChildAt(0).setOnClickListener(v ->
                startActivity(new Intent(this, EmployeeListActivity.class)));

        // Chức năng: Phòng ban
        gridFunctions.getChildAt(1).setOnClickListener(v ->
                startActivity(new Intent(this, DepartmentListActivity.class)));

        // Chức năng: Lương
        gridFunctions.getChildAt(2).setOnClickListener(v ->
                startActivity(new Intent(this, SalaryActivity.class)));

        // Chức năng: Thống kê
        gridFunctions.getChildAt(3).setOnClickListener(v ->
                startActivity(new Intent(this, StatisticsActivity.class)));

        // Đăng xuất
        gridFunctions.getChildAt(4).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}
