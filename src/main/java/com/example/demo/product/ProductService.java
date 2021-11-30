package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository){
        this.repository =  repository;
    }

    public Product getProduct(String id){
        return repository.findById(Long.parseLong(id)).orElse(null);
    }
    public Product addProduct(Product product){
        return repository.save(product);
    }

    public Product updateProduct(Product product) {
        Product updatedProduct = repository.findById(product.getId())
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException(Long.toString(product.getId()), product, " the specified product does not exist"));
        updatedProduct.setId(product.getId());
        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        return repository.save(updatedProduct);
    }

    public String deleteProduct(String id) {
        String response = "";
        Product product = repository.findById(Long.parseLong(id)).orElse(null);
        if (product != null) {
            repository.deleteById(Long.parseLong(id));
            response = "deleted";
        }
        else
            response = "Product not found";
        return response;
    }
}
