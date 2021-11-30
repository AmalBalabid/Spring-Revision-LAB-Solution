package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "product")
public class ProductController {

    private final ProductService service;
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    // Use PathVariable to get specific product.
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id){
        return service.getProduct(id);

    }

    // Use Query params to GET the id and name of the Product and respond using RespondEntity.
    // to reach query params you have to use @RequestParam
    // http://localhost:8080/product?id=12&name=laptop
    @GetMapping
    public ResponseEntity<String> getIdAndName(@RequestParam String id, @RequestParam String name){
        return ResponseEntity.ok().body(id + " " + name);

    }

    // Use RequestBody to add product
    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return service.addProduct(product);
    }


    // Use RequestBody to update product
    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }

    // Delete specific product then response with that product using RespondEntity.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id){
        return ResponseEntity.ok().body(service.deleteProduct(id));
    }



}
