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

public class WorkScheduleAdapter extends RecyclerView.Adapter<WorkScheduleAdapter.WorkEntryViewHolder> {

    private List<WorkEntry> workEntryList;

    public WorkScheduleAdapter(List<WorkEntry> workEntryList) {
        this.workEntryList = workEntryList;
    }

    public void setWorkEntryList(List<WorkEntry> workEntryList) {
        this.workEntryList = workEntryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WorkEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_entry, parent, false);
        return new WorkEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkEntryViewHolder holder, int position) {
        WorkEntry entry = workEntryList.get(position);
        holder.tvTime.setText(entry.getTime());
        holder.tvType.setText(entry.getType());
    }

    @Override
    public int getItemCount() {
        return workEntryList.size();
    }

    public static class WorkEntryViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime, tvType;

        public WorkEntryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_work_time);
            tvType = itemView.findViewById(R.id.tv_work_type);
        }
    }
}