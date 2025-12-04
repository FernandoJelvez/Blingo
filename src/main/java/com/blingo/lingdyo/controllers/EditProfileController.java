package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.CustomUserDetails;
import com.blingo.lingdyo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j(topic = "customLogs")
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
        // Validación de username
        if (!dbUser.getUsername().equals(updatedUser.getUsername())
                && userRepository.existsByUsername(updatedUser.getUsername())) {
            model.addAttribute("error", "Username already exists.");
            return "my/profile/edit";}
        else if(!dbUser.getUsername().equals(updatedUser.getUsername())){
            log.warn("controllers.EditProfileController - The user "+dbUser.getUsername()+" has changed his username to "+updatedUser.getUsername()+".");
        }
        if(!dbUser.compareEditableDetails(updatedUser)){
            log.info("controllers.EditProfileController - User "+updatedUser.getUsername()+" has updated his profile information.");}
        // === Actualización normal (sin recrear el usuario) ===
        updateUser(dbUser,updatedUser);
        userDetails.updateUser(dbUser);
        return "redirect:/my/profile";
    }
    public void updateUser(User user, User updatedUser){
        user.setUsername(updatedUser.getUsername());
        user.setName(updatedUser.getName());
        user.setLastname(updatedUser.getLastname());
        user.setEmail(updatedUser.getEmail());
        user.setAge(updatedUser.getAge());
        user.setNative_tonge(updatedUser.getNative_tonge());

        userRepository.save(user);
    }
}
