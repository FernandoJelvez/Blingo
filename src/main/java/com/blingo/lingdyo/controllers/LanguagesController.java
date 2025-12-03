package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.dtos.LanguageDto;
import com.blingo.lingdyo.services.LanguagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguagesController {
    @Autowired
    LanguagesService languagesService;
    @GetMapping("/languages")
    public LanguageDto[] languages(){
        return languagesService.getLanguages();
    }
}
