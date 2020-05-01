package com.mpkd.chatapp.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class InvalidUserDataException extends RuntimeException {

    @Getter
    private final Set<ErrorCode> errorCodes;

}
