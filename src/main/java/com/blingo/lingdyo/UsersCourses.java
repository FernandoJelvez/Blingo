package com.blingo.lingdyo;

import jakarta.persistence.*;

@Entity
@Table(name = "users_courses")
public class UsersCourses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "course_id")
    private Integer courseId;

    public Integer getUserId() { return userId; }
    public Integer getCourseId() { return courseId; }
}
