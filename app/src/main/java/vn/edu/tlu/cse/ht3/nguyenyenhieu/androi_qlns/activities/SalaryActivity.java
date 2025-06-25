package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters.SalaryAdapter;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.SalaryDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Salary;

public class SalaryActivity extends AppCompatActivity {

    private Spinner spnMonth, spnDepartment;
    private RecyclerView rvSalary;
    private SalaryAdapter salaryAdapter;
    private SalaryDAO salaryDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);

        spnMonth = findViewById(R.id.spnMonth);
        spnDepartment = findViewById(R.id.spnDepartment);
        rvSalary = findViewById(R.id.rvSalary);
        salaryDAO = new SalaryDAO(this);

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, salaryDAO.getAllMonths());
        spnMonth.setAdapter(monthAdapter);

        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, salaryDAO.getAllDepartments());
        spnDepartment.setAdapter(departmentAdapter);

        rvSalary.setLayoutManager(new LinearLayoutManager(this));

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadSalaryData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };

        spnMonth.setOnItemSelectedListener(listener);
        spnDepartment.setOnItemSelectedListener(listener);
    }

    private void loadSalaryData() {
        String selectedMonth = spnMonth.getSelectedItem().toString();
        String selectedDepartment = spnDepartment.getSelectedItem().toString();
        List<Salary> salaryList = salaryDAO.getSalaries(selectedMonth, selectedDepartment);
        salaryAdapter = new SalaryAdapter(salaryList);
        rvSalary.setAdapter(salaryAdapter);
    }
}
