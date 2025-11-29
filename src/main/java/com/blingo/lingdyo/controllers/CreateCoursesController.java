package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.Course;
import com.blingo.lingdyo.CustomUserDetails;
import com.blingo.lingdyo.services.CourseService;
import com.blingo.lingdyo.services.LanguagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateCoursesController {
    @Autowired
    private LanguagesService languagesService;

    @GetMapping("/create/course")
    public String showCreateCoursesPage(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        model.addAttribute("languages", languagesService.getLanguages());

        return "/create/course";
    }
    @PostMapping("/create/course")
    public ModelAndView createCourse(@ModelAttribute("course") Course course, @AuthenticationPrincipal CustomUserDetails userDetails) {
        CourseService courseService = new CourseService();
        try{
            courseService.addCourse(course, userDetails.getUsername());
        } catch (Exception e) {
            ModelAndView mav=new ModelAndView ();
            mav.addObject("message","An error has occurred, please contact an administrator");
            return mav;
        }
        return new ModelAndView("/success/create_course", "course", course);
    };
}
