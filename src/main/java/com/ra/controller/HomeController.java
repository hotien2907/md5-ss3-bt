package com.ra.controller;

import com.ra.entity.Category;
import com.ra.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("listCategory", categoryService.findAll());
        return "home";
    }
    @GetMapping("/add/category")
    public String add(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "create-category";
    }

    @PostMapping("/create/category")
    public String create(@ModelAttribute ("category") Category category){
          categoryService.saveOrUpdate(category);
        return "redirect:/";
    }

    @GetMapping("/edit/category/{id}")
    public String edit(@PathVariable ("id") Integer id, Model model){
        Category category =categoryService.findById(id);
        model.addAttribute("category",category);
        return "edit-category";
    }

    @PostMapping("/update/category")
    public String update(@ModelAttribute ("category") Category category){
              if(categoryService.saveOrUpdate(category) !=null){
                  return "redirect:/";
              }
        return "edit-category";
    }

    @GetMapping("/delete/category/{id}")
    public String delete(@PathVariable ("id") Integer id){
       categoryService.delete(id);
        return "redirect:/";
    }
}
