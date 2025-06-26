package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters.DepartmentAdapter;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.DepartmentDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Department;

public class DepartmentListActivity extends AppCompatActivity {

    private RecyclerView rvDepartments;
    private ImageView btnBack, btnAdd;
    private DepartmentAdapter adapter;
    private DepartmentDAO departmentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_list);

        rvDepartments = findViewById(R.id.rvDepartments);
        btnBack = findViewById(R.id.btnBack);
        btnAdd = findViewById(R.id.btnAdd);

        departmentDAO = new DepartmentDAO(this);

        btnBack.setOnClickListener(v -> finish());

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddUpdateDepartmentActivity.class);
            startActivity(intent);
        });

        loadDepartments();
    }

    private void loadDepartments() {
        List<Department> departments = departmentDAO.getAllDepartments();
        adapter = new DepartmentAdapter(departments, this::openDepartmentDetail);
        rvDepartments.setLayoutManager(new LinearLayoutManager(this));
        rvDepartments.setAdapter(adapter);
    }

    private void openDepartmentDetail(Department department) {
        Intent intent = new Intent(this, DepartmentDetailActivity.class);
        intent.putExtra("department", department); // Truyền Serializable object
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDepartments();
    }
}
