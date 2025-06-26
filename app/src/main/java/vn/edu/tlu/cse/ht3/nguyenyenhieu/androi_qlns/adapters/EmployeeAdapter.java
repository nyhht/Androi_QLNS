package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private final Context context;
    private final List<Employee> employeeList;
    private final OnEmployeeActionListener listener;

    public interface OnEmployeeActionListener {
        void onDelete(Employee employee);
        void onViewDetail(Employee employee);
    }

    public EmployeeAdapter(Context context, List<Employee> employeeList, OnEmployeeActionListener listener) {
        this.context = context;
        this.employeeList = employeeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.txtName.setText(employee.getFullName());
        holder.txtPosition.setText(employee.getPosition());

        holder.btnDelete.setOnClickListener(v -> listener.onDelete(employee));
        holder.btnViewDetail.setOnClickListener(v -> listener.onViewDetail(employee));
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtPosition;
        ImageView btnDelete, btnViewDetail;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPosition = itemView.findViewById(R.id.txtPosition);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnViewDetail = itemView.findViewById(R.id.btnViewDetail);
        }
    }
}
