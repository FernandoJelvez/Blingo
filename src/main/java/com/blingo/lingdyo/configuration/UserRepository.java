package com.blingo.lingdyo.configuration;

import java.util.Optional;
import com.blingo.lingdyo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String id);
}
