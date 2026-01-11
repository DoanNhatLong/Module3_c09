package com.example.student.repository;

import com.example.student.entity.Class_CG;

import java.sql.SQLException;
import java.util.List;

public interface IClass_CGRepository {
    List<Class_CG> getAll() throws SQLException;
}
