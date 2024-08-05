package com.nutech.todowithfirebase.activities;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.nutech.todowithfirebase.R;
import com.nutech.todowithfirebase.adapters.StudentAdapter;
import com.nutech.todowithfirebase.databinding.ActivityMainBinding;
import com.nutech.todowithfirebase.models.Student;
import com.nutech.todowithfirebase.services.OnStudentChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnStudentChangeListener {

    private ActivityMainBinding binding;
    private String TAG = "cust_tag";
    // Create a list of items
    List<Student> studentList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        // Set up the RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setAdapter(){
        studentList.clear();
        db.collection("students")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Map<String, Object> data = document.getData();
                            Log.d(TAG, document.getId() + " => " + data);
                            Student student = new Student(document.getId(), Objects.requireNonNull(data.get("name")).toString(), Integer.parseInt(Objects.requireNonNull(data.get("age")).toString()), Integer.parseInt(Objects.requireNonNull(data.get("roll_no")).toString()), Objects.requireNonNull(data.get("title")).toString());
                            studentList.add(student);
                        }
                        StudentAdapter adapter = new StudentAdapter(studentList, this);
                        binding.recyclerView.setAdapter(adapter);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu resource into the Menu object
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true; // Return true to display the menu
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle menu item clicks here
        if(item.getItemId()==R.id.action_student){
            Intent intent = new Intent(MainActivity.this, StudentAddActivity.class);
            startActivity(intent);
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStudentChange() {
        setAdapter();
    }

    @Override
    public void onStudentEdit(Student student) {
        Intent intent = new Intent(MainActivity.this, StudentEditActivity.class);
        intent.putExtra("std", student);
        startActivity(intent);
    }
}