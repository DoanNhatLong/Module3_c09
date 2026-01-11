package com.example.student.entity;

public class Student {
    int id;
    String name;
    boolean gender;
    double score;
    int id_class;

    public Student() {
    }

    public Student(int id, String name, boolean gender, double score, int id_class) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.score = score;
        this.id_class = id_class;
    }

    public Student(String name, boolean gender, double score, int id_class) {
        this.name = name;
        this.gender = gender;
        this.score = score;
        this.id_class = id_class;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }
}
