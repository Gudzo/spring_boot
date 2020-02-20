package com.project.showCase.controllers;

import com.project.showCase.models.Product;
import com.project.showCase.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Product> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);

        return "index";
    }

    @RequestMapping("/new")
    public String showProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String showProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        System.out.println(product.toString());

        return "redirect:/new";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editProduct(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("update");

        Product product = productService.get(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Integer id) {
        productService.delete(id);

        return "redirect:/";
    }

}

