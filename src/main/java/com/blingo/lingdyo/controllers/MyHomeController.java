package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class MyHomeController {
    @GetMapping("/my/home")
    public String myHome(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("username",userDetails.getUsername());
        return "my/home";
    }
}
