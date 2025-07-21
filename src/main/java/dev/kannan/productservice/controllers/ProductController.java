package dev.kannan.productservice.controllers;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.models.Product;
import dev.kannan.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") int productId) {
        return productService.getSingleProduct(productId);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping()
    public Product createProduct(@RequestBody CreateProductRequestDto product) {
        return productService.createProduct(product);
    }
}
