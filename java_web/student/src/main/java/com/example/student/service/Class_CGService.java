package com.example.student.service;

import com.example.student.entity.Class_CG;
import com.example.student.repository.Class_CGRepository;
import com.example.student.repository.IClass_CGRepository;

import java.sql.SQLException;
import java.util.List;

public class Class_CGService implements IClass_CGService {
    IClass_CGRepository classCgRepository=new Class_CGRepository();
    @Override
    public List<Class_CG> getAll() throws SQLException {
       return classCgRepository.getAll();
    }
}
