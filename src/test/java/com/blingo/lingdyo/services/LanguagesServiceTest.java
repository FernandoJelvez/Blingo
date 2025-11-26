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
    @Test //Test que comprueba que se ha devuelto un arreglo usando LanguagesService
    void getLanguages_ReturnsArray() {
        // Simulamos entidades
        Language lang = new Language();
        lang.setId(1);
        lang.setName("English");
        when(languageRepository.findAll()).thenReturn(List.of(lang));
        // Assert
        assertNotNull(service.getLanguages());
        assertTrue(service.getLanguages().length > 0);
        assertEquals("English", service.getLanguages()[0].getName());

    }
}