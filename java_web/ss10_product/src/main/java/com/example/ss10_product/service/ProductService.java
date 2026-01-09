package com.example.ss10_product.service;

import com.example.ss10_product.entity.Product;
import com.example.ss10_product.repository.IProductRepository;
import com.example.ss10_product.repository.MyException;
import com.example.ss10_product.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {
   IProductRepository productRepository=new ProductRepository();

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public boolean deleteByID(int id) {
        return productRepository.deleteByID(id);
    }

    @Override
    public void addProduct(Product temp) throws MyException {
        if (temp.getName() == null || temp.getName().trim().isEmpty()) {
            throw new MyException("Tên sản phẩm không được để trống");
        }
        productRepository.addProduct(temp);
    }

    @Override
    public List<Product> searchByName(String name) {
        return productRepository.searchByName(name);
    }


}
