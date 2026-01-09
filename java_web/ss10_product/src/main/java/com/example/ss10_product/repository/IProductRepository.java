package com.example.ss10_product.repository;

import com.example.ss10_product.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getAll();
    boolean deleteByID(int id);
    void addProduct(Product temp);
    List<Product> searchByName(String name);

}
