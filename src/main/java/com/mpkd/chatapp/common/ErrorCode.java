package com.mpkd.chatapp.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode {
    NULL_NAME("name is null"),
    NULL_EMAIL("email is null"),
    NULL_PASSWORD("password is null"),
    INVALID_EMAIL("email is invalid");

    @Getter
    private final String message;

}
