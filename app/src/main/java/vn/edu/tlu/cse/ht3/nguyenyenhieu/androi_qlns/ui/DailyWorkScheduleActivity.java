package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.data.DatabaseHelper;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.WorkEntry;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters.DailyWorkScheduleAdapter;

public class DailyWorkScheduleActivity extends AppCompatActivity {

    private EditText etSelectDate;
    private TextView tvSelectedDateDisplay;
    // private Button btnAddWorkEntry; // ĐÃ BỊ XÓA BỎ
    private RecyclerView recyclerViewDailyWorkSchedule;
    private DailyWorkScheduleAdapter adapter;
    private DatabaseHelper dbHelper;
    private Calendar selectedCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_work_schedule);

        etSelectDate = findViewById(R.id.et_select_date);
        tvSelectedDateDisplay = findViewById(R.id.tv_selected_date_display);
        // btnAddWorkEntry = findViewById(R.id.btn_add_work_entry); // ĐÃ BỊ XÓA BỎ
        recyclerViewDailyWorkSchedule = findViewById(R.id.recycler_view_daily_work_schedule);

        dbHelper = new DatabaseHelper(this);
        selectedCalendar = Calendar.getInstance();

        // Setup RecyclerView
        recyclerViewDailyWorkSchedule.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DailyWorkScheduleAdapter(new ArrayList<>());
        recyclerViewDailyWorkSchedule.setAdapter(adapter);

        // Nút Back
        ImageView btnBack = findViewById(R.id.btn_back_daily_schedule);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> onBackPressed());
        }

        // Xử lý click trên EditText để chọn ngày
        etSelectDate.setOnClickListener(v -> showDatePickerDialog());
        etSelectDate.setKeyListener(null);
        etSelectDate.setFocusable(false);
        etSelectDate.setClickable(true);

        // Xử lý click nút "Thêm Chấm Công" (ĐÃ BỊ XÓA BỎ)
        // btnAddWorkEntry.setOnClickListener(v -> showAddWorkEntryDialog());

        // Hiển thị ngày hiện tại khi Activity được tạo
        updateDateLabelAndLoadSchedule(selectedCalendar);
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            selectedCalendar.set(Calendar.YEAR, year);
            selectedCalendar.set(Calendar.MONTH, month);
            selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateLabelAndLoadSchedule(selectedCalendar);
        };

        new DatePickerDialog(
                this,
                dateSetListener,
                selectedCalendar.get(Calendar.YEAR),
                selectedCalendar.get(Calendar.MONTH),
                selectedCalendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void updateDateLabelAndLoadSchedule(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        String formattedDate = sdf.format(calendar.getTime());
        etSelectDate.setText(formattedDate);
        tvSelectedDateDisplay.setText("Lịch làm việc của ngày: " + formattedDate);
        loadDailyWorkSchedule(formattedDate);
    }

    private void loadDailyWorkSchedule(String date) {
        List<WorkEntry> workEntries = new ArrayList<>();
        android.database.Cursor cursor = dbHelper.getWorkEntriesForDate(date);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_WS_ID));
                String entryDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_WS_DATE));
                String checkIn = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_WS_CHECK_IN_TIME));
                String checkOut = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_WS_CHECK_OUT_TIME));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_WS_TYPE));

                // Tạo WorkEntry và thêm vào danh sách
                // Dựa vào cách bạn lưu trữ, nếu một hàng có cả check-in và check-out,
                // bạn có thể tạo một WorkEntry duy nhất hoặc hai WorkEntry riêng biệt.
                // Ở đây, tôi đang tạo hai WorkEntry riêng biệt để hiển thị rõ ràng từng loại.
                if (checkIn != null && !checkIn.isEmpty()) {
                    workEntries.add(new WorkEntry(id, entryDate, checkIn, "CheckIn"));
                }
                if (checkOut != null && !checkOut.isEmpty()) {
                    workEntries.add(new WorkEntry(id, entryDate, checkOut, "CheckOut"));
                }

            } while (cursor.moveToNext());
            cursor.close();
        }

        // Tùy chọn: Sắp xếp lại danh sách theo thời gian nếu cần
        // Collections.sort(workEntries, (e1, e2) -> e1.getDisplayTime().compareTo(e2.getDisplayTime()));

        adapter.updateData(workEntries);
        if (workEntries.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu chấm công cho ngày này.", Toast.LENGTH_SHORT).show();
        }
    }

    // Các phương thức liên quan đến thêm chấm công (showAddWorkEntryDialog, addWorkEntryToDb) ĐÃ BỊ XÓA BỎ
}