package com.example.newdao.controller;

import com.example.newdao.Object;
import com.example.newdao.service.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.newdao.controller.daoController;

@Controller
@PreAuthorize("hasAnyAuthority('Редактор')")
public class daoRedactorController{
    private final ServiceDAO serviceDAO;

    @Autowired
    public daoRedactorController(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @GetMapping("/add")
    String getAdd(Model model, @RequestParam(name="colName1")String col1, @RequestParam(name="colName2")String col2,
                  @RequestParam(name="colName3")String col3, @RequestParam(name="colName4")String col4, @RequestParam(name="table")String table)
    {
        model.addAttribute("colName1", col1);
        model.addAttribute("colName2", col2);
        model.addAttribute("colName3", col3);
        model.addAttribute("colName4", col4);
        model.addAttribute("table", table);
        return "add";
    }
    @GetMapping("/edit")
    String getEdit(Model model, @RequestParam(name="colName1")String col1, @RequestParam(name="colName2")String col2,
                   @RequestParam(name="colName3")String col3, @RequestParam(name="colName4")String col4, @RequestParam(name="table")String table,
                   @ModelAttribute("object") Object object, @RequestParam(name="ID")int ID)
    {
        model.addAttribute("colName1", col1);
        model.addAttribute("colName2", col2);
        model.addAttribute("colName3", col3);
        model.addAttribute("colName4", col4);
        model.addAttribute("table", table);
        model.addAttribute("object", object);
        model.addAttribute("ID", ID);
        return "edit";
    }
    @PostMapping("/add")
    String postObject(Model model, @ModelAttribute("object") Object object, @RequestParam(name="colName1")String col1,
                      @RequestParam(name="colName2")String col2,
                      @RequestParam(name="colName3")String col3, @RequestParam(name="colName4")String col4, @RequestParam(name="table")String table)
    {
        String result = serviceDAO.postObject(table, object);
        model.addAttribute("colName1", col1);
        model.addAttribute("colName2", col2);
        model.addAttribute("colName3", col3);
        model.addAttribute("colName4", col4);
        model.addAttribute("table", table);
        model.addAttribute("exception", result);
        return "add";
    }


    @DeleteMapping("/delete")
    String deleteObject(@ModelAttribute("deleteID") int id, Model model, @RequestParam(name="col1")String col1, @RequestParam(name="col2")String col2,
                        @RequestParam(name="col3")String col3, @RequestParam(name="col4")String col4, @RequestParam(name="table")String table,
                        @RequestParam(name="species")String species)
    {
        serviceDAO.deleteObject(table, id);
        return "home";
    }
    @PutMapping("/edit")
    String putObject(Model model, @ModelAttribute("object") Object object, @RequestParam(name="colName1")String col1, @RequestParam(name="colName2")String col2,
                     @RequestParam(name="colName3")String col3, @RequestParam(name="colName4")String col4, @RequestParam(name="table")String table,
                     @RequestParam(name="ID")int ID)
    {
        String result = serviceDAO.putObject(table,ID,object);
        model.addAttribute("colName1", col1);
        model.addAttribute("colName2", col2);
        model.addAttribute("colName3", col3);
        model.addAttribute("colName4", col4);
        model.addAttribute("table", table);
        model.addAttribute("object", object);
        model.addAttribute("exception", result);
        return "edit";
    }
}
