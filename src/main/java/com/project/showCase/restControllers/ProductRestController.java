package com.project.showCase.restControllers;

//import com.project.showCase.jdbc.JdbcProductRepository;
import com.project.showCase.jdbc.PostgreSQLConnection;
import com.project.showCase.models.Product;
import com.project.showCase.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ProductRestController {

    @Autowired
    ProductService productService;
    @Autowired
//    JdbcProductRepository jdbcProductRepository;

//    @GetMapping("/getAllProducts")
//    public ResponseEntity<List> getProducts() {
//        List<Product> listOfAlProducts = jdbcProductRepository.findAll();
//        return new ResponseEntity<>(listOfAlProducts, HttpStatus.OK);
//    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List> getProducts() throws SQLException {
        List<Product> listOfAlProducts = PostgreSQLConnection.getProducts();
        return new ResponseEntity<>(listOfAlProducts, HttpStatus.OK);
    }

//    @GetMapping("/getProductById/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
//        Product product = jdbcProductRepository.findById(id);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) throws SQLException {
        Product product = PostgreSQLConnection.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

//    @PostMapping("/addPhoneProduct")
//    public ResponseEntity<String> addPhoneProduct(@RequestBody Product newProduct) {
//        jdbcProductRepository.save(newProduct);
//        return new ResponseEntity<>("Product has been added!", HttpStatus.OK);
//    }

    @PostMapping("/addPhoneProduct")
    public ResponseEntity<String> addPhoneProduct(@RequestBody Product newProduct) throws SQLException {
        PostgreSQLConnection.addProduct(newProduct);
        return new ResponseEntity<>("Product has been added!", HttpStatus.OK);
    }

//    @PutMapping("/updateProduct")
//    public ResponseEntity<String> updateProduct(@RequestBody Product updatedProduct) {
//        jdbcProductRepository.updateProductName(updatedProduct);
//        return new ResponseEntity<>("Product " + updatedProduct.getId() + " has been updated!", HttpStatus.OK);
//    }

    @PutMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody Product updatedProduct) throws SQLException {
        PostgreSQLConnection.updateProduct(updatedProduct);
        return new ResponseEntity<>("Product " + updatedProduct.getId() + " has been updated!", HttpStatus.OK);
    }

//    @DeleteMapping("/deleteProduct/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
//        jdbcProductRepository.deleteById(id);
//        return new ResponseEntity<>("Product " + id + " has been deleted!", HttpStatus.OK);
//    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) throws SQLException {
        PostgreSQLConnection.deleteProduct(id);
        return new ResponseEntity<>("Product " + id + " has been deleted!", HttpStatus.OK);
    }



//    //These were used as templates with no DB connection
//    @GetMapping("/getProducts")
//    public ResponseEntity<List> getAllProducts() {
//        List<Product> listOfProducts = productService.getAllProducts();
//        return new ResponseEntity<>(listOfProducts, HttpStatus.OK);
//    }
//
//    @GetMapping("/getProduct/{id}")
//    public ResponseEntity<Product> getProduct(@PathVariable("id") String productId) {
//        Product product = productService.getProductById(productId);
//        if (product == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(product, HttpStatus.OK);
//        }
//    }
//
//    @PostMapping("/addProduct")
//    public Product addProduct(@RequestBody Product newProduct) {
//        System.out.println(newProduct.toString());
//        return productService.addProduct(newProduct);
//    }

}
