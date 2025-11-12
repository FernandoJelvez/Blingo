package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.configuration.CustomUserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class EditProfileController {
    @GetMapping("/my/profile/edit")
    public String editProfile(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("user", userDetails.getUser());
        return "my/profile/edit";
    }
}
