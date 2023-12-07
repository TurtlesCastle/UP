package com.example.newdao.controller;
import com.example.newdao.AppUser;
import com.example.newdao.Object;
import com.example.newdao.service.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.newdao.repositories.*;
import jakarta.validation.Valid;
import java.util.ArrayList;

import com.example.newdao.repositories.AnimalRepo;

@Controller
public class daoController{


    private final ServiceDAO serviceDAO;

    @Autowired
    public daoController(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }


    @GetMapping("/home")
    String getHome()
    {
        return "home";
    }



    @GetMapping("/list")
    public String getList(Model model, @RequestParam(name="col1")String col1, @RequestParam(name="col2")String col2,
                   @RequestParam(name="col3")String col3, @RequestParam(name="col4")String col4,
                   @RequestParam(name="table")String table, @RequestParam(name="species", defaultValue = "")String species)
    {
        model.addAttribute("col1", col1);
        model.addAttribute("col2", col2);
        model.addAttribute("col3", col3);
        model.addAttribute("col4", col4);
        model.addAttribute("objects", serviceDAO.getRows(table, species));
        model.addAttribute("table", table);
        model.addAttribute("species", species);
        return "list";
    }



}
