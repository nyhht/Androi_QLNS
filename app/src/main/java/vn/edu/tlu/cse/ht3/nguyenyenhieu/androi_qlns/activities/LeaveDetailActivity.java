package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;

public class LeaveDetailActivity extends AppCompatActivity {

    private EditText etName, etDate, etPosition, etDepartment, etEmail, etReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_detail);

        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        etPosition = findViewById(R.id.etPosition);
        etDepartment = findViewById(R.id.etDepartment);
        etEmail = findViewById(R.id.etEmail);
        etReason = findViewById(R.id.etReason);

        // Giả định gán dữ liệu tạm cho bản xem chi tiết
        etName.setText("Nguyễn Văn A");
        etDate.setText("2025-07-01");
        etPosition.setText("Nhân viên");
        etDepartment.setText("Phòng Kế toán");
        etEmail.setText("a@example.com");
        etReason.setText("Nghỉ khám bệnh");
    }
}
