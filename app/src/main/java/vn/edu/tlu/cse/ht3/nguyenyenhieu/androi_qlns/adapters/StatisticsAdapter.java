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

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Statistics;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> {

    private final List<Statistics> statisticsList;

    public StatisticsAdapter(List<Statistics> statisticsList) {
        this.statisticsList = statisticsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_statistics, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Statistics stat = statisticsList.get(position);
        holder.tvDepartment.setText(stat.getDepartment());
        holder.tvTotalSalary.setText(formatCurrency(stat.getTotalSalary()));
        holder.tvEmployeeCount.setText(String.valueOf(stat.getEmployeeCount())); // ✅ thêm dòng này
    }

    private String formatCurrency(double amount) {
        return NumberFormat.getNumberInstance(new Locale("vi", "VN")).format(amount);
    }

    @Override
    public int getItemCount() {
        return statisticsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDepartment, tvTotalSalary, tvEmployeeCount; // ✅ thêm dòng này

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDepartment = itemView.findViewById(R.id.tvDepartment);
            tvTotalSalary = itemView.findViewById(R.id.tvTotalSalary);
            tvEmployeeCount = itemView.findViewById(R.id.tvEmployeeCount); // ✅ thêm dòng này
        }
    }
}
