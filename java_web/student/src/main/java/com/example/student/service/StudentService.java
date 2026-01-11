package com.example.student.service;

import com.example.student.dto.StudentDto;
import com.example.student.entity.Student;
import com.example.student.repository.IStudentRepository;
import com.example.student.repository.StudentRepository;

import java.sql.SQLException;
import java.util.List;

public class StudentService implements IStudentService {
    IStudentRepository studentRepository=new StudentRepository();

    @Override
    public List<StudentDto> getAll() throws SQLException {
        return studentRepository.getAll();
    }

    @Override
    public boolean addStudent(Student temp) {
        return studentRepository.addStudent(temp);
    }

    @Override
    public boolean deleteByID(int id) {
        return studentRepository.deleteByID(id);
    }

    @Override
    public StudentDto findByID(int id) throws SQLException {
        return studentRepository.findByID(id);
    }


}
