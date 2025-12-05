package com.blingo.lingdyo.exceptions;

import com.blingo.lingdyo.User;

public class RepeatedUserInfoException extends RuntimeException {
    private final User user;
    public RepeatedUserInfoException(String message, User user) {
        super(message);
        this.user=user;
    }

    public User getUser() {
        return user;
    }
}
