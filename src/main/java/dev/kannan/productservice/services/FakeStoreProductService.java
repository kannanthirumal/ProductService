package dev.kannan.productservice.services;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.dtos.FakeStoreProductDto;
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

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long productId) {
       FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
               "https://fakestoreapi.com/products/" + productId,
               FakeStoreProductDto.class
       );
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

       FakeStoreProductDto response = restTemplate.postForObject(
               "https://fakestoreapi.com/products",
               product,
               FakeStoreProductDto.class
       );

       return response.convertToProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(fakeStoreProductDto.convertToProduct());
        }

        return products;
    }

    @Override
    public Product updateProduct(Long productId, CreateProductRequestDto product) {

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

        FakeStoreProductDto response = responseEntity.getBody();

        return response.convertToProduct();
    }

    @Override
    public String deleteProduct(Long productId) {
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + productId,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        // According to the Fake Store API documentation, a response status of 200 (HttpStatus.OK) indicates that the product has been successfully deleted.
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return "Product deleted successfully.";
        }

        return "The product could not be deleted. Ensure the product ID is correct and try again later.";
    }

}
