package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.activities.EmployeeDetailActivity;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.database.EmployeeDAO;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private Context context;
    private List<Employee> employees;
    private EmployeeDAO dao;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
        dao = new EmployeeDAO(context);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPosition;
        ImageButton btnDetail, btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPosition = itemView.findViewById(R.id.tvPosition);
            btnDetail = itemView.findViewById(R.id.btnDetail);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Employee e = employees.get(position);
        holder.tvName.setText(e.getName());
        holder.tvPosition.setText(e.getPosition());

        holder.btnDetail.setOnClickListener(v -> {
            Intent i = new Intent(context, EmployeeDetailActivity.class);
            i.putExtra("employee", e);
            context.startActivity(i);
        });

        holder.btnDelete.setOnClickListener(v -> {
            dao.deleteEmployee(e.getId());
            employees.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, employees.size());
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }
}
