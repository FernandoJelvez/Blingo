package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoursesMenuController {
    @GetMapping("/courses-menu")
    public String courses(Model model){
        System.out.println("courses");
        return "courses-menu";
    }
}
