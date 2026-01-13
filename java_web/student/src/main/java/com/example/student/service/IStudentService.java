package com.example.student.service;

import com.example.student.dto.StudentDto;
import com.example.student.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentService {
    List<StudentDto> getAll() throws SQLException;
    boolean addStudent(Student temp);
    boolean deleteByID( int id);
    StudentDto findByID(int id) throws SQLException;
    boolean updateStudent(Student student);

    List<StudentDto> search(String name, Integer gender, Integer idClass) throws SQLException;
}
