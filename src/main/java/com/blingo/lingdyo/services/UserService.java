package com.blingo.lingdyo.services;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j(topic = "customLogs")
@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Override
    public void registerNewUserAccount(User user) {
        user.setPswd(encoder.encode(user.getPswd()));
        userRepository.save(user);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean emailExists(String email) {
        try {
            return userRepository.existsByEmail(email);
        } catch (Exception e) {
            log.error("services.UserService - ERROR verifying email '{}'. Exception: {}",
                    email, e.getMessage(), e);
            return false;
        }
    }
}
