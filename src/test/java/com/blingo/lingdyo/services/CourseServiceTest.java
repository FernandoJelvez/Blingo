package com.blingo.lingdyo.services;

import com.blingo.lingdyo.dtos.CourseDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.blingo.lingdyo.repositorys.*;
import com.blingo.lingdyo.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    @InjectMocks
    CourseService service;
    @Mock
    UserRepository userRepository;
    @Mock
    UsersCoursesRepository usersCoursesRepository;
    @Test //Course services obtiene un arreglo no nulo de Cursos
    void getUserCourses_ReturnsArray() {
        // arrange: usuario mock
        User u = new User();
        u.setId(1);
        when(userRepository.findByUsername("john")).thenReturn(java.util.Optional.of(u));
        when(usersCoursesRepository.findByUserId(1)).thenReturn(java.util.Collections.emptyList());
        // act
        CourseDto[] result = service.getUserCourses("john");
        // assert
        assertNotNull(result);
    }
}