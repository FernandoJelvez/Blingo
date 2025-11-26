package com.blingo.lingdyo.services;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;}

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario " + username + " no encontrado")
                );

        System.out.println("Usuario " + username + " detectado, Hash de contraseña almacenado.");

        return new CustomUserDetails(user);
    }
}
