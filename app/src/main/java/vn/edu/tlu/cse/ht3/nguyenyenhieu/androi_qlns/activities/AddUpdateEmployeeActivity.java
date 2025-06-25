package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.EmployeeDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;

public class AddUpdateEmployeeActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPosition, edtDepartment, edtSalary;
    private Button btnSave, btnCancel;
    private EmployeeDAO employeeDAO;
    private int employeeId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_employee);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPosition = findViewById(R.id.edtPosition);
        edtDepartment = findViewById(R.id.edtDepartment);
        edtSalary = findViewById(R.id.edtSalary);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        employeeDAO = new EmployeeDAO(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("employee")) {
            Employee employee = (Employee) intent.getSerializableExtra("employee");
            if (employee != null) {
                employeeId = employee.getId();
                edtName.setText(employee.getName());
                edtEmail.setText(employee.getEmail());
                edtPosition.setText(employee.getPosition());
                edtDepartment.setText(employee.getDepartment());
                edtSalary.setText(String.valueOf(employee.getSalary()));
            }
        }

        btnSave.setOnClickListener(v -> saveEmployee());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void saveEmployee() {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String position = edtPosition.getText().toString().trim();
        String department = edtDepartment.getText().toString().trim();
        String salaryStr = edtSalary.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(position)
                || TextUtils.isEmpty(department) || TextUtils.isEmpty(salaryStr)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        double salary = Double.parseDouble(salaryStr);

        Employee employee = new Employee(employeeId, name, email, position, department, salary);

        if (employeeId == -1) {
            employeeDAO.insertEmployee(employee);
            Toast.makeText(this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
        } else {
            employeeDAO.updateEmployee(employee);
            Toast.makeText(this, "Cập nhật thông tin nhân viên", Toast.LENGTH_SHORT).show();
        }

        setResult(RESULT_OK);
        finish();
    }
}
