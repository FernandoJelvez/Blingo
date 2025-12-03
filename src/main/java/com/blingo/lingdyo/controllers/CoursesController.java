package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.dtos.CourseDto;
import com.blingo.lingdyo.dtos.CourseWithEnrollingStateDto;
import com.blingo.lingdyo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoursesController {
    @Autowired CourseService courseService;

    @GetMapping("/enrolled_courses")
    public CourseDto[] course(@RequestParam(defaultValue="") String username) {
        return courseService.getUserCourses(username);
    }
    @GetMapping("/available_courses")
    public CourseDto[] course(){
        return courseService.getAvailableCourses();
    }
    @GetMapping("/available_courses_with_state")
    public CourseWithEnrollingStateDto[] courseWithEnrollingState(@RequestParam(defaultValue="") String username){
        return courseService.getAvailableCoursesWithState(username);
    }
}
