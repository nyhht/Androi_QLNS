package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;

public class EmployeeActivity extends AppCompatActivity {

    private GridLayout gridEmployeeFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        gridEmployeeFunctions = findViewById(R.id.gridEmployeeFunctions);

        // Chấm công
        gridEmployeeFunctions.getChildAt(0).setOnClickListener(v ->
                startActivity(new Intent(this, TimekeepingActivity.class)));

        // Thông tin nhân viên
        gridEmployeeFunctions.getChildAt(1).setOnClickListener(v ->
                startActivity(new Intent(this, EmployeeDetailActivity.class)));

        // Xem bảng lương
        gridEmployeeFunctions.getChildAt(2).setOnClickListener(v ->
                startActivity(new Intent(this, SalaryActivity.class)));

        // Xin nghỉ phép
        gridEmployeeFunctions.getChildAt(3).setOnClickListener(v ->
                startActivity(new Intent(this, LeaveRequestFormActivity.class)));
    }
}
