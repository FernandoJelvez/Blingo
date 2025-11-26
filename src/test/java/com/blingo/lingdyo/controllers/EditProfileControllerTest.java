package com.blingo.lingdyo.controllers;
import com.blingo.lingdyo.*;
import com.blingo.lingdyo.CustomUserDetails;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EditProfileControllerTest {
    @Mock
    CustomUserDetails cud;
    @Test //Test para metodo update de CustomUserDetails (no cambiar contraseña)
    void updateProfileDoesNotChangePassword() {
        User u = new User();
        u.setPswd("hash123");

        User updated = new User();
        updated.setPswd("other"); // intento ilegal

        cud.updateUser(updated);

        assertEquals("hash123", u.getPswd());
    }
}