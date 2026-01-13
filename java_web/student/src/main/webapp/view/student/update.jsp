<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yuan
  Date: 1/12/2026
  Time: 8:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="../library.jsp"/>
</head>
<body>
<h2> Update</h2>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <form action="/students?action=update" method="post">
                <input type="hidden" name="id" value="${student.id}">
                <div class="mb-3">
                    <input class="form-control form-control-sm"
                           type="text"
                           placeholder="Nhập tên"
                           name="name"
                           value="${student.name}"
                           required
                           pattern="^([A-Z][a-z]+)(\s[A-Z][a-z]+)*$"
                           title="Tên phải viết hoa chữ cái đầu mỗi từ, chỉ gồm chữ cái (A-Z, a-z)">
                </div>
                <div class="mb-3">
                    <label class="form-label d-block">Giới tính</label>
                    <input type="radio" name="gender" value="true"
                           <c:if test="${student.gender}">checked</c:if>>
                    Nam

                    <input type="radio" name="gender" value="false"
                           <c:if test="${!student.gender}">checked</c:if>>
                    Nữ
                </div>
                <div class="mb-3">
                    <input class="form-control form-control-sm"
                           type="number"
                           placeholder="Nhập điểm"
                           name="score"
                           value="${student.score}"
                           min="0"
                           max="10"
                           required
                           title="Điểm nhập từ 0-10">
                </div>
                <div class="mb-4">
                    <select name="id_class" class="form-select form-select-sm" required>
                        <option value="" disabled>-- Chọn lớp --</option>
                        <c:forEach items="${classCG}" var="cls">
                            <option value="${cls.id}" <c:if test="${cls.id == student.id_class}">selected</c:if>>
                                    ${cls.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="text-center">
                    <button class="btn btn-success px-4">
                        Cập nhập
                    </button>
                </div>

            </form>

        </div>
    </div>
</div>
</body>
</html>
