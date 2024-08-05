package com.nutech.todowithfirebase.activities;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.firestore.FirebaseFirestore;
import com.nutech.todowithfirebase.databinding.ActivityStudentAddBinding;

import com.nutech.todowithfirebase.R;

import java.util.HashMap;
import java.util.Map;

public class StudentAddActivity extends AppCompatActivity {

    private ActivityStudentAddBinding binding;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String TAG = "cust_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(v -> saveData());
    }

    private void saveData(){
        String name = String.valueOf(binding.editTextName.getText());
        int age = Integer.parseInt(String.valueOf(binding.editTextAge.getText()));
        int rollNo = Integer.parseInt(String.valueOf(binding.editTextRollNo.getText()));
        String title = String.valueOf(binding.editTextTitle.getText());

        //Store to Firebase
        Map<String, Object> student = new HashMap<>();
        student.put("name", name);
        student.put("roll_no", rollNo);
        student.put("age", age);
        student.put("title", title);

        // Add a new document with a generated ID
        db.collection("students")
                .add(student)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "Record added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error adding document", e);
                });

        binding.editTextName.setText("");
        binding.editTextAge.setText("");
        binding.editTextRollNo.setText("");
        binding.editTextTitle.setText("");
    }


}