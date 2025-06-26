package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.DatabaseHelper;

public class LeaveRequestFormActivity extends AppCompatActivity {

    private EditText etName, etDate, etPosition, etDepartment, etEmail, etReason;
    private Button btnSubmit, btnCancel;
    private ImageButton imgBack;
    private ImageView imgCalendar;

    private DatabaseHelper dbHelper;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);

        dbHelper = new DatabaseHelper(this);
        calendar = Calendar.getInstance();

        // Ánh xạ các view từ layout XML
        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        etPosition = findViewById(R.id.etPosition);
        etDepartment = findViewById(R.id.etDepartment);
        etEmail = findViewById(R.id.etEmail);
        etReason = findViewById(R.id.etReason);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
        imgBack = findViewById(R.id.imgBack);
        imgCalendar = findViewById(R.id.imgCalendar);

        // Mở DatePicker khi click vào icon lịch hoặc EditText ngày
        View.OnClickListener dateClickListener = v -> showDatePickerDialog();
        etDate.setOnClickListener(dateClickListener);
        imgCalendar.setOnClickListener(dateClickListener);

        etDate.setKeyListener(null); // Không nhập tay
        etDate.setFocusable(false);

        // Nút back
        imgBack.setOnClickListener(v -> onBackPressed());

        // Nút gửi yêu cầu
        btnSubmit.setOnClickListener(v -> submitLeaveRequest());

        // Nút hủy
        btnCancel.setOnClickListener(v -> finish());
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateField();
        };

        new DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void updateDateField() {
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        etDate.setText(sdf.format(calendar.getTime()));
    }

    private void submitLeaveRequest() {
        String name = etName.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String position = etPosition.getText().toString().trim();
        String department = etDepartment.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String reason = etReason.getText().toString().trim();

        if (name.isEmpty() || date.isEmpty() || reason.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ Họ tên, Ngày phép và Lý do", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = dbHelper.addLeaveRequest(name, date, position, department, email, reason);

        if (result != -1) {
            Toast.makeText(this, "Gửi yêu cầu thành công!", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etName.setText("");
        etDate.setText("");
        etPosition.setText("");
        etDepartment.setText("");
        etEmail.setText("");
        etReason.setText("");
    }
}
