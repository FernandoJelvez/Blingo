package com.blingo.lingdyo.services;

import com.blingo.lingdyo.User;
import com.blingo.lingdyo.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
}
