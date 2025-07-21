package dev.kannan.productservice.services;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.exceptions.ProductNotFoundException;
import dev.kannan.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(
            String title,
            String description,
            double price,
            String imageUrl,
            String category
    );

    Product updateProduct(
            long id,
            String title,
            String description,
            double price,
            String imageUrl,
            String category
    ) throws ProductNotFoundException;
    String deleteProduct(long id) throws ProductNotFoundException;
}
