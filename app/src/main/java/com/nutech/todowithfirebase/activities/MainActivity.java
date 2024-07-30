package com.nutech.todowithfirebase.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nutech.todowithfirebase.adapters.ItemAdapter;
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
        for (int i = 1; i <= 20; i++) {
            itemList.add(new Student("Item " + i));
        }

        // Set up the RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemAdapter adapter = new ItemAdapter(itemList);
        binding.recyclerView.setAdapter(adapter);
    }
}