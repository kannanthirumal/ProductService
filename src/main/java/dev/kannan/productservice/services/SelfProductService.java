package dev.kannan.productservice.services;

import dev.kannan.productservice.Projections.ProductProjection;
import dev.kannan.productservice.exceptions.ProductNotFoundException;
import dev.kannan.productservice.models.Category;
import dev.kannan.productservice.models.Product;
import dev.kannan.productservice.repositories.CategoryRepository;
import dev.kannan.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw new ProductNotFoundException("Product with the id " + id + " not found");
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public Product createProduct(String title, String description, double price, String imageUrl, String category) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        Category categoryObject = categoryRepository.findByTitle(category);
        if(categoryObject == null){
            categoryObject =  new Category();
            categoryObject.setTitle(category);
        }

        product.setCategory(categoryObject);
        Product createdProduct = productRepository.save(product);
        return createdProduct;
    }

    @Override
    public Product updateProduct(long id, String title, String description, double price, String imageUrl, String category) throws ProductNotFoundException {
        return null;
    }

    @Override
    public String deleteProduct(long id) throws ProductNotFoundException {
        return "";
    }
}
