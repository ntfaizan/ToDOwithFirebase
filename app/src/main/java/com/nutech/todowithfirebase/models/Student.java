package com.nutech.todowithfirebase.models;

public class Student {

    public String docId;
    public String name;
    public int age;
    public int rollNo;
    public String title;



    public Student(String docId, String name, int age, int rollNo, String title) {
        this.docId = docId;
        this.name = name;
        this.age = age;
        this.rollNo = rollNo;
        this.title = title;
    }
}

