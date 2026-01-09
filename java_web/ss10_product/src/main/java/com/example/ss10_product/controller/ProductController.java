package com.example.ss10_product.controller;

import com.example.ss10_product.entity.Product;
import com.example.ss10_product.service.IProductService;
import com.example.ss10_product.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ProductController", value = "/products")

public class ProductController extends HttpServlet {
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create" -> showFormAdd(req, resp);
            case "delete" -> deleteProduct(req, resp);
            default -> showList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create" -> createProduct(req, resp);
            case "delete" -> deleteProduct(req, resp);
            case "search" -> searchByName(req, resp);
            default -> showList(req, resp);
        }
    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("searchName");
        req.setAttribute("productList", productService.searchByName(name));
        req.getRequestDispatcher("/view/product/list.jsp").forward(req, resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("deleteID"));
        boolean isDeleted = productService.deleteByID(id);
        String mess = "Không tìm thấy ID";
        if (isDeleted) {
            mess = "Đã xóa thành công";
        }
        req.getSession().setAttribute("message", mess);
        resp.sendRedirect("/products");
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        Product temp = new Product(id, name, price);
        productService.addProduct(temp);
        showList(req, resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("productList", productService.getAll());
        req.getRequestDispatcher("/view/product/list.jsp").forward(req, resp);
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/product/create.jsp").forward(req, resp);
    }


}
