package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.EmployeeDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;

public class AddUpdateEmployeeActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPhone, edtDob, edtPosition, edtDepartment, edtSalary, edtPassword;
    private Button btnAdd, btnUpdate, btnCancel;
    private ImageView btnBack;
    private Switch switchRole;
    private EmployeeDAO dao;
    private Employee currentEmployee = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_employee);

        dao = new EmployeeDAO(this);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtDob = findViewById(R.id.edtDob);
        edtPosition = findViewById(R.id.edtPosition);
        edtDepartment = findViewById(R.id.edtDepartment);
        edtSalary = findViewById(R.id.edtSalary);
        edtPassword = findViewById(R.id.edtPassword);
        switchRole = findViewById(R.id.switchRole);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());
        btnCancel.setOnClickListener(v -> finish());
        edtDob.setOnClickListener(v -> showDatePicker());

        // Kiểm tra nếu là cập nhật
        if (getIntent().hasExtra("employee")) {
            currentEmployee = (Employee) getIntent().getSerializableExtra("employee");
            if (currentEmployee != null) {
                edtName.setText(currentEmployee.getName());
                edtEmail.setText(currentEmployee.getEmail());
                edtPhone.setText(currentEmployee.getPhone());
                edtDob.setText(currentEmployee.getDob());
                edtPosition.setText(currentEmployee.getPosition());
                edtDepartment.setText(currentEmployee.getDepartment());
                edtSalary.setText(String.valueOf(currentEmployee.getSalary()));
                edtPassword.setText(currentEmployee.getPassword());
                switchRole.setChecked(currentEmployee.isManager());

                btnAdd.setVisibility(Button.GONE);
                btnUpdate.setVisibility(Button.VISIBLE);
            }
        } else {
            btnAdd.setVisibility(Button.VISIBLE);
            btnUpdate.setVisibility(Button.GONE);
        }

        btnAdd.setOnClickListener(v -> saveNewEmployee());
        btnUpdate.setOnClickListener(v -> updateEmployee());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            String selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
            edtDob.setText(selectedDate);
        }, y, m, d);
        dialog.show();
    }

    private void saveNewEmployee() {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String dob = edtDob.getText().toString().trim();
        String position = edtPosition.getText().toString().trim();
        String department = edtDepartment.getText().toString().trim();
        String salaryStr = edtSalary.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        boolean isManager = switchRole.isChecked();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || dob.isEmpty()
                || position.isEmpty() || department.isEmpty() || salaryStr.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        double salary = Double.parseDouble(salaryStr);

        Employee newEmp = new Employee(name, email, password, position, department, isManager, salary, dob, phone);
        long id = dao.insertEmployee(newEmp);
        if (id > 0) {
            Toast.makeText(this, "Đã thêm nhân viên", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateEmployee() {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String dob = edtDob.getText().toString().trim();
        String position = edtPosition.getText().toString().trim();
        String department = edtDepartment.getText().toString().trim();
        String salaryStr = edtSalary.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        boolean isManager = switchRole.isChecked();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || dob.isEmpty()
                || position.isEmpty() || department.isEmpty() || salaryStr.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        double salary = Double.parseDouble(salaryStr);

        currentEmployee.setName(name);
        currentEmployee.setEmail(email);
        currentEmployee.setPhone(phone);
        currentEmployee.setDob(dob);
        currentEmployee.setPosition(position);
        currentEmployee.setDepartment(department);
        currentEmployee.setPassword(password);
        currentEmployee.setManager(isManager);
        currentEmployee.setSalary(salary);

        int rows = dao.updateEmployee(currentEmployee);
        if (rows > 0) {
            Toast.makeText(this, "Đã cập nhật nhân viên", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
