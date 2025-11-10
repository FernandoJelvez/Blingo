package com.blingo.lingdyo.configuration;

import com.blingo.lingdyo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("Usuario "+username+" no encontrado");
                });
        System.out.println("Usuario "+username+" detectado, Hash de contraseña almacenado.");
        return new CustomUserDetails(user);}
}
