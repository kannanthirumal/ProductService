package dev.kannan.productservice.controllers;

import dev.kannan.productservice.exceptions.ProductNotFoundException;
import dev.kannan.productservice.models.Product;
import dev.kannan.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
/*
 @SpringBootTest
 annotation needed here coz the test needs the productcontroller bean
 from the application context
*/

@SpringBootTest
class ProductControllerTest {
    /*
      @Autowired
      for automatically injecting the "ProductController" object dependency
      - but for injecting the object - it should be available in the spring container
      - but to make that happen - we need to initialise the entire spring context
      - "@SpringBootTest" annotation comes for the rescue to solve the above issue
      - we don't have to have this annotation - if we don't need the spring context
     */

    @Autowired
    private ProductController productController;

    /* mock */
    /*
      @MockitoBean
      - here we won't use "@Autowired" coz - we don't want the original dependency object from the spring container
     */
    @MockitoBean
    @Qualifier("fakeStoreProductService")
    private ProductService  fakeStoreProductService;

    /* we can create and test multipl mockito beans */
    @MockitoBean
    @Qualifier("selfProductService")
    private ProductService  selfProductService;

    /* test cases */

    @Test
    void testGetSingleProductPositive() throws ProductNotFoundException {
        Product expectedProduct = new Product();
        

        when(fakeStoreProductService.getSingleProduct(1L))
                .thenReturn(expectedProduct);

        /* getBody() - coz we are getting response entity */
        Product actualProduct = productController.getSingleProduct(1L).getBody();

        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testGetSingleProductTimeout() throws ProductNotFoundException {

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(100000);
        expectedProduct.setTitle("Title");

        when(fakeStoreProductService.getSingleProduct(1L))
                .thenReturn(expectedProduct);

        /* getBody() - coz we are getting response entity */
        ResponseEntity<Product> response = productController.getSingleProduct(1L);
        Product actualProduct = response.getBody();

        assertTimeout(
                Duration.ofMillis(1L),
                () -> productController.getSingleProduct(1L)
        );
    }

    @Test
    void testGetSingleProductNegative() throws ProductNotFoundException {

        when(fakeStoreProductService.getSingleProduct(-1L))
                .thenThrow(ProductNotFoundException.class);
        /*
            - for input parameter "-1L" it throws exception - coz we hardcoded that way
            - but if we pass ("1L") it will return "null" - it won't call the actual dependency
            - if we hardcode it will return that, if we don't it will return null
         */

        assertThrows(ProductNotFoundException.class, () -> productController.getSingleProduct(-1L));
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getAllProductsPaginated() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}