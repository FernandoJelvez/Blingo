package com.blingo.lingdyo.services;

import com.blingo.lingdyo.ConexionMySQL;
import com.blingo.lingdyo.Course;
import com.blingo.lingdyo.dtos.CourseDto;
import com.blingo.lingdyo.dtos.CourseWithEnrollingStateDto;

import java.util.ArrayList;

public class CourseService implements ICourseService{

    @Override
    public CourseDto[] getUserCourses(String userId) {
        ConexionMySQL connMySql = new ConexionMySQL();
        ArrayList<CourseDto> courses = connMySql.getEnrolledCourses(userId);
        CourseDto[] out = new CourseDto[courses.size()];
        for (int i=0;i<courses.size();i++) {
            out[i] = courses.get(i);
        }
        return out;
    }

    @Override
    public CourseDto[] getAvailableCourses() {
        ConexionMySQL connMySql = new ConexionMySQL();
        ArrayList<CourseDto> courses = connMySql.getCourses();
        CourseDto[] out = new CourseDto[courses.size()];
        for (int i=0;i<courses.size();i++) {
            out[i] = courses.get(i);
        }
        return out;
    }

    @Override
    public CourseWithEnrollingStateDto[] getAvailableCoursesWithState(String userId) {
        ConexionMySQL connMySql = new ConexionMySQL();
        ArrayList<CourseWithEnrollingStateDto> courses = connMySql.getCoursesWithEnrollingState(userId);
        CourseWithEnrollingStateDto[] out = new CourseWithEnrollingStateDto[courses.size()];
        for (int i=0;i<courses.size();i++) {
            out[i] = courses.get(i);
        }
        return out;
    }

    @Override
    public void addCourse(Course course, String creator_username) {
        ConexionMySQL conexionMySQL = new ConexionMySQL();
        conexionMySQL.addCourse(creator_username,course.getName(),course.getLanguage_id(),course.getLevel());
    }

}
