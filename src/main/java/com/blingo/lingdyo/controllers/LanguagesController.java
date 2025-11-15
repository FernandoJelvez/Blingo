package com.blingo.lingdyo.controllers;

import com.blingo.lingdyo.dtos.LanguageDto;
import com.blingo.lingdyo.services.LanguagesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguagesController {
    @GetMapping("/languages")
    public LanguageDto[] languages(){
        LanguagesService langServ = new LanguagesService();
        return langServ.getLanguages();
    }
}
