package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R; // Đảm bảo đúng đường dẫn R
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.data.DatabaseHelper; // Import DatabaseHelper
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.LeaveRequest; // Import LeaveRequest

public class LeaveRequestFormActivity extends AppCompatActivity {

    private EditText etFullName, etLeaveDate, etPosition, etDepartment, etEmail, etReason;
    private Button btnSubmitRequest;
    private DatabaseHelper dbHelper; // Khai báo biến dbHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request_form); // Đảm bảo có layout này

        // Khởi tạo DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Ánh xạ các thành phần UI
        etFullName = findViewById(R.id.etFullName); // Đảm bảo ID này tồn tại trong layout
        etLeaveDate = findViewById(R.id.etLeaveDate);
        etPosition = findViewById(R.id.etPosition);
        etDepartment = findViewById(R.id.etDepartment);
        etEmail = findViewById(R.id.etEmail);
        etReason = findViewById(R.id.etReason);
        btnSubmitRequest = findViewById(R.id.btnSubmitRequest);

        btnSubmitRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLeaveRequest();
            }
        });
    }

    private void submitLeaveRequest() {
        String fullName = etFullName.getText().toString().trim();
        String leaveDate = etLeaveDate.getText().toString().trim();
        String position = etPosition.getText().toString().trim();
        String department = etDepartment.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String reason = etReason.getText().toString().trim();

        // Kiểm tra dữ liệu đầu vào cơ bản
        if (fullName.isEmpty() || leaveDate.isEmpty() || position.isEmpty() ||
                department.isEmpty() || email.isEmpty() || reason.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo đối tượng LeaveRequest từ dữ liệu nhập vào
        LeaveRequest leaveRequest = new LeaveRequest(fullName, leaveDate, position, department, email, reason);

        // Gọi phương thức addLeaveRequest của DatabaseHelper
        long newRowId = dbHelper.addLeaveRequest(fullName, leaveDate, position, department, email, reason);

        if (newRowId != -1) {
            Toast.makeText(this, "Gửi yêu cầu thành công!", Toast.LENGTH_SHORT).show();
            // Xóa các trường nhập liệu sau khi gửi thành công
            clearInputFields();
        } else {
            Toast.makeText(this, "Gửi yêu cầu thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputFields() {
        etFullName.setText("");
        etLeaveDate.setText("");
        etPosition.setText("");
        etDepartment.setText("");
        etEmail.setText("");
        etReason.setText("");
    }
}