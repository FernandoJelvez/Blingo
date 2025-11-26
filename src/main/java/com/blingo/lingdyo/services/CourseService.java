package com.blingo.lingdyo.services;

import com.blingo.lingdyo.Course;
import com.blingo.lingdyo.Language;
import com.blingo.lingdyo.UsersCourses;
import com.blingo.lingdyo.dtos.CourseDto;
import com.blingo.lingdyo.dtos.CourseWithEnrollingStateDto;
import com.blingo.lingdyo.repositorys.CourseRepository;
import com.blingo.lingdyo.repositorys.LanguageRepository;
import com.blingo.lingdyo.repositorys.UserRepository;
import com.blingo.lingdyo.repositorys.UsersCoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UsersCoursesRepository usersCoursesRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CourseDto[] getUserCourses(String username) {
        Integer userId = userRepository.findByUsername(username)
                .orElseThrow().getId();
        List<UsersCourses> uc = usersCoursesRepository.findByUserId(userId);
        List<CourseDto> result = new ArrayList<>();
        for (UsersCourses item : uc) {
            Course c = courseRepository.findById(item.getCourseId()).orElse(null);
            if (c == null) continue;
            Language lang = languageRepository.findById(c.getLanguage_id()).orElse(null);
            result.add(new CourseDto(
                    c.getUserId().toString(),
                    c.getName(),
                    c.getLikes(),
                    c.getLevel(),
                    lang != null ? lang.getName() : "Unknown"
            ));
        }
        return result.toArray(new CourseDto[0]);
    }

    @Override
    public CourseDto[] getAvailableCourses() {

        List<Course> courses = courseRepository.findAll();
        List<CourseDto> result = new ArrayList<>();

        for (Course c : courses) {
            Language lang = languageRepository.findById(c.getLanguage_id()).orElse(null);

            result.add(new CourseDto(
                    c.getUserId().toString(),
                    c.getName(),
                    c.getLikes(),
                    c.getLevel(),
                    lang != null ? lang.getName() : "Unknown"
            ));
        }

        return result.toArray(new CourseDto[0]);
    }

    @Override
    public CourseWithEnrollingStateDto[] getAvailableCoursesWithState(String username) {

        Integer userId = userRepository.findByUsername(username).orElseThrow().getId();

        List<UsersCourses> enrolled = usersCoursesRepository.findByUserId(userId);

        List<Course> courses = courseRepository.findAll();

        List<CourseWithEnrollingStateDto> result = new ArrayList<>();

        for (Course c : courses) {

            boolean isEnrolled =
                    enrolled.stream().anyMatch(e -> e.getCourseId().equals(c.getId()));

            String language = languageRepository.findById(c.getLanguage_id())
                    .map(Language::getName).orElse("Unknown");

            result.add(new CourseWithEnrollingStateDto(
                    c.getUserId().toString(),
                    c.getName(),
                    c.getLikes(),
                    c.getLevel(),
                    language,
                    isEnrolled
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