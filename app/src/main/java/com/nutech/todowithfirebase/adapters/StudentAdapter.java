package com.nutech.todowithfirebase.adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nutech.todowithfirebase.R;
import com.nutech.todowithfirebase.activities.MainActivity;
import com.nutech.todowithfirebase.activities.StudentAddActivity;
import com.nutech.todowithfirebase.models.Student;
import com.nutech.todowithfirebase.services.OnStudentChangeListener;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> itemList;
    private OnStudentChangeListener changeListener;

    public StudentAdapter(List<Student> itemList, OnStudentChangeListener changeListener) {
        this.itemList = itemList;
        this.changeListener = changeListener;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtAge;
        public TextView txtRollNo;
        public TextView txtDegreeTitle;
        private ImageButton btnDelete;
        private ImageButton btnEdit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtRollNo = itemView.findViewById(R.id.txtRollNo);
            txtDegreeTitle = itemView.findViewById(R.id.txtTitle);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }

        public void bind(Student student) {
            txtName.setText(student.name);
            txtAge.setText(String.valueOf(student.age));
            txtRollNo.setText(String.valueOf(student.rollNo));
            txtDegreeTitle.setText(student.title);

            btnDelete.setOnClickListener(v -> {
                deleteDocument(student.docId);
            });

            btnEdit.setOnClickListener(v -> {
                if (changeListener != null) {
                    changeListener.onStudentEdit(student);
                }
            });
        }

        private void deleteDocument(String docId){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            // Get a reference to the document
            DocumentReference docRef = db.collection("students").document(docId);
            // Delete the document
            docRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        // Document was successfully deleted
                        System.out.println("DocumentSnapshot successfully deleted!");
                        // Remove the item from the adapter's list and notify
                        if (changeListener != null) {
                            changeListener.onStudentChange();
                        }
                    } else {
                        // Handle the error
                        System.err.println("Error deleting document: " + task.getException());
                    }
                }
            });
        }
    }
}
