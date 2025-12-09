package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.CustomUserDetails;
import com.blingo.lingdyo.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j(topic = "customLogs")
@Controller
public class MyProfileController {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public MyProfileController(UserRepository userRepository,
                               CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/my/profile")
    public String myHome(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("user", userDetails.getUser());
        return "my/profile";
    }

    @PostMapping("/my/profile/updateDescription")
    public String updateDescription(@RequestParam("description") String description,
                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userRepository.findById(userDetails.getId()).orElse(null);

        if (user != null && description != null) {
            user.setDescription(description.trim());
            try {
                userRepository.save(user);
            } catch (Exception e) {
                log.error("controllers.MyProfileController - ERROR updating description for user '{}'. Exception: {}",
                        user.getUsername(), e.getMessage(), e);
            }
            userDetails.setDescription(description);
            log.info("controllers.MyProfileController - The user '"+user.getUsername()+"' has been changed his description.");
        }
        return "redirect:/my/profile";
    }

    @PostMapping("/my/profile/delete")
    public String deleteAccount(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Integer id = userDetails.getUser().getId();
        String name = userDetails.getUsername();
        try {
            courseRepository.deleteByUserId(id);
            userRepository.deleteById(id);
            log.warn("controllers.MyProfileController - The user '"+name+"' has been deleted from the db.");
        } catch (Exception e) {
            log.error("controllers.MyProfileController - ERROR deleting account for user '{}'. Exception: {}",
                    name, e.getMessage(), e);
            return "redirect:/my/profile?error";
        }
        return "redirect:/";
    }
}