package dev.kannan.productservice.controllers;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.dtos.ErrorDto;
import dev.kannan.productservice.exceptions.ProductNotFoundException;
import dev.kannan.productservice.models.Product;
import dev.kannan.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") int productId) throws ProductNotFoundException {
        Product product = productService.getSingleProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        Product productResponse = productService.createProduct(
                createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getImage(),
                createProductRequestDto.getCategory()
        );

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int productId, @RequestBody CreateProductRequestDto createProductRequestDto) throws ProductNotFoundException {
        Product product = productService.updateProduct(
                productId,
                createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getImage(),
                createProductRequestDto.getCategory()
        );

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int productId) throws ProductNotFoundException {
        String response = productService.deleteProduct(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /** exception handler at product controller level for ProductNotFoundException */
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage(productNotFoundException.getMessage());
//
//        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
//    }
}
