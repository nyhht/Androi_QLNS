package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R; // R.layout.activity_work_schedule
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.data.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WorkScheduleActivity extends AppCompatActivity {

    private TextView tvCurrentTime;
    private Button btnCheckIn, btnCheckOut;
    private LinearLayout scheduleContainer; // Để thêm các mục lịch trình vào
    private DatabaseHelper dbHelper;
    private Handler handler;
    private Runnable updateTimeRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_schedule); // Đảm bảo bạn có layout XML này

        // Ánh xạ các View
        tvCurrentTime = findViewById(R.id.tv_current_time);
        btnCheckIn = findViewById(R.id.btn_check_in);
        btnCheckOut = findViewById(R.id.btn_check_out);
        scheduleContainer = findViewById(R.id.schedule_container); // Container cho lịch làm việc

        // Nút quay lại
        ImageView backButton = findViewById(R.id.btn_back_schedule);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        dbHelper = new DatabaseHelper(this);

        // Cập nhật thời gian hiện tại liên tục
        handler = new Handler();
        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                updateCurrentTime();
                handler.postDelayed(this, 1000); // Cập nhật mỗi giây
            }
        };

        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCheckIn();
            }
        });

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCheckOut();
            }
        });

        loadDailyWorkSchedule(); // Tải lịch làm việc khi khởi tạo
        highlightCurrentDay(); // Đánh dấu ngày hiện tại trên "Work Schedule"
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(updateTimeRunnable); // Bắt đầu cập nhật thời gian khi Activity hoạt động
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(updateTimeRunnable); // Dừng cập nhật thời gian khi Activity bị tạm dừng
    }

    private void updateCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        tvCurrentTime.setText(currentTime);
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }

    private String getCurrentTimeForEntry() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }

    private void performCheckIn() {
        String currentDate = getCurrentDate();
        String currentTime = getCurrentTimeForEntry();

        long id = dbHelper.addWorkEntry(currentDate, currentTime, null, "CheckIn");
        if (id != -1) {
            Toast.makeText(this, "Check In thành công: " + currentTime, Toast.LENGTH_SHORT).show();
            loadDailyWorkSchedule(); // Cập nhật lại lịch làm việc
        } else {
            Toast.makeText(this, "Lỗi khi Check In", Toast.LENGTH_SHORT).show();
        }
    }

    private void performCheckOut() {
        String currentDate = getCurrentDate();
        String currentTime = getCurrentTimeForEntry();

        // Trong trường hợp phức tạp hơn, bạn có thể cần tìm bản ghi check-in gần nhất
        // và cập nhật nó. Để đơn giản, ở đây chúng ta sẽ thêm một bản ghi check-out mới.
        long id = dbHelper.addWorkEntry(currentDate, null, currentTime, "CheckOut");
        if (id != -1) {
            Toast.makeText(this, "Check Out thành công: " + currentTime, Toast.LENGTH_SHORT).show();
            loadDailyWorkSchedule(); // Cập nhật lại lịch làm việc
        } else {
            Toast.makeText(this, "Lỗi khi Check Out", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDailyWorkSchedule() {
        scheduleContainer.removeAllViews(); // Xóa các mục cũ trước khi thêm mới
        String currentDate = getCurrentDate();
        List<String[]> scheduleEntries = dbHelper.getDailyWorkSchedule(currentDate);

        if (scheduleEntries.isEmpty()) {
            TextView noData = new TextView(this);
            noData.setText("Chưa có dữ liệu chấm công cho hôm nay.");
            noData.setTextSize(16f);
            noData.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
            noData.setPadding(0, 16, 0, 0);
            scheduleContainer.addView(noData);
            return;
        }

        for (String[] entry : scheduleEntries) {
            String checkIn = entry[0];
            String checkOut = entry[1];

            // Tạo LinearLayout cho mỗi cặp Check In/Check Out hoặc một mục riêng lẻ
            LinearLayout entryLayout = new LinearLayout(this);
            entryLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            entryLayout.setOrientation(LinearLayout.HORIZONTAL);
            entryLayout.setPadding(16, 16, 16, 16);
            entryLayout.setBackgroundResource(R.drawable.rounded_card_background); // Custom background
            ((LinearLayout.LayoutParams) entryLayout.getLayoutParams()).setMargins(0, 8, 0, 8);


            TextView timeTextView = new TextView(this);
            timeTextView.setTextSize(18f);
            timeTextView.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            LinearLayout.LayoutParams timeParams = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            timeTextView.setLayoutParams(timeParams);

            TextView statusTextView = new TextView(this);
            statusTextView.setTextSize(18f);
            statusTextView.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            statusTextView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END); // Căn phải
            LinearLayout.LayoutParams statusParams = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            statusTextView.setLayoutParams(statusParams);

            if (checkIn != null && !checkIn.isEmpty()) {
                timeTextView.setText(checkIn);
                statusTextView.setText("Check In");
            } else if (checkOut != null && !checkOut.isEmpty()) {
                timeTextView.setText(checkOut);
                statusTextView.setText("Check Out");
            }

            entryLayout.addView(timeTextView);
            entryLayout.addView(statusTextView);
            scheduleContainer.addView(entryLayout);
        }
    }

    private void highlightCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE", new Locale("vi", "VN")); // "EEE" for short day name
        String currentDay = sdf.format(new Date()).toUpperCase(Locale.getDefault()); // E.g., "THỨ 4"

        // Mapping day abbreviations to TextView IDs
        TextView tvMon = findViewById(R.id.tv_day_M);
        TextView tvTue = findViewById(R.id.tv_day_T);
        TextView tvWed = findViewById(R.id.tv_day_W);
        TextView tvThu = findViewById(R.id.tv_day_Th);
        TextView tvFri = findViewById(R.id.tv_day_F);
        TextView tvSat = findViewById(R.id.tv_day_S);
        TextView tvSun = findViewById(R.id.tv_day_Su);

        // Reset all to default style
        resetDayHighlight(tvMon);
        resetDayHighlight(tvTue);
        resetDayHighlight(tvWed);
        resetDayHighlight(tvThu);
        resetDayHighlight(tvFri);
        resetDayHighlight(tvSat);
        resetDayHighlight(tvSun);

        // Highlight current day
        // Cần điều chỉnh logic này dựa trên output thực tế của SimpleDateFormat với Locale("vi", "VN")
        // Một số phiên bản Android có thể trả về "Th 2", "Th 3", v.v. hoặc "Hai", "Ba", ...
        // Kiểm tra logcat hoặc debug để biết chính xác chuỗi ngày.
        switch (currentDay) {
            case "HAI": // Monday
            case "TH 2":
                highlightDay(tvMon);
                break;
            case "BA": // Tuesday
            case "TH 3":
                highlightDay(tvTue);
                break;
            case "TƯ": // Wednesday
            case "TH 4":
                highlightDay(tvWed);
                break;
            case "NĂM": // Thursday
            case "TH 5":
                highlightDay(tvThu);
                break;
            case "SÁU": // Friday
            case "TH 6":
                highlightDay(tvFri);
                break;
            case "BẢY": // Saturday
            case "TH 7":
                highlightDay(tvSat);
                break;
            case "CN": // Sunday
                highlightDay(tvSun);
                break;
            default:
                // Xử lý trường hợp không khớp (ví dụ, nếu Locale trả về định dạng khác)
                break;
        }
    }

    private void highlightDay(TextView dayTextView) {
        dayTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
        dayTextView.setTextSize(20f); // Make it a bit larger
        // Bạn có thể thêm một drawable hình tròn nhỏ bên dưới hoặc một background tùy chỉnh
        // dayTextView.setBackgroundResource(R.drawable.rounded_dot_background);
    }

    private void resetDayHighlight(TextView dayTextView) {
        dayTextView.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        dayTextView.setTextSize(16f); // Reset to default size
        // dayTextView.setBackgroundResource(0); // Xóa background nếu có
    }
}