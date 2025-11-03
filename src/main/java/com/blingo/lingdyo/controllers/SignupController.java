package com.blingo.lingdyo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
@Controller
public class SignupController {
    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }
}
