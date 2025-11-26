package com.blingo.lingdyo.controllers;
import com.blingo.lingdyo.*;
import com.blingo.lingdyo.repositorys.UserRepository;
import com.blingo.lingdyo.services.CustomUserDetails;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyProfileControllerTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    MyProfileController controller;

    @Test //Test para comprobar que el usuario se presenta correctamente en MyProfile
    void myProfileAddsUserToModel() {
        Model model = new ConcurrentModel();

        User u = new User();
        u.setName("Alice");

        CustomUserDetails cud = mock(CustomUserDetails.class);
        when(cud.getUser()).thenReturn(u);

        String view = controller.myHome(model, cud);

        User modelUser = (User) model.getAttribute("user");

        assertEquals("my/profile", view);
        assertEquals("Alice", modelUser.getName());
    }
    @Test //Test para actualizar descripcion en MyProfileController
    void updateDescriptionUpdatesUser() {
        User u = new User();
        u.setId(1);
        u.setDescription("old desc");

        CustomUserDetails cud = new CustomUserDetails(u);
        when(userRepository.findById(1))
                .thenReturn(Optional.of(u));

        controller.updateDescription("new desc", cud);
        assertEquals("new desc", u.getDescription());
    }
}