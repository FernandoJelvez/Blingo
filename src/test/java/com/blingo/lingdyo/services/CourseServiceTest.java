package com.blingo.lingdyo.services;

import com.blingo.lingdyo.dtos.CourseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {
    @Test //Se obtiene un CourseDto[] no nulo del CourseService
    void getUserCourses_ReturnsArray() {
        CourseService service = new CourseService();
        CourseDto[] result = service.getUserCourses("john");
        assertNotNull(result);
    }
}