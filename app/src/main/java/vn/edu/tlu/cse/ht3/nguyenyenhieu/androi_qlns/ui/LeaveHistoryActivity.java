package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.ui;

// --- LeaveHistoryActivity.java ---

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.data.DatabaseHelper;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.LeaveRequest;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters.LeaveRequestAdapter;

public class LeaveHistoryActivity extends AppCompatActivity {

    private RecyclerView rvLeaveRequests;
    private LeaveRequestAdapter adapter;
    private DatabaseHelper dbHelper;
    private List<LeaveRequest> leaveRequestList;
    private TextView tvNoData; // Để hiển thị khi không có dữ liệu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_history);

        // Ánh xạ View
        rvLeaveRequests = findViewById(R.id.rv_leave_requests);
        tvNoData = findViewById(R.id.tv_no_data); // Thêm TextView này vào layout nếu muốn

        // Nút quay lại
        ImageView backButton = findViewById(R.id.btn_back_leave_history);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Quay lại màn hình trước
            }
        });

        dbHelper = new DatabaseHelper(this);
        leaveRequestList = new ArrayList<>(); // Khởi tạo danh sách trống

        // Cấu hình RecyclerView
        rvLeaveRequests.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LeaveRequestAdapter(leaveRequestList);
        rvLeaveRequests.setAdapter(adapter);

        loadLeaveRequests(); // Tải dữ liệu từ database
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLeaveRequests(); // Cập nhật lại danh sách mỗi khi Activity trở lại foreground
    }

    private void loadLeaveRequests() {
        List<LeaveRequest> fetchedRequests = dbHelper.getAllLeaveRequests();
        if (fetchedRequests.isEmpty()) {
            // Nếu không có dữ liệu, hiển thị thông báo
            rvLeaveRequests.setVisibility(View.GONE);
            // Bạn có thể thêm một TextView vào activity_leave_history.xml với id tv_no_data
            // và sau đó set text cho nó ở đây.
            // Ví dụ:
            // tvNoData = findViewById(R.id.tv_no_data); // Cần ánh xạ trong onCreate
            // tvNoData.setVisibility(View.VISIBLE);
            // tvNoData.setText("Chưa có yêu cầu nghỉ phép nào.");
            // Hiện tại, không có tvNoData trong layout, nên chỉ log hoặc toast
            // Toast.makeText(this, "Chưa có yêu cầu nghỉ phép nào.", Toast.LENGTH_SHORT).show();
        } else {
            // tvNoData.setVisibility(View.GONE); // Ẩn thông báo nếu có dữ liệu
            rvLeaveRequests.setVisibility(View.VISIBLE);
            adapter.setLeaveRequestList(fetchedRequests); // Cập nhật dữ liệu cho adapter
        }
    }
}