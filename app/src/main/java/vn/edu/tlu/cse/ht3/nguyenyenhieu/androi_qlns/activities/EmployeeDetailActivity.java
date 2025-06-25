package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;

public class EmployeeDetailActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPosition, etDepartment, etSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPosition = findViewById(R.id.etPosition);
        etDepartment = findViewById(R.id.etDepartment);
        etSalary = findViewById(R.id.etSalary);

        Employee employee = (Employee) getIntent().getSerializableExtra("employee");

        if (employee != null) {
            etName.setText(employee.getName());
            etEmail.setText(employee.getEmail());
            etPosition.setText(employee.getPosition());
            etDepartment.setText(employee.getDepartment());
            etSalary.setText(String.valueOf(employee.getSalary()));

            etName.setEnabled(false);
            etEmail.setEnabled(false);
            etPosition.setEnabled(false);
            etDepartment.setEnabled(false);
            etSalary.setEnabled(false);
        }
    }
}
