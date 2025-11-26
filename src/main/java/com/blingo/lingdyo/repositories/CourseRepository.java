package com.blingo.lingdyo.repositories;

import com.blingo.lingdyo.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByUserId(Integer userId);
}
