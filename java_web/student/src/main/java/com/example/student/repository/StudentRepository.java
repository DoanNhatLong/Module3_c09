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
    private static final String UPDATE = "update student set name=?,gender=?,score=?, id_class=? where id=?";


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

    @Override
    public boolean updateStudent(Student student) {
        System.out.println(student.getId());
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setBoolean(2, student.isGender());
            preparedStatement.setDouble(3, student.getScore());
            preparedStatement.setInt(4, student.getId_class());
            preparedStatement.setInt(5, student.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Error");
            return false;
        }
    }

    @Override
    public List<StudentDto> search(String name, Integer gender, Integer idClass) throws SQLException {
        Connection connection = DBConnection.getConnection();
        List<StudentDto> studentList = new ArrayList<>();

        String sql = "select s.*, c.name as class_name " +
                "from student s join clazz c on s.id_class=c.id and s.isDeleted=0 " +
                "where 1=1 ";

        if (name != null && !name.isEmpty()) {
            sql += "and s.name like ? ";
        }
        if (gender != null) {
            sql += "and s.gender = ? ";
        }
        if (idClass != null) {
            sql += "and s.id_class = ? ";
        }

        PreparedStatement ps = connection.prepareStatement(sql);
        int index = 1;
        if (name != null && !name.isEmpty()) {
            ps.setString(index++, "%" + name + "%");
        }
        if (gender != null) {
            ps.setInt(index++, gender);
        }
        if (idClass != null) {
            ps.setInt(index++, idClass);
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            studentList.add(new StudentDto(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBoolean("gender"),
                    rs.getDouble("score"),
                    rs.getString("class_name"),
                    rs.getInt("id_class")
            ));
        }

        return studentList;
    }
}