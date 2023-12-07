package com.example.matrix.controller;



import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOError;
import java.io.IOException;

@Controller
public class HomeController {
    @GetMapping("/home")
    String getHome(){
        return "home";
    }
    @GetMapping("/calc")
    String getCalculator(){
        return "calc";
    }
    @GetMapping("/conv")
    String getConverter(){
        return "conv";
    }
    @GetMapping("/result")
    String getResult(Model model, @RequestParam(name = "result", defaultValue = "Что-то пошло не так") String result){
        model.addAttribute("value", result);
        return "result";
    }
    @PostMapping("/calc")
    String postResult(Model model, @RequestParam(name = "float1") float float1, @RequestParam(name = "float2") float float2,
                      @RequestParam(name="action") String action)
    {
        String result = "";
        switch (action)
        {
            case "+": result = Float.toString(float1 + float2); break;
            case "-": result = Float.toString(float1 - float2); break;
            case "x": result = Float.toString(float1 * float2); break;
            case "÷": result = Float.toString(float1 / float2); break;
            case "%": result = "Число " + Float.toString(float1) + " составляет " + Float.toString(float1 / float2 * 100) + " процента от числа " + Float.toString(float2);  break;
        }
        model.addAttribute("result", result);
        return "result";
    }
    @PostMapping("/conv")
    String postConvertedValue(Model model, @RequestParam(name = "convertFromValue", required = true, defaultValue = "0") float valueFrom, @RequestParam(name = "from") String from,
                              @RequestParam(name = "to") String to)
    {
        String value = "Неверно введено значение";
        try{
        }
        catch (NullPointerException e){
        }
        if (valueFrom >= 0 )
            switch (from) {
                case "dollars":
                    switch (to) {
                        case "dollar":
                            value = Float.toString(valueFrom);
                            break;
                        case "euro":
                            value = Float.toString(valueFrom * 0.921f);
                            break;
                        case "rouble":
                            value = Float.toString(valueFrom * 89.12f) ;
                            break;
                    }
                    ;
                    break;
                case "euros":
                    switch (to) {
                        case "dollar":
                            value = Float.toString(valueFrom * 1.09f) ;
                            break;
                        case "euro":
                            value = Float.toString(valueFrom) ;
                            break;
                        case "rouble":
                            value = Float.toString(valueFrom * 96.77f) ;
                            break;
                    }
                    ;
                    break;
                case "roubles":
                    switch (to) {
                        case "dollar":
                            value = Float.toString(valueFrom * 0.01122f) ;
                            break;
                        case "euro":
                            value = Float.toString(valueFrom * 0.010334f) ;
                            break;
                        case "rouble":
                            value = Float.toString(valueFrom) ;
                            break;
                    }
                    ;
                    break;
            }

        model.addAttribute("value", value);
        return "conv";
    }
}
