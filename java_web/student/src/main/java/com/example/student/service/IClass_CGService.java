package com.example.student.service;

import com.example.student.entity.Class_CG;

import java.sql.SQLException;
import java.util.List;

public interface IClass_CGService {
    List<Class_CG> getAll() throws SQLException;
}
