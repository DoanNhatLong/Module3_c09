package com.example.student.repository;

import com.example.student.entity.Class_CG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Class_CGRepository implements IClass_CGRepository {
    private static final String SELECT_ALL="select * from clazz";
    @Override
    public List<Class_CG> getAll() throws SQLException {
        Connection connection= DBConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet= preparedStatement.executeQuery();
        List<Class_CG> class_cgList=new ArrayList<>();
        while (resultSet.next()){
            int id =resultSet.getInt("id");
            String name=resultSet.getString("name");
            Class_CG classCg=new Class_CG(id,name);
            class_cgList.add(classCg);
        }
        return class_cgList;
    }
}
