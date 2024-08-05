package com.nutech.todowithfirebase.models;

import java.io.Serializable;

public class Student implements Serializable {

    public String docId;
    public String name;
    public int age;
    public int rollNo;
    public String title;

    // Constructor
    public Student(String docId, String name, int age, int rollNo, String title) {
        this.docId = docId;
        this.name = name;
        this.age = age;
        this.rollNo = rollNo;
        this.title = title;
    }
}
