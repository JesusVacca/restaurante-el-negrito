package com.prototype.restaurante_el_negro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard/users")
public class UserController {
    @GetMapping
    public String users(Model model) {
        return "users/listUser";
    }
}
