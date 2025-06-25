package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters.StatisticsAdapter;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.DepartmentDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.EmployeeDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.SalaryDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Statistics;

public class StatisticsActivity extends AppCompatActivity {

    private TextView tvTotalEmployees, tvTotalDepartments, tvTotalSalary;
    private RecyclerView rvStatistics;
    private StatisticsAdapter adapter;
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;
    private SalaryDAO salaryDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        tvTotalEmployees = findViewById(R.id.tvTotalEmployees);
        tvTotalDepartments = findViewById(R.id.tvTotalDepartments);
        tvTotalSalary = findViewById(R.id.tvTotalSalary);
        rvStatistics = findViewById(R.id.rvStatistics);

        employeeDAO = new EmployeeDAO(this);
        departmentDAO = new DepartmentDAO(this);
        salaryDAO = new SalaryDAO(this);

        loadStatistics();
    }

    private void loadStatistics() {
        int totalEmployees = employeeDAO.getAllEmployees().size();
        int totalDepartments = departmentDAO.getAllDepartments().size();
        double totalSalary = salaryDAO.getTotalSalary();

        tvTotalEmployees.setText(String.valueOf(totalEmployees));
        tvTotalDepartments.setText(String.valueOf(totalDepartments));
        tvTotalSalary.setText(String.format("%.0f", totalSalary));

        List<Statistics> stats = salaryDAO.getStatisticsByDepartment();
        adapter = new StatisticsAdapter(stats);
        rvStatistics.setLayoutManager(new LinearLayoutManager(this));
        rvStatistics.setAdapter(adapter);
    }
}
