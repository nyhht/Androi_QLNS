package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;

public class EmployeeDetailActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPosition, etDepartment, etSalary, etDob, etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPosition = findViewById(R.id.etPosition);
        etDepartment = findViewById(R.id.etDepartment);
        etSalary = findViewById(R.id.etSalary);
        etDob = findViewById(R.id.etDob);
        etPhone = findViewById(R.id.etPhone);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Nhận Employee truyền từ Intent
        Employee employee = (Employee) getIntent().getSerializableExtra("employee");

        if (employee != null) {
            etName.setText(employee.getName());
            etEmail.setText(employee.getEmail());
            etPosition.setText(employee.getPosition());
            etDepartment.setText(employee.getDepartment());
            etSalary.setText(String.valueOf(employee.getSalary()));
            etDob.setText(employee.getDob());       // Đảm bảo model có getDob()
            etPhone.setText(employee.getPhone());   // Đảm bảo model có getPhone()
        }
    }
}
