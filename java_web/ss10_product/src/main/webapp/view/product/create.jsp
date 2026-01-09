<%--
  Created by IntelliJ IDEA.
  User: Yuan
  Date: 1/9/2026
  Time: 8:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:import url="../library.jsp"/>
</head>
<body>
<h2> Form</h2>
<div class="container ">
    <form action="/products?action=create" method="post">
        <input class="form-control" type="text" placeholder="Nhập id" name="id">
        <input class="form-control" type="text" placeholder="Nhập tên" name="name">
        <input class="form-control" type="text" placeholder="Nhập giá"  name="price">
        <button class="btn btn-success"> Thêm mới</button>
    </form>


</div>


</body>
</html>
