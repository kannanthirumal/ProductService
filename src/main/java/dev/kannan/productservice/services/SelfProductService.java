package dev.kannan.productservice.services;

import dev.kannan.productservice.dtos.CreateProductRequestDto;
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
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private Category fetchOrCreateCategory(String categoryName) {
        Optional<Category> optionalCategory = categoryRepository.findByTitle(categoryName);
        Category category;
        if (optionalCategory.isEmpty()) {
            Category newCategory = new Category();
            newCategory.setTitle(categoryName);
            category = categoryRepository.save(newCategory);
        }
        else{
            category = optionalCategory.get();
        }

        return category;
    }

    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with ID " + productId + " does not exist.");
        }

        Product product;
        product = optionalProduct.get();
        return product;
    }

    @Override
    public Product createProduct(String title, String description, Double price, String categoryName, String image) {
        Category category = fetchOrCreateCategory(categoryName);

        Product newProduct = new Product();

        newProduct.setTitle(title);
        newProduct.setPrice(price);
        newProduct.setCategory(category);
        newProduct.setImageUrl(image);
        newProduct.setDescription(description);

        return productRepository.save(newProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long productId, String title, String description, Double price, String categoryName, String image) throws ProductNotFoundException {
       Optional<Product> optionalProduct = productRepository.findById(productId);
       Product updateProduct;

       if (optionalProduct.isEmpty()) {
           throw new ProductNotFoundException("Product with ID " + productId + " does not exist.");
       }
       else{
           Category category = fetchOrCreateCategory(categoryName);

           updateProduct = new Product();
           updateProduct.setId(productId);
           updateProduct.setTitle(title);
           updateProduct.setPrice(price);
           updateProduct.setDescription(description);
           updateProduct.setImageUrl(image);
           updateProduct.setCategory(category);
       }

       return productRepository.save(updateProduct);
    }

    @Override
    public String deleteProduct(Long productId) throws Exception {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with ID " + productId + " does not exist.");
        }

        productRepository.deleteById(productId);
        return "Product with ID " + productId + " deleted successfully";
    }
}
