package com.shaale.rms.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/home")
    public String hod(Model model){ return "home"; }

    @GetMapping("/facultylist")
    public String faculty(Model model){ return "faculty"; }
}