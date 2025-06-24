package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast; // Thêm import này

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPayroll;
    private PayrollAdapter payrollAdapter;
    private List<Employee> employeeList;
    private PayrollDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPayroll = findViewById(R.id.recyclerViewPayroll);
        recyclerViewPayroll.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new PayrollDatabaseHelper(this);

        // Khởi tạo danh sách nhân viên rỗng trước
        employeeList = new ArrayList<>();
        payrollAdapter = new PayrollAdapter(employeeList);
        recyclerViewPayroll.setAdapter(payrollAdapter);

        // Kiểm tra xem có dữ liệu trong DB chưa, nếu chưa thì thêm dữ liệu mẫu
        if (dbHelper.getAllEmployees().isEmpty()) {
            addSampleData();
            Toast.makeText(this, "Đã thêm dữ liệu mẫu vào cơ sở dữ liệu!", Toast.LENGTH_LONG).show();
        }

        // Tải dữ liệu từ cơ sở dữ liệu và hiển thị
        loadPayrollData();

        // Xử lý sự kiện click cho mũi tên quay lại (nếu bạn muốn)
        findViewById(R.id.backArrow).setOnClickListener(v -> {
            finish(); // Đóng Activity hiện tại
        });

        // Bạn có thể thêm listeners cho Spinner nếu muốn lọc dữ liệu
        // Ví dụ:
        // Spinner spinnerMonth = findViewById(R.id.spinnerMonth);
        // spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        //     @Override
        //     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //         // Xử lý khi chọn tháng, tải lại dữ liệu
        //     }
        //     @Override
        //     public void onNothingSelected(AdapterView<?> parent) {}
        // });
    }

    private void addSampleData() {
        // Dữ liệu mẫu từ hình ảnh của bạn
        dbHelper.addEmployee(new Employee("Van A", "Trưởng phòng", 0, 15000000.0, 15000000.0));
        dbHelper.addEmployee(new Employee("Van B", "Nhân viên", 2, 8300000.0, 8100000.0));
        dbHelper.addEmployee(new Employee("Ha Phuong", "Nhân viên", 1, 7800000.0, 7700000.0));
        dbHelper.addEmployee(new Employee("Thi Hang", "Nhân viên", 0, 9000000.0, 9000000.0));
        dbHelper.addEmployee(new Employee("Van Anh", "Nhân viên", 3, 8600000.0, 8300000.0));
        dbHelper.addEmployee(new Employee("Mai Anh", "Nhân viên", 0, 9800000.0, 9800000.0));
    }

    private void loadPayrollData() {
        employeeList.clear(); // Xóa dữ liệu cũ
        employeeList.addAll(dbHelper.getAllEmployees()); // Tải dữ liệu mới
        payrollAdapter.notifyDataSetChanged(); // Thông báo cho adapter cập nhật UI
    }
}