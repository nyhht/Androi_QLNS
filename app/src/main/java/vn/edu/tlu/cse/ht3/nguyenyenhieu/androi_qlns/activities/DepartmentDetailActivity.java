package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Department;

public class DepartmentDetailActivity extends AppCompatActivity {

    private TextView tvName, tvDescription, tvLeader, tvEmployeeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_detail);

        // Ánh xạ view
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        tvLeader = findViewById(R.id.tvLeader);
        tvEmployeeCount = findViewById(R.id.tvEmployeeCount);
        ImageView btnBack = findViewById(R.id.btnBack);

        // Xử lý nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Lấy dữ liệu phòng ban từ Intent
        Department department = (Department) getIntent().getSerializableExtra("department");
        if (department != null) {
            tvName.setText(department.getName());
            tvDescription.setText(department.getDescription());
            tvLeader.setText(department.getLeader());
            tvEmployeeCount.setText(String.valueOf(department.getEmployeeCount()));
        }
    }
}
