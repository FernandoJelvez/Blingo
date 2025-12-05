package com.blingo.lingdyo.services;

import com.blingo.lingdyo.User;

public interface IUserService {
    void registerNewUserAccount(User user);

    boolean usernameExists(String username);
    boolean emailExists(String email);
}
