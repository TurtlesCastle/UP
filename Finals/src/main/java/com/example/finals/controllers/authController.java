package com.example.finals.controllers;

import com.example.finals.config.WebSecurityConfig;
import com.example.finals.entities.User;
import com.example.finals.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class authController {
    private PasswordEncoder passwordEncoder = WebSecurityConfig.passwordEncoder();
    private final MyService serviceDAO;
    @Autowired
    public authController(MyService serviceDAO) {
        this.serviceDAO = serviceDAO;
    }
    @GetMapping("/reg")
    private String getRegister()
    {
        return "reg";
    }
    @GetMapping("/login")
    private String getLogin() {return "login";}
    @PostMapping("/reg")
    private  String reg(@RequestParam(name = "login")String login,
                        @RequestParam(name="password")String pass){
        if(!serviceDAO.register(new User(login,passwordEncoder.encode(pass)))) {
            return "reg";
        }
        return "login";
    }
}
