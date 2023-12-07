package com.example.finals.controllers;

import com.example.finals.config.WebSecurityConfig;
import com.example.finals.entities.Recipe;
import com.example.finals.entities.User;
import com.example.finals.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class authorController {
    private final MyService serviceDAO;
    @Autowired
    public authorController(MyService serviceDAO) {
        this.serviceDAO = serviceDAO;
    }
@GetMapping("/myrecipes")
    private String getMyRecipes(Model model)
    {
        model.addAttribute("objects", serviceDAO.getMyRecipes());
        return "myrecipes";
    }
@GetMapping("/addrecipe")
    private String getAddRecipe(Model model)
    {
        model.addAttribute("types", serviceDAO.getTypes());
        model.addAttribute("categories", serviceDAO.getCategories());
        return "addrecipe";
    }
    @PostMapping("/addrecipe")
    private String postAddRecipe(@RequestParam(name="category_name")String category_name,
                                 @RequestParam(name="type_name")String type_name,
                                 @RequestParam(name="name")String name,
                                 @RequestParam(name="text")String text,
                                 Model model)
    {
        serviceDAO.addRecipe(new Recipe(name, text), category_name, type_name);
        model.addAttribute("objects", serviceDAO.getMyRecipes());
        return "myrecipes";
    }
}
