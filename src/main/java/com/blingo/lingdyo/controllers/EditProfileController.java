package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.services.CustomUserDetails;
import com.blingo.lingdyo.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditProfileController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/my/profile/edit")
    public String editProfile(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("user", userDetails.getUser());
        return "my/profile/edit";
    }

    @PostMapping("/my/profile/save")
    public String saveProfile(
            @ModelAttribute User updatedUser,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model) {

        // Buscar por ID (el id real que no cambia)
        User dbUser = userRepository.findById(userDetails.getUser().getId()).orElse(null);
        if (dbUser == null) {
            model.addAttribute("error", "User not found.");
            return "my/profile/edit";
        }

        // Validación de email
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().contains("@")) {
            model.addAttribute("error", "Invalid email.");
            return "my/profile/edit";
        }

        if (!dbUser.getUsername().equals(updatedUser.getUsername())
                && userRepository.existsByUsername(updatedUser.getUsername())) {
            model.addAttribute("error", "Username already exists.");
            return "my/profile/edit";
        }

        // === Actualización normal (sin recrear el usuario) ===

        dbUser.setUsername(updatedUser.getUsername());
        dbUser.setName(updatedUser.getName());
        dbUser.setLastname(updatedUser.getLastname());
        dbUser.setEmail(updatedUser.getEmail());
        dbUser.setAge(updatedUser.getAge());
        dbUser.setNative_tonge(updatedUser.getNative_tonge());

        userRepository.save(dbUser);

        // Actualizar los datos de la sesión
        userDetails.updateUser(dbUser);

        return "redirect:/my/profile";
    }
}
