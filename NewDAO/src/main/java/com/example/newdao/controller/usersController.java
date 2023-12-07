package com.example.newdao.controller;

import com.example.newdao.service.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasAnyAuthority('Администратор')")
@Controller
public class usersController {
    private final ServiceDAO serviceDAO;

    @Autowired
    public usersController(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }
    @GetMapping("/users")
    public String getUsers(Model model)
    {
        model.addAttribute("users", serviceDAO.getUserRows());
        return "users";
    }

    @GetMapping("/useredit")
    public String getEditUser(Model model, @RequestParam(name = "login")String login)
    {

        model.addAttribute("user", serviceDAO.getUser(login));
        return "useredit";
    }

    @PutMapping("/useredit")
    public String putUser(@RequestParam(name="role")String role, @RequestParam(name="login")String login,
                          Model model)
    {
        String result = serviceDAO.putUser(role,login);
        if (result.isEmpty())
            model.addAttribute("exception", result);
        model.addAttribute("user", serviceDAO.getUser(login));
        return "useredit";
    }

    @DeleteMapping("/userdelete")
    public String deleteUser(@RequestParam(name="login")String login, Model model)
    {
        serviceDAO.deleteUser(login);
        model.addAttribute("users", serviceDAO.getUserRows());
        return "users";
    }
}
