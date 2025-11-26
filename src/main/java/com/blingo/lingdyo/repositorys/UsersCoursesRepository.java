package com.blingo.lingdyo.repositorys;

import com.blingo.lingdyo.UsersCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsersCoursesRepository extends JpaRepository<UsersCourses, Integer> {
    List<UsersCourses> findByUserId(Integer userId);
}