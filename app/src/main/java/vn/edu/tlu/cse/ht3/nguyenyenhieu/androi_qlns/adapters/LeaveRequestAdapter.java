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
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.LeaveRequest;

public class LeaveRequestAdapter extends RecyclerView.Adapter<LeaveRequestAdapter.LeaveRequestViewHolder> {
    private Context context;
    private List<LeaveRequest> leaveRequestList;

    public LeaveRequestAdapter(Context context, List<LeaveRequest> leaveRequestList) {
        this.context = context;
        this.leaveRequestList = leaveRequestList;
    }

    public void updateList(List<LeaveRequest> newList) {
        this.leaveRequestList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LeaveRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_leave_request, parent, false);
        return new LeaveRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveRequestViewHolder holder, int position) {
        LeaveRequest leaveRequest = leaveRequestList.get(position);
        holder.tvLeaveDate.setText("Ngày nghỉ: " + leaveRequest.getLeaveDate());
        holder.tvFullName.setText("Họ tên: " + leaveRequest.getFullName());
        holder.tvReasonSummary.setText("Lý do: " + leaveRequest.getReason());

        // Optional: Click để mở chi tiết đơn
        // holder.itemView.setOnClickListener(...)
    }

    @Override
    public int getItemCount() {
        return leaveRequestList.size();
    }

    public static class LeaveRequestViewHolder extends RecyclerView.ViewHolder {
        TextView tvLeaveDate, tvFullName, tvReasonSummary;

        public LeaveRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLeaveDate = itemView.findViewById(R.id.tv_item_leave_date);
            tvFullName = itemView.findViewById(R.id.tv_item_full_name);
            tvReasonSummary = itemView.findViewById(R.id.tv_item_reason_summary);
        }
    }
}
