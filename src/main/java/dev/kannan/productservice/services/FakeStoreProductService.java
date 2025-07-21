package dev.kannan.productservice.services;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
import dev.kannan.productservice.dtos.FakeStoreProductDto;
import dev.kannan.productservice.exceptions.ProductNotFoundException;
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
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public  FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        /** using getForObject */
         // FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
         // return fakeStoreProductDto.toProduct();

        /** using getForEntity */
        ResponseEntity<FakeStoreProductDto> fakeStoreProductResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductResponse.getBody();

        //fakeStoreProductResponse.getStatusCode() is HttpStatus.OK even if the product doesn't exist, so resorted to the below validation
        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product with ID " + id + " does not exist.");
        }

        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductResponse = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreProductResponse.getBody();

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(fakeStoreProductDto.toProduct());
        }

        return products;
    }

    @Override
    public Product createProduct(String title, String description, double price, String imageUrl, String category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(imageUrl);
        fakeStoreProductDto.setCategory(category);

        ResponseEntity<FakeStoreProductDto> fakeStoreProductResponse = restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        fakeStoreProductDto = fakeStoreProductResponse.getBody();

        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product updateProduct(long id, String title, String description, double price, String imageUrl, String category) throws ProductNotFoundException {
        Product product = getSingleProduct(id); //if product not found, then exception will bubble up naturally

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(imageUrl);
        fakeStoreProductDto.setCategory(category);

        ResponseEntity<FakeStoreProductDto> fakeStoreProductResponse = restTemplate.exchange(
            "https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(fakeStoreProductDto),
                FakeStoreProductDto.class
        );

        fakeStoreProductDto = fakeStoreProductResponse.getBody();
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public String deleteProduct(long id) throws ProductNotFoundException {

        Product product = getSingleProduct(id); //if product not found, then exception will bubble up naturally

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            return "Product with ID " + id + " deleted successfully";
        }

        return "Product with ID " + id + " could not be deleted";
    }


}
