package com.example.finals.controllers;

import com.example.finals.config.WebSecurityConfig;
import com.example.finals.entities.License;
import com.example.finals.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class guestController {
    private final MyService serviceDAO;
    @Autowired
    public guestController(MyService serviceDAO) {
        this.serviceDAO = serviceDAO;
    }


    @GetMapping("/recipelist")
    private String getRecipeList(Model model)
    {
        model.addAttribute("objects", serviceDAO.getRecipes());
        return "recipelist";
    }
    @GetMapping("/addlicense")
    private String getAddLicense()
    {
        return "addlicense";
    }
    @PostMapping("/addlicense")
    private String postAddLicense(@RequestParam(name="fio")String fio, @RequestParam(name="text_source")String text_source)
    {
        serviceDAO.addLicense(new License(text_source, fio));
        return "addlicense";
    }
    @PostMapping("/tofavourites")
    private String postFavourite(@RequestParam(name="ID")int ID, Model model)
    {
        serviceDAO.addFavourite(ID);
        model.addAttribute("objects", serviceDAO.getRecipes());
        return "recipelist";
    }

}
