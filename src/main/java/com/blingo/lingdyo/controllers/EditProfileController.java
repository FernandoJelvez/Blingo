package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.configuration.CustomUserDetails;
import com.blingo.lingdyo.configuration.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @PostMapping("/my/profile/edit")
    @Transactional
    public String saveProfile(
            @ModelAttribute User updatedUser,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model) {
        User dbUser = userRepository.findById(userDetails.getUsername()).orElse(null);

        if (dbUser == null) {
            model.addAttribute("error", "User not found.");
            return "my/profile/edit";
        }
        //valida el email
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().contains("@")) {
            model.addAttribute("error", "Invalid email.");
            return "my/profile/edit";
        }
        //confirma username único
        if (!dbUser.getId().equals(updatedUser.getId())
                && userRepository.existsById(updatedUser.getId())) {
            model.addAttribute("error", "Username already exists.");
            return "my/profile/edit";
        }

        boolean changedId = !dbUser.getId().equals(updatedUser.getId());

        if (changedId) {//Crear un nuevo usuario con el ID nuevo
            User newUser = new User();
            newUser.setId(updatedUser.getId());
            newUser.setPswd(dbUser.getPswd());    //nota: esto solo conserva la contraseña, no hay cambios reales
            newUser.setName(updatedUser.getName());
            newUser.setLastname(updatedUser.getLastname());
            newUser.setEmail(updatedUser.getEmail());
            newUser.setAge(updatedUser.getAge());
            newUser.setNative_tonge(updatedUser.getNative_tonge());
            newUser.setDescription(dbUser.getDescription());

            userRepository.save(newUser);
            // eliminar el usuario viejo
            userRepository.delete(dbUser);
            // actualizar los datos en la sesión
            userDetails.updateUser(newUser);
        } else {
            // Edición normal
            dbUser.setName(updatedUser.getName());
            dbUser.setLastname(updatedUser.getLastname());
            dbUser.setEmail(updatedUser.getEmail());
            dbUser.setAge(updatedUser.getAge());
            dbUser.setNative_tonge(updatedUser.getNative_tonge());

            userRepository.save(dbUser);
            userDetails.updateUser(dbUser);
        }
        return "redirect:/my/profile";
    }
}
