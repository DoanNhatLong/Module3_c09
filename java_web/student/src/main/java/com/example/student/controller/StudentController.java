package com.example.student.controller;

import com.example.student.dto.StudentDto;
import com.example.student.entity.Student;
import com.example.student.service.Class_CGService;
import com.example.student.service.IClass_CGService;
import com.example.student.service.IStudentService;
import com.example.student.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentController", value = "/students")
public class StudentController extends HttpServlet {
    IStudentService studentService = new StudentService();
    IClass_CGService classCgService = new Class_CGService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create" -> {
                try {
                    showFormAdd(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case "detail" -> {
                try {
                    goDetail(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case "update" -> {
                try {
                    updateStudent(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case "search" -> {
                try {
                    search(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> showList(req, resp);
        }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String name = req.getParameter("name");
        String genderParam = req.getParameter("gender");
        Integer gender = null;
        if (genderParam != null && !genderParam.isEmpty()) {
            gender = Integer.parseInt(genderParam);
        }
        String classParam = req.getParameter("id_class");
        Integer id_class = null;
        if (classParam != null && !classParam.isEmpty()) {
            id_class = Integer.parseInt(classParam);
        }
        List<StudentDto> studentDto = studentService.search(name, gender, id_class);
        req.setAttribute("studentList", studentDto);
        req.setAttribute("classCG", classCgService.getAll());
        req.getRequestDispatcher("/view/student/list.jsp").forward(req, resp);

    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        System.out.println("Check");
        int id = Integer.parseInt(req.getParameter("id"));
        StudentDto studentDto = studentService.findByID(id);
        req.setAttribute("student", studentDto);
        req.setAttribute("classCG", classCgService.getAll());
        System.out.println("Student ID: " + studentDto.getId());
        req.getRequestDispatcher("view/student/update.jsp").forward(req, resp);

    }

    private void goDetail(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        System.out.println("Check");
        int id = Integer.parseInt(req.getParameter("id"));
        StudentDto studentDto = studentService.findByID(id);
        req.setAttribute("student", studentDto);
        req.getRequestDispatcher("view/student/detail.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create" -> createStudent(req, resp);
            case "delete" -> deleteStudent(req, resp);
            case "search" -> searchByName(req, resp);
            case "update" -> update_Student(req, resp);
            default -> showList(req, resp);
        }
    }

    private void update_Student(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
        double score = Double.parseDouble(req.getParameter("score"));
        int id_class = Integer.parseInt(req.getParameter("id_class"));
        Student student = new Student(id, name, gender, score, id_class);
        boolean flag = studentService.updateStudent(student);
        String mess = flag ? "Cập nhập thành công" : "Cập nhập thất bại";
        req.getSession().setAttribute("message", mess);
        resp.sendRedirect("/students");
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("studentList", studentService.getAll());
            req.setAttribute("classCG", classCgService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/view/student/list.jsp").forward(req, resp);
    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println(req.getParameter("deleteID"));
        boolean flag = studentService.deleteByID(Integer.parseInt(req.getParameter("deleteID")));
        String mess = flag ? "Xóa thành công" : "Xóa thất bại";
        req.getSession().setAttribute("message", mess);
        resp.sendRedirect("/students");
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
        double score = Double.parseDouble(req.getParameter("score"));
        int id_class = Integer.parseInt(req.getParameter("id_class"));
        Student student = new Student(name, gender, score, id_class);
        boolean flag = studentService.addStudent(student);
        String mess = flag ? "Thêm mới thành công" : "Thêm mới thất bại";
        req.getSession().setAttribute("message", mess);
        resp.sendRedirect("/students");
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        req.setAttribute("classCG", classCgService.getAll());
        req.getRequestDispatcher("/view/student/add.jsp").forward(req, resp);
    }
}
