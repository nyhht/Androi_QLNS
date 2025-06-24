package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters.EmployeeAdapter;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.EmployeeDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;

public class EmployeeListActivity extends AppCompatActivity {

    private RecyclerView rvEmployees;
    private Button btnAddEmployee;
    private EmployeeAdapter adapter;
    private EmployeeDAO employeeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        rvEmployees = findViewById(R.id.rvEmployees);
        btnAddEmployee = findViewById(R.id.btnAddEmployee);

        employeeDAO = new EmployeeDAO(this);
        List<Employee> list = employeeDAO.getAllEmployees();

        adapter = new EmployeeAdapter(this, list);
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
        rvEmployees.setAdapter(adapter);

        btnAddEmployee.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddUpdateEmployeeActivity.class);
            startActivity(intent);
        });
    }
}
