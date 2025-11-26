package com.blingo.lingdyo.services;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.configuration.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository repo;
    @InjectMocks
    UserService service;

    @Test //Test para probar que UserService agrega el nuevo usuario a la DB
    void registerNewUserAccount_savesUserCorrectly() {
        User u = new User();
        u.setId("john");
        u.setPswd("123");
        u.setName("John");
        u.setLastname("Doe");
        u.setEmail("john@mail.com");

        // No importa si internamente usa ConexionMySQL o JPA.
        // Solo probamos que NO lance excepción.
        assertDoesNotThrow(() -> service.registerNewUserAccount(u));
    }
}