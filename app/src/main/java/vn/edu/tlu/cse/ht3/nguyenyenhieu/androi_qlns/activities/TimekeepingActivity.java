package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimekeepingActivity extends AppCompatActivity {

    private TextView tvTime;
    private Button btnCheckIn, btnCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timekeeping);

        tvTime = findViewById(R.id.tvTime);
        btnCheckIn = findViewById(R.id.btnCheckIn);
        btnCheckOut = findViewById(R.id.btnCheckOut);

        updateTime();

        btnCheckIn.setOnClickListener(v -> {
            Toast.makeText(this, "Check-in thành công!", Toast.LENGTH_SHORT).show();
        });

        btnCheckOut.setOnClickListener(v -> {
            Toast.makeText(this, "Check-out thành công!", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateTime() {
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        tvTime.setText(currentTime);
    }
}
