package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.EmployeeDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;

public class EmployeeDetailActivity extends AppCompatActivity {

    TextView tvName, tvBirth, tvPhone, tvEmail, tvPosition, tvRole;
    EmployeeDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        int empId = getIntent().getIntExtra("employee_id", -1);
        dao = new EmployeeDAO(this);
        Employee e = dao.getEmployeeById(empId);

        tvName = findViewById(R.id.tvName);
        tvBirth = findViewById(R.id.tvBirth);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);
        tvPosition = findViewById(R.id.tvPosition);
        tvRole = findViewById(R.id.tvRole);

        if (e != null) {
            tvName.setText(e.getName());
            tvBirth.setText(e.getBirthday());
            tvPhone.setText(e.getPhone());
            tvEmail.setText(e.getEmail());
            tvPosition.setText(e.getPosition());
            tvRole.setText(e.isManager() ? "Quản lý" : "Nhân viên");
        }
    }
}