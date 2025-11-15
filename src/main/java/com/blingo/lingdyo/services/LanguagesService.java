package com.blingo.lingdyo.services;

import com.blingo.lingdyo.ConexionMySQL;
import com.blingo.lingdyo.dtos.LanguageDto;

import java.util.List;

public class LanguagesService implements  ILanguagesService {
    @Override
    public LanguageDto[] getLanguages(){
        ConexionMySQL conn = new ConexionMySQL();
        List<LanguageDto> languages = conn.getLanguages();
        LanguageDto[] out = new LanguageDto[languages.size()];
        for (int i = 0; i < languages.size(); i++) {
            out[i] = languages.get(i);
        }
        return out;
    }
}
