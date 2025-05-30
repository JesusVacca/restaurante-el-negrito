package com.prototype.restaurante_el_negro.controller;

import com.prototype.restaurante_el_negro.models.Member;
import com.prototype.restaurante_el_negro.services.AuthServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {
    private final AuthServices authServices;

    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }

    @GetMapping
    public String login() {
        return "base";
    }

    @PostMapping
    public String login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model) {
        System.out.println(username);
        try {
            Member member = this.authServices.login(username, password);
            System.out.println(member);
            return "redirect:/dashboard/";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "base";
        }
    }
}
