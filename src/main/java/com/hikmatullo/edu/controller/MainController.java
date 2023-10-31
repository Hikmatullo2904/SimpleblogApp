package com.hikmatullo.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("title", "Home page");
        return "home";
    }

    @GetMapping("/about")
    public String getAboutPage(Model model) {
        model.addAttribute("title", "About us");
        return "about";
    }
}
