package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {
    @Autowired
    private UserService userService;
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") @Valid User user) {
        try {
            userService.registerNewUserAccount(user);
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("signup");
            mav.addObject("message", "An error has occurred, please contact an administrator");
            return mav;
        }

        return new ModelAndView("login");
    }
}