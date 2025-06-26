package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters.TimekeepingAdapter;

public class TimekeepingActivity extends AppCompatActivity {

    private TextView tvTime;
    private Button btnCheckIn, btnCheckOut;
    private RecyclerView recyclerView;
    private TimekeepingAdapter adapter;
    private ArrayList<String> checkinList;
    private boolean isCheckInMode = true;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        tvTime = findViewById(R.id.tvTime);
        btnCheckIn = findViewById(R.id.btnCheckIn);
        btnCheckOut = findViewById(R.id.btnCheckOut);
        recyclerView = findViewById(R.id.recyclerViewTimekeeping);
        imgBack = findViewById(R.id.imgBack);

        updateTime();

        checkinList = new ArrayList<>();
        adapter = new TimekeepingAdapter(checkinList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnCheckIn.setOnClickListener(view -> {
            isCheckInMode = true;
            String time = getCurrentTime();
            checkinList.add("Check In: " + time);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Check-in lúc " + time, Toast.LENGTH_SHORT).show();
        });

        btnCheckOut.setOnClickListener(view -> {
            isCheckInMode = false;
            String time = getCurrentTime();
            checkinList.add("Check Out: " + time);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Check-out lúc " + time, Toast.LENGTH_SHORT).show();
        });

        imgBack.setOnClickListener(v -> finish());
    }

    private void updateTime() {
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        tvTime.setText(currentTime);
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
    }
}
