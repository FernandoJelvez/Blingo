package com.blingo.lingdyo.dtos;

public record CourseWithEnrollingStateDto(String creator, String name, int likes, String level, String language,
                                          boolean is_enrolled) {}
