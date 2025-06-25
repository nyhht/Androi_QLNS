package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Statistics;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder> {
    private Context context;
    private List<Statistics> statisticsList;

    public StatisticsAdapter(Context context, List<Statistics> statisticsList) {
        this.context = context;
        this.statisticsList = statisticsList;
    }

    @NonNull
    @Override
    public StatisticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_statistics, parent, false);
        return new StatisticsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsViewHolder holder, int position) {
        Statistics stat = statisticsList.get(position);
        holder.tvName.setText(stat.getEmployeeName());
        holder.tvAttendance.setText("Công: " + stat.getWorkdays());
        holder.tvLeave.setText("Nghỉ: " + stat.getLeaveDays());
    }

    @Override
    public int getItemCount() {
        return statisticsList.size();
    }

    public static class StatisticsViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAttendance, tvLeave;

        public StatisticsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvStatisticsName);
            tvAttendance = itemView.findViewById(R.id.tvStatisticsWorkdays);
            tvLeave = itemView.findViewById(R.id.tvStatisticsLeaveDays);
        }
    }
}
