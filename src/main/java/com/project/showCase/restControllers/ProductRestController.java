package com.project.showCase.restControllers;

import com.project.showCase.models.Product;
import com.project.showCase.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

    @Autowired
    ProductService productService;

    @GetMapping("/getProducts")
    public ResponseEntity<List> getAllProducts() {
        List<Product> listOfProducts = productService.getAllProducts();
        return new ResponseEntity<>(listOfProducts, HttpStatus.OK);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product newProduct) {
        System.out.println(newProduct.toString());
        return productService.addProduct(newProduct);
    }

}
