package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.EmployeeDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;

public class AddUpdateEmployeeActivity extends AppCompatActivity {

    EditText edtName, edtBirth, edtPhone, edtPosition, edtEmail, edtPassword;
    Switch swRole;
    Button btnAdd, btnCancel;

    EmployeeDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_employee);

        edtName = findViewById(R.id.edtName);
        edtBirth = findViewById(R.id.edtBirth);
        edtPhone = findViewById(R.id.edtPhone);
        edtPosition = findViewById(R.id.edtPosition);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        swRole = findViewById(R.id.swRole);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        dao = new EmployeeDAO(this);

        btnAdd.setOnClickListener(v -> {
            Employee e = new Employee(
                    edtName.getText().toString(),
                    edtBirth.getText().toString(),
                    edtPhone.getText().toString(),
                    edtPosition.getText().toString(),
                    edtEmail.getText().toString(),
                    edtPassword.getText().toString(),
                    swRole.isChecked()
            );
            dao.insertEmployee(e);
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}