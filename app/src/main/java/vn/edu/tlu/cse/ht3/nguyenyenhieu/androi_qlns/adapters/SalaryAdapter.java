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
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Salary;

public class SalaryAdapter extends RecyclerView.Adapter<SalaryAdapter.SalaryViewHolder> {

    private final Context context;
    private final List<Salary> salaryList;

    public SalaryAdapter(Context context, List<Salary> salaryList) {
        this.context = context;
        this.salaryList = salaryList;
    }

    @NonNull
    @Override
    public SalaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_salary_row, parent, false);
        return new SalaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaryViewHolder holder, int position) {
        Salary salary = salaryList.get(position);
        holder.tvSalaryName.setText(salary.getEmployeeName());
        holder.tvSalaryMonth.setText(salary.getMonth());
        holder.tvSalaryAmount.setText(String.format("%,d VNĐ", salary.getTotalSalary()));
    }

    @Override
    public int getItemCount() {
        return salaryList.size();
    }

    public static class SalaryViewHolder extends RecyclerView.ViewHolder {
        TextView tvSalaryName, tvSalaryMonth, tvSalaryAmount;

        public SalaryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSalaryName = itemView.findViewById(R.id.tvSalaryName);
            tvSalaryMonth = itemView.findViewById(R.id.tvSalaryMonth);
            tvSalaryAmount = itemView.findViewById(R.id.tvSalaryAmount);
        }
    }
}
