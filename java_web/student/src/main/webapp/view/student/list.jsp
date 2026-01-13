<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yuan
  Date: 1/10/2026
  Time: 8:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
    <c:import url="../library.jsp"/>
</head>
<body>
<c:import url="../navbar.jsp"/>
<div class="container ">
    <h2 class="text-center mb-5"> Quản lí sinh viên </h2>
    <form action="/students?action=search" method="get">
        <div class="row g-3 align-items-center mb-3 justify-content-end">
            <div class="col-3">
                <input type="text" name="name" class="form-control" placeholder="Tên sinh viên">
            </div>
            <div class="col-3">
                <select name="gender" class="form-select">
                    <option value="">-- Giới tính --</option>
                    <option value="1">Nam</option>
                    <option value="0">Nữ</option>
                </select>
            </div>
            <div class="col-3">
                <select name="id_class" class="form-select">
                    <option value="">-- Chọn lớp --</option>
                    <c:forEach items="${classCG}" var="cls">
                        <option value="${cls.id}">${cls.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-12 mb-3 text-end">
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
        </div>
    </form>
    <table class="table table-striped ">
        <tr>
            <th>STT</th>
            <th>ID</th>
            <th>Tên</th>
            <th>Giới tính</th>
            <th>Điểm</th>
            <th>Lớp</th>
            <th>Xóa</th>
            <th>Cập nhập</th>
            <th>Chi tiết</th>
        </tr>
        <c:forEach items="${studentList}" var="student" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.gender ? 'Nam' : 'Nữ'}</td>
                <td>${student.score}</td>
                <td>${student.class_name}</td>
                <td>
                    <button type="button"
                            class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal"
                            onclick="deleteInfo(`${student.name}`,`${student.id}`)">
                        Xóa
                    </button>
                </td>
                <td>
                    <a class="btn btn-success" href="/students?action=update&id=${student.id}"> Cập nhập</a>
                </td>
                <td>
                    <a class="btn btn-primary" href="/students?action=detail&id=${student.id}"> Xem chi tiết</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a class="btn btn-primary" href="/students?action=create"> Thêm mới</a>
</div>

<c:if test="${sessionScope.message != null}">
    <div class="toast-container position-fixed top-0 end-0 p-3">
        <div class="toast show " role="alert">
            <div class="toast-header">
                <strong class="me-auto text-primary">Thông báo</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast"></button>
            </div>
            <div class="toast-body">
                    ${sessionScope.message}
            </div>
        </div>
    </div>
    <c:remove var="message" scope="session"/>
</c:if>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/students?action=delete" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="deleteID" name="deleteID">
                    <span>Bạn muốn xóa sinh viên </span>
                    <span id="deleteName"></span>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary">Xóa</button>
                </div>
            </div>
        </form>
    </div>

</div>

<script>
    function deleteInfo(name, id) {
        document.getElementById("deleteID").value = id;
        document.getElementById("deleteName").innerText = name;
    }
</script>

</body>
</html>
