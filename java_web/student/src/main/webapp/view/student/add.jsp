<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yuan
  Date: 1/10/2026
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="../library.jsp"/>
</head>
<body>
<h2 class="text-center my-4">Thêm mới</h2>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <form action="/students?action=create" method="post">
                <div class="mb-3">
                    <input class="form-control form-control-sm"
                           type="text"
                           placeholder="Nhập tên"
                           name="name"
                           required
                           pattern="^([A-Z][a-z]+)(\s[A-Z][a-z]+)*$"
                           title="Tên phải viết hoa chữ cái đầu mỗi từ, chỉ gồm chữ cái (A-Z, a-z)">
                </div>
                <div class="mb-3">
                    <label class="form-label d-block">Giới tính</label>

                    <div class="form-check form-check-inline">
                        <input class="form-check-input"
                               type="radio"
                               name="gender"
                               value="true"
                               id="genderMale" required>
                        <label class="form-check-label" for="genderMale">
                            Nam
                        </label>
                    </div>

                    <div class="form-check form-check-inline">
                        <input class="form-check-input"
                               type="radio"
                               name="gender"
                               value="false"
                               id="genderFemale">
                        <label class="form-check-label" for="genderFemale">
                            Nữ
                        </label>
                    </div>
                </div>
                <div class="mb-3">
                    <input class="form-control form-control-sm"
                           type="number"
                           placeholder="Nhập điểm"
                           name="score"
                           min="0"
                           max="10"
                           required
                           title="Điểm nhập từ 0-10">
                </div>
                <div class="mb-4">
                    <select name="id_class" class="form-select form-select-sm" required>
                        <option value="" disabled selected>-- Chọn lớp --</option>
                        <c:forEach items="${classCG}" var="cls">
                            <option value="${cls.id}">
                                    ${cls.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="text-center">
                    <button class="btn btn-success px-4">
                        Thêm mới
                    </button>
                </div>

            </form>

        </div>
    </div>
</div>


</body>
</html>
