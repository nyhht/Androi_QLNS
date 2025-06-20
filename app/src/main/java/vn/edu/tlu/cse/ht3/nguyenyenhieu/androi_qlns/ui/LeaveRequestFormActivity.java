package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.ui;

import android.app.DatePickerDialog; // <-- THÊM DÒNG NÀY
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView; // <-- THÊM DÒNG NÀY CHO NÚT BACK (nếu có)
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat; // <-- THÊM DÒNG NÀY
import java.util.Calendar; // <-- THÊM DÒNG NÀY
import java.util.Locale;   // <-- THÊM DÒNG NÀY

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.data.DatabaseHelper;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.LeaveRequest;

public class LeaveRequestFormActivity extends AppCompatActivity {

    private EditText etFullName, etLeaveDate, etPosition, etDepartment, etEmail, etReason;
    private Button btnSubmitRequest, btnCancel; // Thêm btnCancel nếu có
    private DatabaseHelper dbHelper;
    private Calendar calendar; // Khai báo Calendar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request_form);

        // Khởi tạo DatabaseHelper
        dbHelper = new DatabaseHelper(this);
        calendar = Calendar.getInstance(); // Khởi tạo Calendar

        // Ánh xạ các thành phần UI
        etFullName = findViewById(R.id.etFullName); // Hoặc R.id.edit_text_full_name
        etLeaveDate = findViewById(R.id.etLeaveDate); // <--- KIỂM TRA LẠI ID NÀY TRONG XML CÓ PHẢI LÀ etLeaveDate HAY edit_text_leave_date
        etPosition = findViewById(R.id.etPosition);
        etDepartment = findViewById(R.id.etDepartment);
        etEmail = findViewById(R.id.etEmail);
        etReason = findViewById(R.id.etReason);

        btnSubmitRequest = findViewById(R.id.btnSubmitRequest);
        btnCancel = findViewById(R.id.btn_cancel_leave_request); // Nếu có nút hủy

        // Xử lý nút Back (Nếu có)
        ImageView btnBack = findViewById(R.id.btn_back_form); // Đảm bảo ID này đúng trong layout của bạn
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> onBackPressed());
        }

        // Xử lý sự kiện click cho EditText Ngày phép (QUAN TRỌNG NHẤT)
        // Đảm bảo etLeaveDate không phải là null trước khi gắn Listener
        if (etLeaveDate != null) {
            etLeaveDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog();
                }
            });
            // Ngoài ra, bạn có thể thiết lập thuộc tính không cho phép nhập tay
            etLeaveDate.setKeyListener(null); // Không cho phép nhập bằng bàn phím
            etLeaveDate.setFocusable(false); // Không focusable
            etLeaveDate.setClickable(true); // Có thể click được
        } else {
            Toast.makeText(this, "Lỗi: Không tìm thấy trường 'Ngày phép' (etLeaveDate). Kiểm tra ID trong layout.", Toast.LENGTH_LONG).show();
        }


        // Xử lý sự kiện cho nút Gửi yêu cầu
        btnSubmitRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLeaveRequest();
            }
        });

        // Xử lý sự kiện cho nút Hủy (nếu có)
        if (btnCancel != null) {
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish(); // Đóng Activity hiện tại
                }
            });
        }
    }

    // Phương thức hiển thị DatePickerDialog
    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(); // Cập nhật EditText sau khi chọn ngày
        };

        new DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    // Phương thức cập nhật EditText với ngày đã chọn
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; // Định dạng ngày tháng
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etLeaveDate.setText(sdf.format(calendar.getTime()));
    }


    private void submitLeaveRequest() {
        String fullName = etFullName.getText().toString().trim();
        String leaveDate = etLeaveDate.getText().toString().trim();
        String position = etPosition.getText().toString().trim();
        String department = etDepartment.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String reason = etReason.getText().toString().trim();

        // Kiểm tra dữ liệu đầu vào cơ bản
        if (fullName.isEmpty() || leaveDate.isEmpty() || reason.isEmpty()) { // Có thể bỏ kiểm tra position, department, email nếu chúng không bắt buộc
            Toast.makeText(this, "Vui lòng điền đầy đủ Họ tên, Ngày phép và Lý do", Toast.LENGTH_SHORT).show();
            return;
        }

        // Gọi phương thức addLeaveRequest của DatabaseHelper
        long newRowId = dbHelper.addLeaveRequest(fullName, leaveDate, position, department, email, reason);

        if (newRowId != -1) {
            Toast.makeText(this, "Gửi yêu cầu thành công!", Toast.LENGTH_SHORT).show();
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