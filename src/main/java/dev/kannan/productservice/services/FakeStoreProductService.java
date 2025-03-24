package dev.kannan.productservice.services;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.dtos.FakeStoreProductDto;
import dev.kannan.productservice.exceptions.ProductNotFoundException;
import dev.kannan.productservice.models.Category;
import dev.kannan.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException {
       ResponseEntity<FakeStoreProductDto> fakeStoreProductResponse = restTemplate.getForEntity(
               "https://fakestoreapi.com/products/" + productId,
               FakeStoreProductDto.class
       );

       FakeStoreProductDto fakeStoreProductDto = fakeStoreProductResponse.getBody();
        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " does not exist.");
        }

       return fakeStoreProductDto.convertToProduct();
    }

    @Override
    public Product createProduct(CreateProductRequestDto product) {
       FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

       fakeStoreProductDto.setTitle(product.getTitle());
       fakeStoreProductDto.setDescription(product.getDescription());
       fakeStoreProductDto.setPrice(product.getPrice());
       fakeStoreProductDto.setCategory(product.getCategory());
       fakeStoreProductDto.setImage(product.getImage());

       ResponseEntity<FakeStoreProductDto> fakeStoreProductResponse = restTemplate.postForEntity(
               "https://fakestoreapi.com/products",
               product,
               FakeStoreProductDto.class
       );

       FakeStoreProductDto fakeStoreProduct = fakeStoreProductResponse.getBody();

       return fakeStoreProduct.convertToProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductResponse = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreProductResponse.getBody();


        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(fakeStoreProductDto.convertToProduct());
        }

        return products;
    }

    @Override
    public Product updateProduct(Long productId, CreateProductRequestDto product) throws ProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory());
        fakeStoreProductDto.setImage(product.getImage());

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + productId,
                HttpMethod.PUT,
                new HttpEntity<>(fakeStoreProductDto),
                FakeStoreProductDto.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ProductNotFoundException("Product with ID " + productId + " does not exist.");
        }

        FakeStoreProductDto response = responseEntity.getBody();

        return response.convertToProduct();
    }

    @Override
    public String deleteProduct(Long productId) throws Exception {
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + productId,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ProductNotFoundException("Product with ID " + productId + " does not exist.");
        }

        if(responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new Exception();
        }

        // According to the Fake Store API documentation, a response status of 200 (HttpStatus.OK) indicates that the product has been successfully deleted.
        return "Product with ID " + productId + " deleted successfully";

    }

}
