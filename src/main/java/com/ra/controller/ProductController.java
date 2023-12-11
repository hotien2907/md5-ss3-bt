package com.ra.controller;
import com.ra.entity.Category;
import com.ra.entity.Product;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/product")
    public String product(Model model){
        model.addAttribute("products", productService.findAll());
        return "product";
    }
    @GetMapping("/add/product")
    public String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("listCategory",categoryService.findAll() );
        model.addAttribute("product",product);
        return "create-product";
    }

    @PostMapping("/create/product")
    public String createProduct(@ModelAttribute("product") Product product){
        productService.saveOrUpdate(product);
        return "redirect:/product";
    }
    @GetMapping("/delete/product/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        productService.delete(id);
        return "redirect:/product";
    }


    @GetMapping("/edit/product/{id}")
    public String editProduct(@PathVariable ("id") Integer id, Model model){
        Product product =productService.findById(id);
        model.addAttribute("listCategory",categoryService.findAll() );
        model.addAttribute("product",product);
        return "edit-product";
    }


}
