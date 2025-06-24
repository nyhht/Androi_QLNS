package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class StatisticsActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView tvTotalEmployees, tvTotalDepartments, tvTotalSalaryOverall;
    private RecyclerView recyclerViewDepartmentStats;
    private DepartmentStatsAdapter adapter;
    private ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        dbHelper = new DatabaseHelper(this);

        // Ánh xạ các thành phần UI
        tvTotalEmployees = findViewById(R.id.tvTotalEmployees);
        tvTotalDepartments = findViewById(R.id.tvTotalDepartments);
        tvTotalSalaryOverall = findViewById(R.id.tvTotalSalaryOverall);
        recyclerViewDepartmentStats = findViewById(R.id.recyclerViewDepartmentStats);
        imageViewBack = findViewById(R.id.imageViewBack);

        // Cấu hình RecyclerView
        recyclerViewDepartmentStats.setLayoutManager(new LinearLayoutManager(this));

        // Tải và hiển thị dữ liệu thống kê
        loadStatisticsData();

        // Xử lý sự kiện nút quay lại
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Quay lại màn hình trước đó
            }
        });
    }

    private void loadStatisticsData() {
        // Lấy dữ liệu tổng quan
        int totalEmployees = dbHelper.getTotalEmployees();
        int totalDepartments = dbHelper.getTotalDepartments();
        double totalSalaryOverall = dbHelper.getTotalSalaryOverall();

        // Định dạng tiền tệ
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        currencyFormatter.setMinimumFractionDigits(0); // Không hiển thị số lẻ
        String formattedTotalSalaryOverall = currencyFormatter.format(totalSalaryOverall) + ".000 VND";


        // Cập nhật UI cho các số liệu tổng quan
        tvTotalEmployees.setText(String.valueOf(totalEmployees));
        tvTotalDepartments.setText(String.valueOf(totalDepartments));
        tvTotalSalaryOverall.setText(formattedTotalSalaryOverall);


        // Lấy dữ liệu thống kê chi tiết theo phòng ban
        List<DepartmentStats> departmentStatsList = dbHelper.getDepartmentStatistics();

        // Cập nhật RecyclerView
        adapter = new DepartmentStatsAdapter(departmentStatsList);
        recyclerViewDepartmentStats.setAdapter(adapter);
    }
}