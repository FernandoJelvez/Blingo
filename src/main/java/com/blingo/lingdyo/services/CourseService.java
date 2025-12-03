package com.blingo.lingdyo.services;

import com.blingo.lingdyo.Course;
import com.blingo.lingdyo.Language;
import com.blingo.lingdyo.dtos.CourseDto;
import com.blingo.lingdyo.dtos.CourseWithEnrollingStateDto;
import com.blingo.lingdyo.repositories.CourseRepository;
import com.blingo.lingdyo.repositories.LanguageRepository;
import com.blingo.lingdyo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CourseDto[] getUserCourses(String username) {
        Integer userId = userRepository.findByUsername(username)
                .orElseThrow().getId();
        List<Course> courses = courseRepository.findByUserId(userId);
        List<CourseDto> result = new ArrayList<>();
        for (Course c : courses) {

            String language = languageRepository.findById(c.getLanguage_id())
                    .map(Language::getName)
                    .orElse("Unknown");
            result.add(new CourseDto(
                    c.getUserId().toString(),
                    c.getName(),
                    c.getLikes(),
                    c.getLevel(),
                    language
            ));
        }
        return result.toArray(new CourseDto[0]);
    }

    @Override
    public CourseDto[] getAvailableCourses() {

        List<Course> courses = courseRepository.findAll();

        List<CourseDto> result = new ArrayList<>();

        for (Course c : courses) {

            String language = languageRepository.findById(c.getLanguage_id())
                    .map(Language::getName)
                    .orElse("Unknown");

            result.add(new CourseDto(
                    c.getUserId().toString(),
                    c.getName(),
                    c.getLikes(),
                    c.getLevel(),
                    language
            ));
        }

        return result.toArray(new CourseDto[0]);
    }

    @Override
    public CourseWithEnrollingStateDto[] getAvailableCoursesWithState(String username) {

        List<Course> courses = courseRepository.findAll();

        List<CourseWithEnrollingStateDto> result = new ArrayList<>();

        for (Course c : courses) {

            String language = languageRepository.findById(c.getLanguage_id())
                    .map(Language::getName)
                    .orElse("Unknown");

            result.add(new CourseWithEnrollingStateDto(
                    c.getUserId().toString(),
                    c.getName(),
                    c.getLikes(),
                    c.getLevel(),
                    language,
                    false // 🔥 No existe inscripción
            ));
        }

        return result.toArray(new CourseWithEnrollingStateDto[0]);
    }

    @Override
    public void addCourse(Course course, String creatorUsername) {

        Integer userId = userRepository.findByUsername(creatorUsername)
                .orElseThrow().getId();

        course.setUserId(userId);

        courseRepository.save(course);
    }
}