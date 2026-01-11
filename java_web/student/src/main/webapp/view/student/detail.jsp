<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi tiết sinh viên</title>
    <c:import url="../library.jsp"/>
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">

            <div class="card shadow-sm">
                <div class="card-header text-center fw-bold">
                    Chi tiết sinh viên
                </div>

                <div class="card-body">
                    <p><strong>ID:</strong> ${student.id}</p>
                    <p><strong>Tên:</strong> ${student.name}</p>
                    <p><strong>Giới tính:</strong> ${student.gender ? 'Nam' : 'Nữ'}</p>
                    <p><strong>Điểm:</strong> ${student.score}</p>
                    <p><strong>ID lớp:</strong> ${student.id_class}</p>
                    <p><strong>Lớp:</strong> ${student.class_name}</p>
                </div>

                <div class="card-footer text-center">
                    <a href="/students" class="btn btn-secondary btn-sm">Quay lại</a>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>