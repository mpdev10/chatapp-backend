package com.mpkd.chatapp.domain.exception;


import com.mpkd.chatapp.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

@RequiredArgsConstructor
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidUserDataException extends RuntimeException {

    public static final String MESSAGE_PREFIX = "Invalid user data: ";

    @Getter
    private final Set<ErrorCode> errorCodes;

    @Override
    public String getMessage() {
        return MESSAGE_PREFIX + errorCodes.stream().map(ErrorCode::getMessage).reduce((x, y) -> x + ", " + y).orElse("");
    }
}
