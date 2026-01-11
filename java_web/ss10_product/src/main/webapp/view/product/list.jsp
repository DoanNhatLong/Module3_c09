<%--
  Created by IntelliJ IDEA.
  User: Yuan
  Date: 1/8/2026
  Time: 9:17 PM
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
<div class="container">
    <h2 class="text-center"> Danh sách sản phẩm </h2>
    <form action="/products?action=search" method="post" class="float-end" role="search">
        <input name="searchName"
               class="form-control me-2"
               type="search"
               placeholder=" Tìm kiếm ..."
               aria-label="Search"
               style="width: 30rem;">
    </form>
    <table class="table table-striped ">
        <tr>
            <th>STT</th>
            <th>ID</th>
            <th>Tên</th>
            <th>Giá</th>
            <th>Xóa</th>
        </tr>
        <c:forEach items="${productList}" var="product" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>
                    <button type="button"
                            class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"
                            onclick="deleteInfo(`${product.name}`, `${product.id}`)">
                        Xóa
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a class="btn btn-primary" href="/products?action=create"> Thêm mới</a>
    <a class="btn btn-primary" href="/view/product/create.jsp"> Test</a>
</div>




<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/products?action=delete" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input hidden="hidden" id="deleteID" name="deleteID">
                    <span> Bạn muốn xóa sản phẩm  </span> <span id="deleteName"></span>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary">Xóa</button>
                </div>
            </div>
        </form>
    </div>

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


<script>
    function deleteInfo(name, id) {
        document.getElementById("deleteID").value = id;
        document.getElementById("deleteName").innerText = name;
    }
</script>
</body>
</html>
