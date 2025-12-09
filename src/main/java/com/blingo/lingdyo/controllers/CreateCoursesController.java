package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.Course;
import com.blingo.lingdyo.CustomUserDetails;
import com.blingo.lingdyo.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j(topic = "customLogs")
@Controller
public class CreateCoursesController {
    @Autowired CourseService courseService;

    @GetMapping("/create/course")
    public String showCreateCoursesPage(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Course course = new Course();
        model.addAttribute("username",userDetails.getUsername());
        model.addAttribute("course",course);
        System.out.println("create course");
        return "/create/course";
    }
    @PostMapping("/create/course")
    public ModelAndView createCourse(@ModelAttribute("course") Course course, @AuthenticationPrincipal CustomUserDetails userDetails) {
        try{
            courseService.addCourse(course, userDetails.getUsername());
            log.warn("configuration.CreateCoursesController - The course of '"+userDetails.getUsername()+"' has been registered as '"+course.getName()+"'.");
        } catch (Exception e) {
            log.error("controllers.CreateCoursesController - ERROR creating course for user '{}'. Exception: {}",
                    userDetails.getUsername(), e.getMessage(), e);
            ModelAndView mav=new ModelAndView ();
            mav.addObject("message","An error has occurred, please contact an administrator");
            return mav;
        }
        return new ModelAndView("/success/create_course", "course", course);
    };
}
