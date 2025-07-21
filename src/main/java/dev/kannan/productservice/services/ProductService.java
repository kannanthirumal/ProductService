package dev.kannan.productservice.services;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(int id);
    List<Product> getAllProducts();
    Product createProduct(CreateProductRequestDto product);
}
