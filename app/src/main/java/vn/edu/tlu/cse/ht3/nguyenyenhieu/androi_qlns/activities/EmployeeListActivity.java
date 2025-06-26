package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters.EmployeeAdapter;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.EmployeeDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;

public class EmployeeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageButton btnBack, btnAdd;
    private EmployeeDAO dao;

    private static final int REQUEST_ADD_UPDATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        recyclerView = findViewById(R.id.recyclerViewEmployees);
        btnBack = findViewById(R.id.btnBack);
        btnAdd = findViewById(R.id.btnAdd);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dao = new EmployeeDAO(this);

        btnBack.setOnClickListener(v -> finish());

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(EmployeeListActivity.this, AddUpdateEmployeeActivity.class);
            startActivityForResult(intent, REQUEST_ADD_UPDATE);
        });

        loadEmployeeList();
    }

    private void loadEmployeeList() {
        List<Employee> employeeList = dao.getAllEmployees();

        EmployeeAdapter adapter = new EmployeeAdapter(this, employeeList, new EmployeeAdapter.OnEmployeeActionListener() {
            @Override
            public void onDelete(Employee employee) {
                int rowsDeleted = dao.delete(employee.getId());
                if (rowsDeleted > 0) {
                    Toast.makeText(EmployeeListActivity.this, "Đã xóa nhân viên", Toast.LENGTH_SHORT).show();
                    loadEmployeeList();
                } else {
                    Toast.makeText(EmployeeListActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onViewDetail(Employee employee) {
                Intent intent = new Intent(EmployeeListActivity.this, EmployeeDetailActivity.class);
                intent.putExtra("employee", employee);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_UPDATE && resultCode == RESULT_OK) {
            loadEmployeeList();
        }
    }
}
