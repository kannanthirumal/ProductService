package dev.kannan.productservice.services;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.exceptions.ProductNotFoundException;
import dev.kannan.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(int id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(
            String title,
            String description,
            double price,
            String imageUrl,
            String category
    );

    Product updateProduct(
            int id,
            String title,
            String description,
            double price,
            String imageUrl,
            String category
    ) throws ProductNotFoundException;
    String deleteProduct(int id) throws ProductNotFoundException;
}
