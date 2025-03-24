package dev.kannan.productservice.controllers;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.dtos.ErrorResponseDto;
import dev.kannan.productservice.exceptions.ProductNotFoundException;
import dev.kannan.productservice.models.Product;
import dev.kannan.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId) throws ProductNotFoundException {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequestDto product) {
        Product productResponse = productService.createProduct(product);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long productId, @RequestBody CreateProductRequestDto product) throws ProductNotFoundException {
        Product productResponse = productService.updateProduct(productId, product);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) throws Exception {
        String response = productService.deleteProduct(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    local exception handler specific to the Product Controller class
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorResponseDto> handleProductNotFoundException(ProductNotFoundException e) {
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
//        errorResponseDto.setMessage(e.getMessage());
//        errorResponseDto.setResolution("Please try passing a valid productId.");
//        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
//    }
}
