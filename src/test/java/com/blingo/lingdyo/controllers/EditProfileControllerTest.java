package com.blingo.lingdyo.controllers;
import com.blingo.lingdyo.*;
import com.blingo.lingdyo.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EditProfileControllerTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CustomUserDetails userDetails;
    @Mock
    private Model model;
    @InjectMocks
    private EditProfileController controller;
    private User dbUser;
    private User updatedUser;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dbUser = new User();
        dbUser.setId(1); // ID real que no cambia nunca
        dbUser.setUsername("john");
        dbUser.setName("John");
        dbUser.setLastname("Doe");
        dbUser.setEmail("john@example.com");
        dbUser.setAge(30);
        dbUser.setNative_tonge("English");

        updatedUser = new User();
        updatedUser.setUsername("john1");
        updatedUser.setName("Johnny");
        updatedUser.setLastname("Doe");
        updatedUser.setEmail("johnny@example.com");
        updatedUser.setAge(31);
        updatedUser.setNative_tonge("English");
    }

    @Test
    void testSaveProfile_NormalEdit_CallsUpdateUser() {
        // Arrange
        when(userDetails.getUser()).thenReturn(dbUser);
        when(userRepository.findById(1)).thenReturn(Optional.of(dbUser));
        // Act
        // El username NO existe para otro usuario
        when(userRepository.existsByUsername("john1")).thenReturn(false);

        // Ejecutar
        String view = controller.saveProfile(updatedUser, userDetails, model);
        assertEquals("redirect:/my/profile", view);
        // Verifica que el usuario fue guardado
        verify(userRepository).save(dbUser);
        // Verifica que updateUser fue llamado con los valores modificados
        verify(userDetails).updateUser(argThat(user ->
                user.getName().equals("Johnny") &&
                        user.getEmail().equals("johnny@example.com") &&
                        user.getAge() == 31
        ));
    }
}