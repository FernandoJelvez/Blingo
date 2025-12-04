package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.repositories.UserRepository;
import com.blingo.lingdyo.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j(topic = "customLogs")
@Controller
public class SignupController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") @Valid User user) {
        try {
            userService.registerNewUserAccount(user);
            if(userRepository.existsByUsername(user.getUsername())){
                log.warn("controllers.SignupController - The user "+user.getUsername()+" has been registered");
            }
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("signup");
            mav.addObject("message", "An error has occurred, please contact an administrator");
            log.error("controllers.SignupController - Error registering user - "+e);
            return mav;
        }

        return new ModelAndView("login");
    }
}