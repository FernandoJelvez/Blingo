package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {
    @GetMapping("/signup")
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        System.out.println("signupPage");
        return "signup";
    }
    @PostMapping("/signup")
    public ModelAndView signup (@ModelAttribute("user") @Valid User user) {
        UserService userService = new UserService();
        try {
            userService.registerNewUserAccount(user);
        } catch (Exception e) {
            ModelAndView mav=new ModelAndView ();
            mav.addObject("message","An error has occurred, please contact an administrator");
            return mav;
        }
        return new ModelAndView("successRegister", "user", user);
    }
}
