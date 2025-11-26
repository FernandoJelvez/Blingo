package com.blingo.lingdyo.services;

import com.blingo.lingdyo.Language;
import com.blingo.lingdyo.dtos.LanguageDto;
import com.blingo.lingdyo.repositorys.LanguageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LanguagesServiceTest {
    @Mock
    private LanguageRepository languageRepository;
    @InjectMocks
    private LanguagesService service;
    @Test
    void getLanguages_ReturnsArray() {
        // Arrange — simulamos entidades
        Language lang = new Language();
        lang.setId(1);
        lang.setName("English");
        when(languageRepository.findAll()).thenReturn(List.of(lang));
        // Act
        LanguageDto[] out = service.getLanguages();
        // Assert
        assertNotNull(out);
        assertEquals(1, out.length);
        assertEquals("English", out[0].getName());
    }
}