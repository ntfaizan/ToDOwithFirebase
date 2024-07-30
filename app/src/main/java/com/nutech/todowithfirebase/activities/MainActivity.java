package com.nutech.todowithfirebase.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nutech.todowithfirebase.adapters.StudentAdapter;
import com.nutech.todowithfirebase.databinding.ActivityMainBinding;
import com.nutech.todowithfirebase.models.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        // Create a list of items
        List<Student> itemList = new ArrayList<>();
        itemList.add(new Student("Ali", 25, 1, "BSCS"));
        itemList.add(new Student("Munir", 28, 2, "BSMath"));
        itemList.add(new Student("Hamza", 21, 3, "BSIT"));
        itemList.add(new Student("Farman", 23, 4, "BSCS"));
        itemList.add(new Student("Ali", 25, 5, "BSCS"));
        itemList.add(new Student("Munir", 28, 6, "BSMath"));
        itemList.add(new Student("Hamza", 21, 7, "BSIT"));
        itemList.add(new Student("Farman", 23, 8, "BSCS"));

        // Set up the RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StudentAdapter adapter = new StudentAdapter(itemList);
        binding.recyclerView.setAdapter(adapter);
    }
}