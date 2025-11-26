package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.services.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyCoursesController {
    @GetMapping("/my/courses")
    public String myCourses(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("username",userDetails.getUsername());
        return "my/courses";
    }
}
