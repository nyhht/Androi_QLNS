package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.WorkEntry;

public class DailyWorkScheduleAdapter extends RecyclerView.Adapter<DailyWorkScheduleAdapter.DailyWorkScheduleViewHolder> {

    private List<WorkEntry> workEntries;

    public DailyWorkScheduleAdapter(List<WorkEntry> workEntries) {
        this.workEntries = workEntries;
    }

    public void updateData(List<WorkEntry> newEntries) {
        this.workEntries.clear();
        this.workEntries.addAll(newEntries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DailyWorkScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_work_schedule, parent, false);
        return new DailyWorkScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyWorkScheduleViewHolder holder, int position) {
        WorkEntry entry = workEntries.get(position);
        holder.tvTime.setText(entry.getTime());
        holder.tvType.setText(entry.getType());

        // Tùy chỉnh màu sắc hoặc biểu tượng nếu muốn
        if ("CheckIn".equals(entry.getType())) {
            holder.tvType.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_dark));
        } else if ("CheckOut".equals(entry.getType())) {
            holder.tvType.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    @Override
    public int getItemCount() {
        return workEntries.size();
    }

    public static class DailyWorkScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTime;
        public TextView tvType;

        public DailyWorkScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_item_time);
            tvType = itemView.findViewById(R.id.tv_item_type);
        }
    }
}