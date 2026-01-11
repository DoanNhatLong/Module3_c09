package com.example.ss10_product.controller;

import com.example.ss10_product.repository.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Kết nối thành công!");
        }
    }
}
