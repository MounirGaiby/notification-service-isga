package com.mounirgaiby.notificationsservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("serviceName", "Notifications Service");
        model.addAttribute("version", "2.0");
        model.addAttribute("status", "RUNNING");
        return "index";
    }
}
