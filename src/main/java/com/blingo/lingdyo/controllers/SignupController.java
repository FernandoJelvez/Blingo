package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.exceptions.RepeatedUserInfoException;
import com.blingo.lingdyo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
        if (userService.usernameExists(user.getUsername())){
            throw new RepeatedUserInfoException("username: repeated username",user);
        } else if (userService.emailExists(user.getEmail())) {
            throw new RepeatedUserInfoException("email: repeated username",user);
        }
        userService.registerNewUserAccount(user);

        return new ModelAndView("login");
    }
    @ExceptionHandler
    public ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException error){
        User user = (User) error.getModel().get("user");
        ModelAndView mav = new ModelAndView("signup");
        mav.addObject("user",user);
        mav.addObject("error",error.getDetailMessageArguments()[1]);
        return mav;
    }
    @ExceptionHandler
    public ModelAndView handleRepeatedUserInfoException(RepeatedUserInfoException error){
        User user = error.getUser();
        ModelAndView mav = new ModelAndView("signup");
        mav.addObject("user",user);
        mav.addObject("error",error.getMessage());
        return mav;
    }
}