// --- LeaveRequestFormActivity.java ---
package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView; // Vẫn cần cho tiêu đề và các nhãn
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.data.DatabaseHelper;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LeaveRequestFormActivity extends AppCompatActivity {

    private EditText etFullName, etLeaveDate, etPosition, etDepartment, etEmail, etReason;
    private ImageView ivCalendarIcon;
    private Button btnAdd, btnCancel;
    private DatabaseHelper dbHelper;

    private Calendar calendar; // Dùng để chọn ngày

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request_form); // Đã cập nhật layout

        // Ánh xạ các EditText và ImageView
        etFullName = findViewById(R.id.et_full_name);
        etLeaveDate = findViewById(R.id.et_leave_date);
        etPosition = findViewById(R.id.et_position);
        etDepartment = findViewById(R.id.et_department);
        etEmail = findViewById(R.id.et_email);
        etReason = findViewById(R.id.et_reason);
        ivCalendarIcon = findViewById(R.id.iv_calendar_icon);

        // Ánh xạ các Button
        btnAdd = findViewById(R.id.btn_add);
        btnCancel = findViewById(R.id.btn_cancel);

        // Nút quay lại
        ImageView backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Quay lại màn hình trước
            }
        });

        dbHelper = new DatabaseHelper(this);
        calendar = Calendar.getInstance(); // Khởi tạo Calendar

        // Thiết lập sự kiện click cho icon lịch
        ivCalendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Thiết lập sự kiện click cho EditText ngày phép (để mở DatePicker khi click vào trường)
        etLeaveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Thiết lập sự kiện click cho nút Thêm
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLeaveRequest();
            }
        });

        // Thiết lập sự kiện click cho nút Hủy
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm(); // Xóa trắng form
                Toast.makeText(LeaveRequestFormActivity.this, "Đã hủy nhập liệu.", Toast.LENGTH_SHORT).show();
                // Hoặc có thể đóng Activity: finish();
            }
        });
    }

    private void showDatePickerDialog() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; // Định dạng ngày
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etLeaveDate.setText(sdf.format(calendar.getTime()));
    }

    private void saveLeaveRequest() {
        String fullName = etFullName.getText().toString().trim();
        String leaveDate = etLeaveDate.getText().toString().trim();
        String position = etPosition.getText().toString().trim();
        String department = etDepartment.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String reason = etReason.getText().toString().trim();

        // Kiểm tra dữ liệu đầu vào
        if (fullName.isEmpty() || leaveDate.isEmpty() || reason.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ Họ tên, Ngày phép và Lý do.", Toast.LENGTH_LONG).show();
            return;
        }

        long newRowId = dbHelper.addLeaveRequest(fullName, leaveDate, position, department, email, reason);

        if (newRowId != -1) {
            Toast.makeText(this, "Yêu cầu nghỉ phép đã được thêm thành công!", Toast.LENGTH_SHORT).show();
            clearForm(); // Xóa trắng form sau khi thêm thành công
            // Nếu bạn muốn quay lại màn hình danh sách yêu cầu nghỉ phép (nếu có)
            // finish();
        } else {
            Toast.makeText(this, "Lỗi khi thêm yêu cầu nghỉ phép.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearForm() {
        etFullName.setText("");
        etLeaveDate.setText("");
        etPosition.setText("");
        etDepartment.setText("");
        etEmail.setText("");
        etReason.setText("");
    }
}