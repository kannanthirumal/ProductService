package dev.kannan.productservice.services;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.exceptions.ProductNotFoundException;
import dev.kannan.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    public Page<Product> getAllProductsPaginated(int pageNo, int pageSize);
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
