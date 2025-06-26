package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters.StatisticsAdapter;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.DatabaseHelper;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.SalaryDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Statistics;

public class StatisticsActivity extends AppCompatActivity {

    private TextView tvTotalEmployees, tvTotalDepartments, tvTotalSalary;
    private RecyclerView rvStatistics;
    private SalaryDAO salaryDAO;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        salaryDAO = new SalaryDAO(this);
        dbHelper = new DatabaseHelper(this);

        tvTotalEmployees = findViewById(R.id.tvTotalEmployees);
        tvTotalDepartments = findViewById(R.id.tvTotalDepartments);
        tvTotalSalary = findViewById(R.id.tvTotalSalary);
        rvStatistics = findViewById(R.id.rvStatistics);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Load statistics
        tvTotalEmployees.setText(String.valueOf(dbHelper.getEmployeeCount()));
        tvTotalDepartments.setText(String.valueOf(salaryDAO.getAllDepartments().size()));
        tvTotalSalary.setText(formatCurrency(salaryDAO.getTotalSalary()));

        List<Statistics> stats = salaryDAO.getStatisticsByDepartment();
        StatisticsAdapter adapter = new StatisticsAdapter(stats);
        rvStatistics.setLayoutManager(new LinearLayoutManager(this));
        rvStatistics.setAdapter(adapter);
    }

    private String formatCurrency(double amount) {
        return NumberFormat.getNumberInstance(new Locale("vi", "VN")).format(amount);
    }
}
