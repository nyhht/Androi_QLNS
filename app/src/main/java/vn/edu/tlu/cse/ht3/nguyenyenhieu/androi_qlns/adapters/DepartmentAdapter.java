package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.R;
import vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models.Department;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Department department);
    }

    private final List<Department> departmentList;
    private final OnItemClickListener listener;

    public DepartmentAdapter(List<Department> departmentList, OnItemClickListener listener) {
        this.departmentList = departmentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_department, parent, false);
        return new DepartmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder holder, int position) {
        Department department = departmentList.get(position);
        holder.bind(department, listener);
    }

    @Override
    public int getItemCount() {
        return departmentList.size();
    }

    public static class DepartmentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDescription;

        public DepartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvDepartmentName);
            tvDescription = itemView.findViewById(R.id.tvDepartmentDescription);
        }

        public void bind(Department department, OnItemClickListener listener) {
            tvName.setText(department.getName());
            tvDescription.setText(department.getDescription());
            itemView.setOnClickListener(v -> listener.onItemClick(department));
        }
    }
}
