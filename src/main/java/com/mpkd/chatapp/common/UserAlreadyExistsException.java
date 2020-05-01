package com.mpkd.chatapp.common;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(final String mail) {
        super(String.format("User %s already exists", mail));
    }
}

