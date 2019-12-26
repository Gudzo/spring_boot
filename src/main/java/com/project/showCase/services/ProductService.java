package com.project.showCase.services;


import com.project.showCase.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ProductService {

    public List<Product> getAllProducts() {
        List<Product> listOfProducts = new ArrayList<>();
//        listOfProducts.add(new Product("1", "Samsung", "Ok", 100.50, "Amazon"));
//        listOfProducts.add(new Product("2", "Iphone", "Decent", 300.00, "Ebay"));
//        listOfProducts.add(new Product("3", "OnePlus", "Great", 250.00, "AliExpress"));

        return listOfProducts;
    }

    public Product getProductById(String id) {

        Predicate<Product> byId = p -> p.getId().equals(id);
        return filterProducts(byId);
    }

    private Product filterProducts(Predicate<Product> strategy) {
        return getAllProducts().stream().filter(strategy).findFirst().orElse(null);
    }

    public Product addProduct(Product newProduct) {
        return newProduct;
    }

}
