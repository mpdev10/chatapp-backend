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

    @Getter
    private final Set<ErrorCode> errorCodes;

}
