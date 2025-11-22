package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.configuration.CustomUserDetails;
import com.blingo.lingdyo.configuration.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyProfileController {

    private final UserRepository userRepository;

    public MyProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/my/profile")
    public String myHome(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("user", userDetails.getUser());
        return "my/profile";
    }

    @PostMapping("/my/profile/updateDescription")
    public String updateDescription(@RequestParam("description") String description,
                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userRepository.findById(userDetails.getUsername()).orElse(null);

        if (user != null && description != null) {
            user.setDescription(description.trim());
            userRepository.save(user);
            userDetails.setDescription(description);}
        return "redirect:/my/profile";
    }
}