package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PayrollAdapter extends RecyclerView.Adapter<PayrollAdapter.PayrollViewHolder> {

    private List<Employee> employeeList;

    public PayrollAdapter(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public PayrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payroll, parent, false);
        return new PayrollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayrollViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.tvNamePosition.setText(employee.getName() + "\n" + employee.getPosition());
        holder.tvDaysOff.setText(String.valueOf(employee.getDaysOff()));
        holder.tvBaseSalary.setText(employee.getFormattedSalary(employee.getBaseSalary()));
        holder.tvTotalSalary.setText(employee.getFormattedSalary(employee.getTotalSalary()));
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public void updateList(List<Employee> newList) {
        this.employeeList = newList;
        notifyDataSetChanged(); // Thông báo cho adapter rằng dữ liệu đã thay đổi
    }

    static class PayrollViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamePosition, tvDaysOff, tvBaseSalary, tvTotalSalary;

        public PayrollViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamePosition = itemView.findViewById(R.id.tvNamePosition);
            tvDaysOff = itemView.findViewById(R.id.tvDaysOff);
            tvBaseSalary = itemView.findViewById(R.id.tvBaseSalary);
            tvTotalSalary = itemView.findViewById(R.id.tvTotalSalary);
        }
    }
}