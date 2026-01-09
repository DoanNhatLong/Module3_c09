package com.example.ss10_product.service;

import com.example.ss10_product.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    boolean deleteByID(int id);
    void addProduct(Product temp);
    List<Product> searchByName(String name);
}
