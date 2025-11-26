package com.blingo.lingdyo.dtos;

public record LanguageDto(int id, String name) {
    public String getName(){return name;}
}
