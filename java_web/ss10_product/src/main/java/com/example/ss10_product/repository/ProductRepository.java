package com.example.ss10_product.repository;

import com.example.ss10_product.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private static final List<Product> productList = new ArrayList<>();

    static {
        productList.add(new Product(1, "Book", 800));
        productList.add(new Product(2, "Pen", 700));
        productList.add(new Product(3, "Book", 1800));
        productList.add(new Product(4, "Comp", 8000));
    }

    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public boolean deleteByID(int id) {
        return productList.removeIf(p -> p.getId() == id);
    }

    @Override
    public void addProduct(Product temp) {
        productList.add(temp);
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> temp = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().contains(name)) {
                temp.add(product);
            }
        }
        return temp;
    }
}
