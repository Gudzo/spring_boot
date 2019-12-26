package com.project.showCase.controllers;

import com.project.showCase.models.Product;
import com.project.showCase.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/new")
    public String showProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String showProduct(@ModelAttribute("product") Product product) {
        System.out.println(product.toString());

        return "redirect:/new";
    }

}
