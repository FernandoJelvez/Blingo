package com.blingo.lingdyo.services;

import com.blingo.lingdyo.Course;
import com.blingo.lingdyo.Language;
import com.blingo.lingdyo.User;
import com.blingo.lingdyo.dtos.CourseDto;
import com.blingo.lingdyo.dtos.CourseWithEnrollingStateDto;
import com.blingo.lingdyo.repositories.CourseRepository;
import com.blingo.lingdyo.repositories.LanguageRepository;
import com.blingo.lingdyo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "customLogs")
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
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("services.CourseService - ERROR: User '{}' not found while obtaining user courses.", username);
                    return new RuntimeException("User not found");
                });
        Integer userId = userRepository.findByUsername(username)
                .orElseThrow().getId();
        List<Course> courses = courseRepository.findByUserId(userId);
        List<CourseDto> result = new ArrayList<>();
        for (Course c : courses) {

            String language = languageRepository.findById(c.getLanguage_id())
                    .map(Language::getName)
                    .orElseGet(() -> {
                        log.error("services.CourseService - ERROR: Language ID {} not found for course '{}'.",
                                c.getLanguage_id(), c.getName());
                        return "Unknown";
                    });
            result.add(new CourseDto(
                    userRepository.findById(c.getUserId()).orElseThrow().getUsername(),
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
                    userRepository.findById(c.getUserId()).orElseThrow().getUsername(),
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
                    userRepository.findById(c.getUserId()).orElseThrow().getUsername(),
                    c.getName(),
                    c.getLikes(),
                    c.getLevel(),
                    language,
                    false //
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