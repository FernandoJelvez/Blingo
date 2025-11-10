package com.blingo.lingdyo.services;

import com.blingo.lingdyo.dtos.CourseDto;
import com.blingo.lingdyo.dtos.CourseWithEnrollingStateDto;

public interface ICourseService {
    CourseDto[] getUserCourses(String userId);

    CourseDto[] getAvailableCourses();

    CourseWithEnrollingStateDto[] getAvailableCoursesWithState(String userId);
}
