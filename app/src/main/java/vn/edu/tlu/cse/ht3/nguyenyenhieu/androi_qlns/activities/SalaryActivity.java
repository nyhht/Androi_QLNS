package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters.SalaryAdapter;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.SalaryDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Salary;

public class SalaryActivity extends AppCompatActivity {

    private RecyclerView rvSalary;
    private SalaryAdapter salaryAdapter;
    private Spinner spnMonth, spnDepartment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);

        // Gán view
        rvSalary = findViewById(R.id.rvSalary);
        spnMonth = findViewById(R.id.spnMonth);
        spnDepartment = findViewById(R.id.spnDepartment);

        // Back button
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Load dữ liệu (ví dụ tĩnh)
        SalaryDAO salaryDAO = new SalaryDAO(this);
        List<Salary> salaries = salaryDAO.calculateSalaries("06/2025", 500000, 22);

        salaryAdapter = new SalaryAdapter(this, salaries);
        rvSalary.setLayoutManager(new LinearLayoutManager(this));
        rvSalary.setAdapter(salaryAdapter);
    }
}
