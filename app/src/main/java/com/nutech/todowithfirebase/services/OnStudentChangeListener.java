package com.nutech.todowithfirebase.services;

import com.nutech.todowithfirebase.models.Student;

public interface OnStudentChangeListener {
    void onStudentChange();
    void onStudentEdit(Student student);
}

