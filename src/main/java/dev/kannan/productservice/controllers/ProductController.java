package dev.kannan.productservice.controllers;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.models.Product;
import dev.kannan.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody CreateProductRequestDto product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long productId, @RequestBody CreateProductRequestDto product) {
        return productService.updateProduct(productId, product);
    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        return productService.deleteProduct(productId);
    }
}
