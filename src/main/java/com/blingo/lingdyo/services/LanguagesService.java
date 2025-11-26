package com.blingo.lingdyo.services;

import com.blingo.lingdyo.ConexionMySQL;
import com.blingo.lingdyo.dtos.LanguageDto;
import com.blingo.lingdyo.repositorys.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguagesService implements ILanguagesService {
    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public LanguageDto[] getLanguages() {
        return languageRepository.findAll()
                .stream()
                .map(l -> new LanguageDto(l.getId(), l.getName()))
                .toArray(LanguageDto[]::new);
    }
}
