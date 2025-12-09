package com.blingo.lingdyo.services;

import com.blingo.lingdyo.CustomUserDetails;
import com.blingo.lingdyo.User;
import com.blingo.lingdyo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j(topic = "customLogs")
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;}

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("services.CustomUserDetailsService - ERROR: User '{}' not found during login attempt.",
                            username);
                    return new UsernameNotFoundException("Usuario " + username + " no encontrado");
                });

        log.info("services.CustomUserDetailsService - User '{}' found. Password hash loaded.", username);;

        return new CustomUserDetails(user);
    }
}
