package dev.kannan.productservice.services;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.exceptions.ProductNotFoundException;
import dev.kannan.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long productId) throws ProductNotFoundException;
    Product createProduct(String title, String description, Double price, String category, String image);
    List<Product> getAllProducts();
    Product updateProduct(Long productId, String title, String description, Double price, String category, String image) throws ProductNotFoundException;
    String deleteProduct(Long productId) throws Exception;
}