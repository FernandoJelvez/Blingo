package com.blingo.lingdyo.repositories;

import com.blingo.lingdyo.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByUserId(Integer userId);

    @Transactional
    void deleteByUserId(Integer userId);
}