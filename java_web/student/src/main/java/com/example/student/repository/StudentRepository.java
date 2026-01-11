package com.example.student.repository;

import com.example.student.dto.StudentDto;
import com.example.student.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private static final String SELECT_ALL =
            "select s.*, c.name as class_name " +
                    "from student s join clazz c on s.id_class=c.id " +
                    "where s.isDeleted=0;";

    private static final String ADD_NEW = "insert into student(name,gender,score,id_class) value (?,?,?,?);";
    private static final String DELETE_SOFT = "update student set isdeleted =1 where id=?";
    private static final String FIND_ID =
            "select s.*, c.name as class_name " +
                    "from student s join clazz c on s.id_class=c.id " +
                    "where s.id=?;";

    @Override
    public List<StudentDto> getAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        List<StudentDto> studentList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            boolean gender = resultSet.getBoolean("gender");
            double score = resultSet.getDouble("score");
            String class_name = resultSet.getString("class_name");
            StudentDto studentDto = new StudentDto(id, name, gender, score, class_name);
            studentList.add(studentDto);
        }
        return studentList;
    }

    @Override
    public boolean addStudent(Student temp) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW);
            preparedStatement.setString(1, temp.getName());
            preparedStatement.setBoolean(2, temp.isGender());
            preparedStatement.setDouble(3, temp.getScore());
            preparedStatement.setInt(4, temp.getId_class());
            int row = preparedStatement.executeUpdate();
            return row == 1;
        } catch (SQLException e) {
            System.out.println("Error");
            return false;
        }
    }

    @Override
    public boolean deleteByID(int id) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SOFT);
            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
            return row == 1;
        } catch (SQLException e) {
            System.out.println("Error");
            return false;
        }
    }

    @Override
    public StudentDto findByID(int id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id_basic = resultSet.getInt("id");
                String name = resultSet.getString("name");
                boolean gender = resultSet.getBoolean("gender");
                double score = resultSet.getDouble("score");
                String class_name = resultSet.getString("class_name");
                int id_class = resultSet.getInt("id_class");
                return new StudentDto(id_basic, name, gender, score, class_name, id_class);
        }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
}
