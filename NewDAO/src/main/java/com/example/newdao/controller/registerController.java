package com.example.newdao.controller;

import com.example.newdao.AppUser;
import com.example.newdao.config.WebSecurityConfig;
import com.example.newdao.service.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class registerController {
    private PasswordEncoder passwordEncoder = WebSecurityConfig.passwordEncoder();
    private final ServiceDAO serviceDAO;
    @Autowired
    public registerController(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }
    @GetMapping("/registration")
    private String getRegister()
    {
        return "registration";
    }
    @GetMapping("/login")
    private String getLogin() {return "login";}
    @PostMapping("/registration")
    private  String reg(@RequestParam(name = "login")String login,
                        @RequestParam(name="password")String pass,
                        @RequestParam(name="eMail")String eMail){
        if(!serviceDAO.register(new AppUser(login,eMail, passwordEncoder.encode(pass)))) {
            return "registration";
        }
        return "login";
    }
}
