package com.blingo.lingdyo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "languages")
public class Language {
    @Id
    private Integer id;
    private String name;

// Getters y setters
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
}