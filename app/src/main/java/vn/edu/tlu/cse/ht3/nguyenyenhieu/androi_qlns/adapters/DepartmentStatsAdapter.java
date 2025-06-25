package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class DepartmentStatsAdapter extends RecyclerView.Adapter<DepartmentStatsAdapter.DepartmentStatsViewHolder> {

    private List<DepartmentStats> statsList;
    private NumberFormat currencyFormatter;

    public DepartmentStatsAdapter(List<DepartmentStats> statsList) {
        this.statsList = statsList;
        // Khởi tạo NumberFormat cho tiền tệ Việt Nam
        currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        // Loại bỏ ký hiệu tiền tệ mặc định và thay bằng " VND"
    }

    @NonNull
    @Override
    public DepartmentStatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_department_stats, parent, false);
        return new DepartmentStatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentStatsViewHolder holder, int position) {
        DepartmentStats stats = statsList.get(position);
        holder.tvDepartmentName.setText(stats.getDepartmentName());
        holder.tvEmployeeCount.setText(String.valueOf(stats.getEmployeeCount()));
        holder.tvTotalBaseSalary.setText(formatCurrency(stats.getTotalBaseSalary()));
        holder.tvTotalDepartmentSalary.setText(formatCurrency(stats.getTotalDepartmentSalary()));

        // Thay đổi màu nền hàng xen kẽ
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.white));
        } else {
            holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.background_light));
        }
    }

    @Override
    public int getItemCount() {
        return statsList.size();
    }

    private String formatCurrency(double amount) {
        // Định dạng tiền tệ và thêm " VND" thủ công
        return currencyFormatter.format(amount) + " VND";
    }

    public static class DepartmentStatsViewHolder extends RecyclerView.ViewHolder {
        TextView tvDepartmentName, tvEmployeeCount, tvTotalBaseSalary, tvTotalDepartmentSalary;

        public DepartmentStatsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDepartmentName = itemView.findViewById(R.id.tvDepartmentName);
            tvEmployeeCount = itemView.findViewById(R.id.tvEmployeeCount);
            tvTotalBaseSalary = itemView.findViewById(R.id.tvTotalBaseSalary);
            tvTotalDepartmentSalary = itemView.findViewById(R.id.tvTotalDepartmentSalary);
        }
    }
}