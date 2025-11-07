package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.dtos.CourseDto;
import com.blingo.lingdyo.services.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoursesController {
    @GetMapping("/enrolled_courses")
    public CourseDto[] course(@RequestParam(defaultValue="") String username) {
        CourseService cs = new CourseService();
        return cs.getUserCourses(username);
    }
}
