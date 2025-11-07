package com.blingo.lingdyo.services;

import com.blingo.lingdyo.dtos.CourseDto;

public interface ICourseService {
    CourseDto[] getUserCourses(String userId);
}
