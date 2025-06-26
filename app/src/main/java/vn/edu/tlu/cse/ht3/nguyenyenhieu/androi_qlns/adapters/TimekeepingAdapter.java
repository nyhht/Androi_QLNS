package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;

public class TimekeepingAdapter extends RecyclerView.Adapter<TimekeepingAdapter.ViewHolder> {

    private List<String> timeList;

    public TimekeepingAdapter(List<String> timeList) {
        this.timeList = timeList;
    }

    @NonNull
    @Override
    public TimekeepingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_timekeeping_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimekeepingAdapter.ViewHolder holder, int position) {
        String time = timeList.get(position);
        holder.tvTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTimeRecord);
        }
    }
}
