package com.example.student.dto;

public class StudentDto {
    int id;
    String name;
    boolean gender;
    double score;
    String class_name;
    int id_class;

    public StudentDto() {
    }

    public StudentDto(String name, boolean gender, double score, String class_name) {
        this.name = name;
        this.gender = gender;
        this.score = score;
        this.class_name = class_name;
    }

    public StudentDto(int id, String name, boolean gender, double score, String class_name, int id_class) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.score = score;
        this.class_name = class_name;
        this.id_class = id_class;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public StudentDto(int id, String name, boolean gender, double score, String class_name) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.score = score;
        this.class_name = class_name;
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

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
