package com.blingo.lingdyo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    private int id;
    private Integer userId;
    private int language_id;
    private String name;
    private int likes;
    private String level;

    // Getters y setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public Integer getUserId() {return userId;}
    public void setUserId(Integer userId) {this.userId = userId;}

    public int getLanguage_id() {return language_id;}
    public void setLanguage_id(int language_id) {this.language_id = language_id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getLikes() {return likes;}
    public void setLikes(int likes) {this.likes = likes;}

    public String getLevel() {return level;}
    public void setLevel(String level) {this.level = level;}
}
