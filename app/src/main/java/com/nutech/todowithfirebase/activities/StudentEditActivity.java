package com.nutech.todowithfirebase.activities;

import android.content.Intent;
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
import com.nutech.todowithfirebase.databinding.ActivityStudentEditBinding;

import com.nutech.todowithfirebase.R;
import com.nutech.todowithfirebase.models.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentEditActivity extends AppCompatActivity {

    private ActivityStudentEditBinding binding;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String TAG = "cust_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("std");

        if(student!=null){
            binding.editTextNameStudentEdit.setText(student.name);
            binding.editTextAgeStudentEdit.setText(String.valueOf(student.age));
            binding.editTextRollNoStudentEdit.setText(String.valueOf(student.rollNo));
            binding.editTextTitleStudentEdit.setText(student.title);
        }
        else{
            System.out.println(TAG+"Error in Std Data");
        }
        setSupportActionBar(binding.toolbarStudentEdit);
        binding.fabStudentEdit.setOnClickListener(v -> editData(student.docId));
    }

    private void editData(String docId){
        String name = String.valueOf(binding.editTextNameStudentEdit.getText());
        int age = Integer.parseInt(String.valueOf(binding.editTextAgeStudentEdit.getText()));
        int rollNo = Integer.parseInt(String.valueOf(binding.editTextRollNoStudentEdit.getText()));
        String title = String.valueOf(binding.editTextTitleStudentEdit.getText());

        //Store to Firebase
        Map<String, Object> student = new HashMap<>();
        student.put("name", name);
        student.put("roll_no", rollNo);
        student.put("age", age);
        student.put("title", title);

        // Add a new document with a generated ID
        db.collection("students").document(docId)
                .set(student)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "Successfully adding document");
                })
                .addOnFailureListener(e -> {
                    Log.d(TAG, "Error adding document", e);
                });

        binding.editTextNameStudentEdit.setText("");
        binding.editTextAgeStudentEdit.setText("");
        binding.editTextRollNoStudentEdit.setText("");
        binding.editTextTitleStudentEdit.setText("");
    }
}