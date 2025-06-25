package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.DepartmentDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Department;

public class AddUpdateDepartmentActivity extends AppCompatActivity {

    private EditText edtName, edtDescription, edtLeader, edtNumber;
    private Button btnCancel, btnSave;
    private ImageView btnBack;
    private Department department;
    private DepartmentDAO departmentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_department);

        edtName = findViewById(R.id.edtName);
        edtDescription = findViewById(R.id.edtDescription);
        edtLeader = findViewById(R.id.edtLeader);
        edtNumber = findViewById(R.id.edtNumber);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);

        departmentDAO = new DepartmentDAO(this);

        if (getIntent().hasExtra("department")) {
            department = (Department) getIntent().getSerializableExtra("department");
            if (department != null) {
                edtName.setText(department.getName());
                edtDescription.setText(department.getDescription());
                edtLeader.setText(department.getLeader());
                edtNumber.setText(String.valueOf(department.getEmployeeCount()));
            }
        }

        btnSave.setOnClickListener(v -> saveDepartment());

        btnCancel.setOnClickListener(v -> finish());

        btnBack.setOnClickListener(v -> finish());
    }

    private void saveDepartment() {
        String name = edtName.getText().toString().trim();
        String desc = edtDescription.getText().toString().trim();
        String leader = edtLeader.getText().toString().trim();
        String numberStr = edtNumber.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(leader) || TextUtils.isEmpty(numberStr)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        int number = Integer.parseInt(numberStr);

        if (department == null) {
            Department newDept = new Department(name, desc, leader, number);
            departmentDAO.insertDepartment(newDept);
            Toast.makeText(this, "Đã thêm phòng ban", Toast.LENGTH_SHORT).show();
        } else {
            department.setName(name);
            department.setDescription(desc);
            department.setLeader(leader);
            department.setEmployeeCount(number);
            departmentDAO.updateDepartment(department);
            Toast.makeText(this, "Đã cập nhật phòng ban", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
