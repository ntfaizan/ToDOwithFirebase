package com.nutech.todowithfirebase.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nutech.todowithfirebase.R;
import com.nutech.todowithfirebase.models.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> itemList;

    public StudentAdapter(List<Student> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Student student = itemList.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtAge;
        public TextView txtRollNo;
        public TextView txtDegreeTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtRollNo = itemView.findViewById(R.id.txtRollNo);
            txtDegreeTitle = itemView.findViewById(R.id.txtTitle);
        }

        public void bind(Student student) {
            txtName.setText(student.name);
            txtAge.setText(String.valueOf(student.age));
            txtRollNo.setText(String.valueOf(student.rollNo));
            txtDegreeTitle.setText(student.title);
        }
    }
}
