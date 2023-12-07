package com.example.finals.controllers;

import com.example.finals.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("hasAnyAuthority('Admin')")
@Controller
public class adminController {
    private final MyService myService;

    @Autowired
    public adminController(MyService myService) {
        this.myService = myService;
    }
    @GetMapping("/users")
    public String getUsers(Model model)
    {
        model.addAttribute("users", myService.getUserRows());
        return "users";
    }

    @GetMapping("/dishcategories")
    public  String getCategories(Model model)
    {
        model.addAttribute("objects", myService.getCategories());
        return "dishcategories";
    }
    @PostMapping("/dishcategories")
    public String postCategory(@RequestParam(name="name_category")String name, Model model)
    {
        myService.addCategory(name);
        model.addAttribute("objects", myService.getCategories());
        return "dishcategories";
    }
    @DeleteMapping("/dishcategories/delete")
    public String deleteCategory(@RequestParam(name = "name_category")String name, Model model)
    {
        myService.deleteCategory(name);
        model.addAttribute("objects", myService.getCategories());
        return "dishcategories";
    }
    @GetMapping("/dishtypes")
    public  String getTypes(Model model)
    {
        model.addAttribute("objects", myService.getTypes());
        return "dishtypes";
    }
    @PostMapping("/dishtypes")
    public String postType(@RequestParam(name="name_type")String name, Model model)
    {
        myService.addType(name);
        model.addAttribute("objects", myService.getTypes());
        return "dishtypes";
    }
    @DeleteMapping("/dishtypes/delete")
    public String deleteType(@RequestParam(name = "name_type")String name, Model model)
    {
        myService.deleteType(name);
        model.addAttribute("objects", myService.getTypes());
        return "dishtypes";
    }
}
