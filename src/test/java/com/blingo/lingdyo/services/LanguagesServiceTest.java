package com.blingo.lingdyo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LanguagesServiceTest {
    @Test //Test que comprueba que se ha devuelto un arreglo usando LanguagesService
    void getLanguages_ReturnsArray() {
        LanguagesService service = new LanguagesService();

        assertNotNull(service.getLanguages());
        assertTrue(service.getLanguages().length >= 0);
    }
}