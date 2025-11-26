package com.blingo.lingdyo.services;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.repositorys.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository repo;
    @Mock
    BCryptPasswordEncoder encoder;
    @InjectMocks
    UserService service;
    @Test //Test para probar que UserService agrega el nuevo usuario a la DB
    void registerNewUserAccount_savesUserCorrectly() {
        User u = new User();
        u.setUsername("jhon123");
        u.setPswd("123");
        u.setName("John");
        u.setLastname("Doe");
        u.setEmail("john@mail.com");
        // Simular comportamiento del encoder
        when(encoder.encode("123")).thenReturn("encrypted123");
        // Simular save()
        when(repo.save(any(User.class))).thenReturn(u);
        assertDoesNotThrow(() -> service.registerNewUserAccount(u));
        // Verificar que realmente se llamó encode
        verify(encoder).encode("123");
        // Verificar que se llamó save()
        verify(repo).save(any(User.class));
    }
}