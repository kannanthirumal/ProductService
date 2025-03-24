package dev.kannan.productservice.services;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long productId);
    Product createProduct(CreateProductRequestDto product);
    List<Product> getAllProducts();
    Product updateProduct(Long productId, CreateProductRequestDto product);
    String deleteProduct(Long productId);
}
